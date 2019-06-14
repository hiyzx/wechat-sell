package com.zero.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.OrderMaster;


/**
 * @author zero
 * @date 2018/08/30
 */
public interface OrderMasterService extends IService<OrderMaster> {

    IPage<OrderMaster> findByUserId(Integer buyerId, Integer page, Integer pageSize);

    OrderMaster findBuUid(String orderId);
}
