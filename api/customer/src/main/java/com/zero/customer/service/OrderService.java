package com.zero.customer.service;

import com.zero.common.dao.OrderDetailMapper;
import com.zero.common.dao.OrderMasterMapper;
import com.zero.common.dao.ProductInfoMapper;
import com.zero.common.po.OrderDetail;
import com.zero.common.po.OrderMaster;
import com.zero.common.po.ProductInfo;
import com.zero.common.util.DateHelper;
import com.zero.common.util.NumberUtil;
import com.zero.common.util.StringHelper;
import com.zero.customer.vo.dto.OrderDetailDto;
import com.zero.customer.vo.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    public void add(OrderDto orderDto) {
        List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetailDtos();
        String orderId = StringHelper.generateMasterKey();
        Double amount = 0D;
        List<OrderDetail> orderDetailList = new ArrayList<>(orderDetailDtos.size());
        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(StringHelper.generateDetailKey());
            orderDetail.setOrderId(orderId);
            String productInfoId = orderDetailDto.getProductInfoId();
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productInfoId);
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
    }
}
