package com.zero.product.service.impl;

import com.zero.product.dao.OrderDetailMapper;
import com.zero.product.po.OrderDetail;
import com.zero.product.service.OrderDetailService;
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
public class OrderDetailServiceImpl extends AbstractService<OrderDetail> implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> findByOrderUid(String orderUid) {
        Condition detailCondition = new Condition(OrderDetail.class);
        detailCondition.createCriteria().andEqualTo("orderUid", orderUid);
        detailCondition.orderBy("createTime").asc();
        return orderDetailMapper.selectByCondition(detailCondition);
    }
}
