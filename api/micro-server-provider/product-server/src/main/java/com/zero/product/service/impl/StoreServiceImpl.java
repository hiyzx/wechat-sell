package com.zero.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.Store;
import com.zero.product.dao.StoreMapper;
import com.zero.product.service.StoreService;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public Store findByName(String storeName) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", storeName);
        return this.selectOne(queryWrapper);
    }
}
