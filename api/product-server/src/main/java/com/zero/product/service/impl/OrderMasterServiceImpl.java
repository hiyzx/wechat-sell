package com.zero.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.product.dao.OrderMasterMapper;
import com.zero.product.po.OrderMaster;
import com.zero.product.service.OrderMasterService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class OrderMasterServiceImpl extends AbstractService<OrderMaster> implements OrderMasterService {
    @Resource
    private OrderMasterMapper orderMasterMapper;

    @Override
    public PageInfo<OrderMaster> findByUserId(Integer buyerId, Integer page, Integer pageSize) {
        Condition masterCondition = new Condition(OrderMaster.class);
        masterCondition.createCriteria().andEqualTo("buyerId", buyerId);
        masterCondition.orderBy("createTime").desc();
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(orderMasterMapper.selectByCondition(masterCondition));
    }
}
