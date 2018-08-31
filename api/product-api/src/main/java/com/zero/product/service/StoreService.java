package com.zero.product.service;

import com.zero.product.common.BaseService;
import com.zero.product.po.Store;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface StoreService extends BaseService<Store> {

    Store findByName(String storeName);
}
