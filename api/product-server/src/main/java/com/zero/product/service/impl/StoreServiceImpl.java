package com.zero.product.service.impl;

import com.zero.product.dao.StoreMapper;
import com.zero.product.po.Store;
import com.zero.product.service.StoreService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author zero
 * @date 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class StoreServiceImpl extends AbstractService<Store> implements StoreService {
    @Resource
    private StoreMapper storeMapper;

    @Override
    public Store findByName(String storeName) {
        Condition condition = new Condition(Store.class);
        condition.createCriteria().andEqualTo("name", storeName);
        List<Store> stores = storeMapper.selectByExample(condition);
        return stores.isEmpty() ? null : stores.get(0);
    }
}
