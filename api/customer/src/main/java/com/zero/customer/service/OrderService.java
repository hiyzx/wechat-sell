package com.zero.customer.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.common.constants.RedisPrefix;
import com.zero.common.dao.OrderDetailMapper;
import com.zero.common.dao.OrderMasterMapper;
import com.zero.common.dao.ProductInfoMapper;
import com.zero.common.exception.BaseException;
import com.zero.common.po.OrderDetail;
import com.zero.common.po.OrderMaster;
import com.zero.common.po.ProductInfo;
import com.zero.common.util.DateHelper;
import com.zero.common.util.NumberUtil;
import com.zero.common.util.StringHelper;
import com.zero.customer.enums.CustomerCodeEnum;
import com.zero.customer.util.RedisHelper;
import com.zero.customer.vo.MyOrderVo;
import com.zero.customer.vo.OrderDetailVo;
import com.zero.customer.vo.OrderVo;
import com.zero.customer.vo.dto.OrderDetailDto;
import com.zero.customer.vo.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    @Resource
    private OrderMasterMapper orderMasterMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private RedisHelper<String, List<OrderDetailDto>> redisHelper;

    public PageInfo<MyOrderVo> list(Integer userId, Integer page, Integer pageSize) {
        Condition masterCondition = new Condition(OrderMaster.class);
        masterCondition.createCriteria().andEqualTo("buyerId", userId);
        masterCondition.orderBy("createTime").desc();
        PageHelper.startPage(page, pageSize);
        List<OrderMaster> orderMasters = orderMasterMapper.selectByExample(masterCondition);
        PageInfo<OrderMaster> tmpPageInfo = new PageInfo<>(orderMasters);
        List<MyOrderVo> myOrderVos = new ArrayList<>(orderMasters.size());
        for (OrderMaster orderMaster : orderMasters) {
            MyOrderVo myOrderVo = new MyOrderVo();
            myOrderVo.setTotalCount(orderMaster.getTotalCount());
            myOrderVo.setTotalAmount(orderMaster.getTotalAmount());
            Condition detailCondition = new Condition(OrderDetail.class);
            detailCondition.createCriteria().andEqualTo("orderId", orderMaster.getId());
            detailCondition.orderBy("createTime").asc();
            OrderDetail orderDetail = orderDetailMapper.selectByExample(detailCondition).get(0);
            myOrderVo.setProductName(orderDetail.getProductName());
            myOrderVo.setProductImage(orderDetail.getProductIcon());
            myOrderVo.setOrderTime(orderMaster.getCreateTime());
            myOrderVo.setOrderStatus(orderMaster.getOrderStatus());
            myOrderVo.setPayStatus(orderMaster.getPayStatus());
            myOrderVos.add(myOrderVo);
        }
        PageInfo<MyOrderVo> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(tmpPageInfo, pageInfo);
        pageInfo.setList(myOrderVos);
        return pageInfo;
    }

    public void add(Integer userId, OrderDto orderDto) throws BaseException {
        // 将订单表和订单详情表写入数据库中
        List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetailDtos();
        String orderId = StringHelper.generateMasterKey();
        Double amount = 0D;
        Integer totalCount = 0;
        Date now = DateHelper.getCurrentDateTime();
        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
            String productInfoId = orderDetailDto.getProductInfoId();
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productInfoId);
            if (productInfo == null) {
                throw new BaseException(CustomerCodeEnum.PRODUCT_NOT_EXIST, "product is not exist");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(StringHelper.generateDetailKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productInfoId);
            orderDetail.setProductName(productInfo.getName());
            Integer count = orderDetailDto.getCount();
            orderDetail.setProductQuantity(count);
            orderDetail.setProductIcon(productInfo.getIcon());
            orderDetail.setCreateTime(now);
            orderDetail.setProductPrice(productInfo.getPrice());
            orderDetail.setUpdateTime(now);
            orderDetail.setIsDelete(false);
            orderDetailMapper.insert(orderDetail);
            totalCount += count;
            amount = NumberUtil.add(amount, NumberUtil.mul(productInfo.getPrice(), count));
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setBuyerId(userId);
        orderMaster.setId(orderId);
        orderMaster.setCreateTime(now);
        orderMaster.setTotalAmount(amount);
        orderMaster.setTotalCount(totalCount);
        orderMaster.setPayStatus(OrderMaster.PAY_STATUS_NOT);
        orderMaster.setOrderStatus(OrderMaster.ORDER_STATUS_NEW);
        orderMaster.setUpdateTime(now);
        orderMaster.setIsDelete(false);
        orderMasterMapper.insertSelective(orderMaster);
        log.info("{} order", orderDto.toString());
        // 将销量暂时放在缓存中,付款成功后再增加到数据库中
        redisHelper.set(wrapperRedisKey(orderId), orderDetailDtos);
    }

    public OrderVo getByOrderId(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        List<OrderDetailVo> orderDetails = this.getOrderDetailByMasterId(orderId);
        OrderVo rtn = new OrderVo();
        BeanUtils.copyProperties(orderMaster, rtn);
        rtn.setOrderDetailDtos(orderDetails);
        return rtn;
    }

    private List<OrderDetailVo> getOrderDetailByMasterId(String orderId) {
        Condition condition = new Condition(OrderDetail.class);
        condition.createCriteria().andEqualTo("orderId", orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(condition);
        List<OrderDetailVo> rtn = new ArrayList<>(orderDetails.size());
        orderDetails.forEach(orderDetail -> {
            OrderDetailVo tmp = new OrderDetailVo();
            BeanUtils.copyProperties(orderDetail, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public void cancel(String openid, String orderId) throws BaseException {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if (orderMaster.getPayStatus() == OrderMaster.PAY_STATUS_SUCCESS) {
            throw new BaseException(CustomerCodeEnum.HAS_PAY, "已经付款,不能取消");
        }
        if (orderMaster.getOrderStatus() != OrderMaster.ORDER_STATUS_NEW) {
            throw new BaseException(CustomerCodeEnum.NOT_NEW_ORDER, "非新订单,不能取消");
        }
        OrderMaster tmp = new OrderMaster();
        tmp.setId(orderId);
        tmp.setOrderStatus(OrderMaster.ORDER_STATUS_CANCEL);
        orderMasterMapper.updateByPrimaryKeySelective(tmp);
        log.info("openid={} cancel orderId={}", openid, orderId);
        // 取消订单,将缓存移除
        redisHelper.delete(wrapperRedisKey(orderId));
    }

    public void pay(String openid, String orderId) throws BaseException {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if (orderMaster.getPayStatus() == OrderMaster.PAY_STATUS_SUCCESS) {
            throw new BaseException(CustomerCodeEnum.HAS_PAY, "已经付款");
        }
        if (orderMaster.getOrderStatus() != OrderMaster.ORDER_STATUS_NEW) {
            throw new BaseException(CustomerCodeEnum.NOT_NEW_ORDER, "非新订单,不能付款");
        }
        OrderMaster tmp = new OrderMaster();
        tmp.setId(orderId);
        tmp.setPayStatus(OrderMaster.PAY_STATUS_SUCCESS);
        orderMasterMapper.updateByPrimaryKeySelective(tmp);
        log.info("openid={} pay order={}", openid, orderId);
        // 支付成功,修改商品销量
        List<OrderDetailDto> orderDetailDtos = redisHelper.get(wrapperRedisKey(orderId));
        orderDetailDtos.forEach(orderDetailDto -> {
            ProductInfo productInfo = new ProductInfo();
            String productInfoId = orderDetailDto.getProductInfoId();
            productInfo.setId(productInfoId);
            int count = orderDetailDto.getCount();
            productInfo.setSellCount(count);
            productInfoMapper.updateByPrimaryKeySelective(productInfo);
            log.info("openid={} pay success and modify the sellCount={} of productId={}", openid, count, productInfoId);
        });
        // 支付成功,将缓存移除
        redisHelper.delete(wrapperRedisKey(orderId));
    }

    private String wrapperRedisKey(String orderId) {
        return String.format("%s%s", RedisPrefix.ORDER_SELL_COUNT, orderId);
    }
}
