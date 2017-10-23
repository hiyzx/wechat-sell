package com.zero.customer.service;

import com.zero.common.dao.StoreMapper;
import com.zero.common.po.Store;
import com.zero.customer.vo.StoreInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/10/23
 */
@Service
@Slf4j
public class StoreService {

    @Resource
    private StoreMapper storeMapper;

    public StoreInfoVo getStoreInfo() {
        Condition condition = new Condition(Store.class);
        List<Store> stores = storeMapper.selectByExample(condition);
        Store store = stores.get(0);
        StoreInfoVo rtn = new StoreInfoVo();
        BeanUtils.copyProperties(store, rtn);
        return rtn;
    }
}
