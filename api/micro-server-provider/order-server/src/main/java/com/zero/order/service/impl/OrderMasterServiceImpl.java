package com.zero.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.OrderMaster;
import com.zero.order.dao.OrderMasterMapper;
import com.zero.order.service.OrderMasterService;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {

    @Override
    public IPage<OrderMaster> findByUserId(Integer buyerId, Integer page, Integer pageSize) {
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_id", buyerId).orderByAsc("create_time");
        return this.selectPage(new Page<>(page, pageSize), queryWrapper);
    }

    @Override
    public OrderMaster findBuUid(String orderId) {
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", orderId);
        return this.selectOne(queryWrapper);
    }

}
