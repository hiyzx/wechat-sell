package com.zero.product.service.impl;

import com.zero.product.dao.StoreNoticeMapper;
import com.zero.product.po.StoreNotice;
import com.zero.product.service.StoreNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author zero
 * @date 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class StoreNoticeServiceImpl extends AbstractService<StoreNotice> implements StoreNoticeService {
    @Resource
    private StoreNoticeMapper storeNoticeMapper;

}
