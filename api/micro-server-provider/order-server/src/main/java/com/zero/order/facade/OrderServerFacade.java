package com.zero.order.facade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zero.common.exception.BaseException;
import com.zero.order.vo.MyOrderVo;
import com.zero.order.vo.OrderVo;
import com.zero.order.vo.dto.OrderDto;

/**
 * @author yezhaoxing
 * @date 2019/6/15
 */
public interface OrderServerFacade {

	/**
	 * @author yezhaoxing
	 * @date 2019/6/15
	 * @description 查询所有的订单
	 */
    IPage<MyOrderVo> list(Integer userId, Integer page, Integer pageSize);

	/**
	 * @author yezhaoxing
	 * @date 2019/6/15
	 * @description 下单
	 */
    String add(Integer userId, OrderDto orderDto) throws BaseException;

	/**
	 * @author yezhaoxing
	 * @date 2019/6/15
	 * @description 获取单个订单
	 */
    OrderVo getByOrderId(String orderId) throws BaseException;

	/**
	 * @author yezhaoxing
	 * @date 2019/6/15
	 * @description 取消
	 */
    void cancel(Integer userId, String orderId) throws BaseException;

	/**
	 * @author yezhaoxing
	 * @date 2019/6/15
	 * @description 支付
	 */
    void pay(Integer userId, String orderId) throws BaseException;
}
