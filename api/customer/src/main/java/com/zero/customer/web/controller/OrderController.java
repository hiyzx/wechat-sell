package com.zero.customer.web.controller;

import com.zero.common.exception.BaseException;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.customer.annotation.Authorize;
import com.zero.customer.service.OrderService;
import com.zero.customer.vo.OrderVo;
import com.zero.customer.vo.dto.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@RestController
@RequestMapping("/order")
@Api(description = "订单相关接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Authorize
    @PostMapping("/add")
    @ApiOperation("下单")
    public BaseReturnVo add(OrderDto orderDto) throws BaseException {
        orderService.add(orderDto);
        return BaseReturnVo.success();
    }

    @Authorize
    @GetMapping("/get")
    @ApiOperation("查询单个订单")
    public ReturnVo<OrderVo> get(@ApiParam("订单id") @RequestParam String orderId) throws BaseException {
        return ReturnVo.success(orderService.getByOrderId(orderId));
    }

    @Authorize
    @PostMapping("/cancel")
    @ApiOperation("取消某个订单")
    public BaseReturnVo cancel(@ApiParam("订单人") @RequestParam String openid,
            @ApiParam("订单id") @RequestParam String orderId) throws BaseException {
        orderService.cancel(openid, orderId);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/pay")
    @ApiOperation("支付")
    public BaseReturnVo pay(@ApiParam("订单人") @RequestParam String openid,
            @ApiParam("订单id") @RequestParam String orderId) throws BaseException {
        orderService.pay(openid, orderId);
        return BaseReturnVo.success();
    }
}
