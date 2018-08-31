package com.zero.product.service;

import com.zero.product.common.BaseService;
import com.zero.product.po.OrderDetail;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface OrderDetailService extends BaseService<OrderDetail> {

    List<OrderDetail> findByOrderUid(String orderUid);
}
