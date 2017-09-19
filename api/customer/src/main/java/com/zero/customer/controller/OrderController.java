package com.zero.customer.controller;

import com.zero.common.exception.BaseException;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
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

    @PostMapping("/add")
    @ApiOperation("下单")
    public BaseReturnVo add(OrderDto orderDto) throws BaseException {
        orderService.add(orderDto);
        return BaseReturnVo.success();
    }

    @GetMapping("/get")
    @ApiOperation("查询单个订单")
    public ReturnVo<OrderVo> get(@ApiParam("订单id") @RequestParam Integer orderId) throws BaseException {
        return ReturnVo.success(orderService.getByOrderId(orderId));
    }
}
