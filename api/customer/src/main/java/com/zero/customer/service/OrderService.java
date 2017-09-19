package com.zero.customer.service;

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
import com.zero.customer.vo.OrderDetailVo;
import com.zero.customer.vo.OrderVo;
import com.zero.customer.vo.dto.OrderDetailDto;
import com.zero.customer.vo.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Service
@Slf4j
public class OrderService {

    @Resource
    private OrderMasterMapper orderMasterMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private RedisHelper<String, List<OrderDetailDto>> redisHelper;

    public void add(OrderDto orderDto) throws BaseException {
        // 将订单表和订单详情表写入数据库中
        List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetailDtos();
        String orderId = StringHelper.generateMasterKey();
        Double amount = 0D;
        List<OrderDetail> orderDetailList = new ArrayList<>(orderDetailDtos.size());
        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
            String productInfoId = orderDetailDto.getProductInfoId();
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productInfoId);
            if (productInfo == null) {
                throw new BaseException(CustomerCodeEnum.PRODUCT_NOT_EXIST, "product is not exist");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(StringHelper.generateDetailKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productInfoId);
            orderDetail.setProductName(productInfo.getProductName());
            Integer count = orderDetailDto.getCount();
            orderDetail.setProductQuantity(count);
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setCreateTime(DateHelper.getCurrentDateTime());
            orderDetail.setProductPrice(NumberUtil.mul(productInfo.getProductPrice(), count));
            orderDetailList.add(orderDetail);
            amount = NumberUtil.add(amount, orderDetail.getProductPrice());
        }
        orderDetailMapper.insertList(orderDetailList);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setCreateTime(DateHelper.getCurrentDateTime());
        orderMaster.setOrderAmount(amount);
        orderMaster.setPayStatus(OrderMaster.PAY_STATUS_NOT);
        orderMaster.setOrderStatus(OrderMaster.ORDER_STATUS_NEW);
        orderMasterMapper.insertSelective(orderMaster);
        log.info("{} order", orderDto.toString());
        // 将销量暂时放在缓存中,付款成功后再增加到数据库中
        redisHelper.set(String.format("%s%s", RedisPrefix.ORDER_SELL_COUNT, orderId), orderDetailDtos);
    }

    public OrderVo getByOrderId(Integer orderId) {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        List<OrderDetailVo> orderDetails = this.getOrderDetailByMasterId(orderId);
        OrderVo rtn = new OrderVo();
        BeanUtils.copyProperties(orderMaster, rtn);
        rtn.setOrderDetailDtos(orderDetails);
        return rtn;
    }

    private List<OrderDetailVo> getOrderDetailByMasterId(Integer orderId) {
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
}
