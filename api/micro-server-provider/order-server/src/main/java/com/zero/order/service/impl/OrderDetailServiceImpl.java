package com.zero.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.OrderDetail;
import com.zero.order.dao.OrderDetailMapper;
import com.zero.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Override
    public List<OrderDetail> findByOrderUid(String orderUid) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_uid", orderUid).orderByAsc("create_time");
        return this.selectList(queryWrapper);
    }
}
