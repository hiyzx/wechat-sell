package com.zero.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.OrderDetail;

import java.util.List;



/**
 * @author zero
 * @date 2018/08/30
 */
public interface OrderDetailService extends IService<OrderDetail> {

    List<OrderDetail> findByOrderUid(String orderUid);
}
