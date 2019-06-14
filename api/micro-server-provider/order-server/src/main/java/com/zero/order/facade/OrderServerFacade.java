package com.zero.order.facade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.netflix.discovery.converters.Auto;
import com.zero.common.constants.RedisPrefix;
import com.zero.common.enums.CodeEnum;
import com.zero.common.exception.BaseException;
import com.zero.common.po.OrderDetail;
import com.zero.common.po.OrderMaster;
import com.zero.common.po.ProductInfo;
import com.zero.common.util.DateHelper;
import com.zero.common.util.NumberUtil;
import com.zero.common.util.StringHelper;
import com.zero.common.vo.ReturnVo;
import com.zero.order.enums.CustomerCodeEnum;
import com.zero.order.mq.MessageProducer;
import com.zero.order.service.OrderDetailService;
import com.zero.order.service.OrderMasterService;
import com.zero.order.util.RedisHelper;
import com.zero.order.vo.MyOrderVo;
import com.zero.order.vo.OrderDetailVo;
import com.zero.order.vo.OrderVo;
import com.zero.order.vo.dto.OrderDetailDto;
import com.zero.order.vo.dto.OrderDto;
import com.zero.order.vo.message.UserPayMessage;
import com.zero.product.feign.ProductServerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Service
@Slf4j
public class OrderServerFacade {

    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductServerClient productServerClient;
    @Resource
    private RedisHelper<String, List<OrderDetailDto>> redisHelper;
    @Resource
    private MessageProducer messageProducer;

    public IPage<MyOrderVo> list(Integer userId, Integer page, Integer pageSize) {
        IPage<OrderMaster> orderMasterPage = orderMasterService.findByUserId(userId, page, pageSize);
        List<OrderMaster> orderMasters = orderMasterPage.getRecords();
        List<MyOrderVo> myOrderVos = new ArrayList<>(orderMasters.size());
        for (OrderMaster orderMaster : orderMasters) {
            MyOrderVo myOrderVo = new MyOrderVo();
            myOrderVo.setTotalCount(orderMaster.getTotalCount());
            myOrderVo.setTotalAmount(orderMaster.getTotalAmount());
            OrderDetail orderDetail = orderDetailService.findByOrderUid(orderMaster.getUid()).get(0);
            myOrderVo.setOrderId(orderMaster.getUid());
            myOrderVo.setProductName(orderDetail.getProductName());
            myOrderVo.setProductImage(orderDetail.getProductIcon());
            myOrderVo.setOrderTime(orderMaster.getCreateTime());
            myOrderVo.setOrderStatus(orderMaster.getOrderStatus());
            myOrderVo.setPayStatus(orderMaster.getPayStatus());
            myOrderVos.add(myOrderVo);
        }
        IPage<MyOrderVo> pageInfo = new Page<>();
        BeanUtils.copyProperties(orderMasterPage, pageInfo);
        pageInfo.setRecords(myOrderVos);
        return pageInfo;
    }

    public String add(Integer userId, OrderDto orderDto) throws BaseException {
        // 将订单表和订单详情表写入redis中
        List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetailDtos();
        String orderId = StringHelper.generateMasterKey();
        Double amount = 0D;
        Integer totalCount = 0;
        Date now = DateHelper.getCurrentDateTime();
        List<OrderDetail> orderDetailList = new ArrayList<>(orderDetailDtos.size());
        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
            Integer productId = orderDetailDto.getProductId();
            ReturnVo<ProductInfo> productInfoReturnVo = productServerClient.get(productId);
            if (!Objects.equals(productInfoReturnVo.getResCode(), CodeEnum.SUCCESS.getCodeEnum())) {
                throw new BaseException(CustomerCodeEnum.REQUEST_ERROR, "request product-server error");
            }
            ProductInfo productInfo = productInfoReturnVo.getData();
            if (productInfo == null) {
                throw new BaseException(CustomerCodeEnum.PRODUCT_NOT_EXIST, "product is not exist");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setUid(StringHelper.generateDetailKey());
            orderDetail.setOrderUid(orderId);
            orderDetail.setProductId(productInfo.getId());
            orderDetail.setProductName(productInfo.getName());
            Integer count = orderDetailDto.getCount();
            orderDetail.setProductQuantity(count);
            orderDetail.setProductIcon(productInfo.getIcon());
            orderDetail.setCreateTime(now);
            orderDetail.setProductPrice(productInfo.getPrice());
            orderDetail.setUpdateTime(now);
            orderDetail.setIsDelete(false);
            orderDetailList.add(orderDetail);
            totalCount += count;
            amount = NumberUtil.add(amount, NumberUtil.mul(productInfo.getPrice(), count));
        }
        orderDetailService.insertBatch(orderDetailList);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setBuyerId(userId);
        orderMaster.setUid(orderId);
        orderMaster.setCreateTime(now);
        orderMaster.setTotalAmount(amount);
        orderMaster.setTotalCount(totalCount);
        orderMaster.setPayStatus(OrderMaster.PAY_STATUS_NOT);
        orderMaster.setOrderStatus(OrderMaster.ORDER_STATUS_NEW);
        orderMaster.setUpdateTime(now);
        orderMaster.setIsDelete(false);
        orderMasterService.insert(orderMaster);
        log.info("{} order", orderDto.toString());
        // 将销量暂时放在缓存中,付款成功后再增加到数据库中
        redisHelper.set(wrapperRedisKey(orderId), orderDetailDtos);
        return orderMaster.getUid();
    }

    public OrderVo getByOrderId(String orderId) throws BaseException {
        OrderMaster orderMaster = getByUid(orderId);
        if (orderMaster == null) {
            throw new BaseException(CodeEnum.ORDER_NOT_FOUND, "未找到该订单");
        }
        List<OrderDetailVo> orderDetails = this.getOrderDetailByMasterId(orderId);
        OrderVo rtn = new OrderVo();
        BeanUtils.copyProperties(orderMaster, rtn);
        rtn.setOrderDetailVos(orderDetails);
        return rtn;
    }

    private List<OrderDetailVo> getOrderDetailByMasterId(String orderId) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderUid(orderId);
        List<OrderDetailVo> rtn = new ArrayList<>(orderDetails.size());
        orderDetails.forEach(orderDetail -> {
            OrderDetailVo tmp = new OrderDetailVo();
            BeanUtils.copyProperties(orderDetail, tmp);
            rtn.add(tmp);
        });
        return rtn;
    }

    public void cancel(Integer userId, String orderId) throws BaseException {
        OrderMaster orderMaster = getByUid(orderId);
        if (orderMaster == null) {
            throw new BaseException(CodeEnum.ORDER_NOT_FOUND, "未找到该订单");
        }
        if (orderMaster.getPayStatus() == OrderMaster.PAY_STATUS_SUCCESS) {
            throw new BaseException(CustomerCodeEnum.HAS_PAY, "已经付款,不能取消");
        }
        if (orderMaster.getOrderStatus() != OrderMaster.ORDER_STATUS_NEW) {
            throw new BaseException(CustomerCodeEnum.NOT_NEW_ORDER, "非新订单,不能取消");
        }
        OrderMaster tmp = new OrderMaster();
        tmp.setId(orderMaster.getId());
        tmp.setOrderStatus(OrderMaster.ORDER_STATUS_CANCEL);
        orderMasterService.updateById(tmp);
        log.info("userId={} cancel orderId={}", userId, orderId);
        // 取消订单,将缓存移除
        redisHelper.delete(wrapperRedisKey(orderId));
    }

    public void pay(Integer userId, String orderId) throws BaseException {
        OrderMaster orderMaster = getByUid(orderId);
        if (orderMaster == null) {
            throw new BaseException(CodeEnum.ORDER_NOT_FOUND, "未找到该订单");
        }
        if (orderMaster.getPayStatus() == OrderMaster.PAY_STATUS_SUCCESS) {
            throw new BaseException(CustomerCodeEnum.HAS_PAY, "已经付款");
        }
        if (orderMaster.getOrderStatus() != OrderMaster.ORDER_STATUS_NEW) {
            throw new BaseException(CustomerCodeEnum.NOT_NEW_ORDER, "非新订单,不能付款");
        }
        OrderMaster tmp = new OrderMaster();
        tmp.setId(orderMaster.getId());
        tmp.setPayStatus(OrderMaster.PAY_STATUS_SUCCESS);
        orderMasterService.updateById(tmp);
        log.info("userId={} pay order={}", userId, orderId);
        // 支付成功,修改商品销量
        List<OrderDetailDto> orderDetailDtos = redisHelper.get(wrapperRedisKey(orderId));
        // 将商品信息写入mq进行统计
        messageProducer.sendUserPayMessage(new UserPayMessage(userId, orderDetailDtos));
        // 支付成功,将缓存移除
        redisHelper.delete(wrapperRedisKey(orderId));
    }

    private OrderMaster getByUid(String orderId) {
        try {
            return orderMasterService.findBuUid(orderId);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    private String wrapperRedisKey(String orderId) {
        return String.format("%s%s", RedisPrefix.ORDER_SELL_COUNT, orderId);
    }
}
