package com.zero.product.service;

import com.github.pagehelper.PageInfo;
import com.zero.product.common.BaseService;
import com.zero.product.po.OrderMaster;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface OrderMasterService extends BaseService<OrderMaster> {

    PageInfo<OrderMaster> findByUserId(Integer buyerId, Integer page, Integer pageSize);
}
