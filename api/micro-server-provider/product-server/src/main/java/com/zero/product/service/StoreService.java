package com.zero.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.po.Store;

/**
 * @author zero
 * @date 2018/08/30
 */
public interface StoreService extends IService<Store> {

    Store findByName(String storeName);
}
