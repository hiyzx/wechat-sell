package com.zero.customer.controller;

import com.zero.common.vo.BaseReturnVo;
import com.zero.customer.service.OrderService;
import com.zero.customer.vo.dto.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseReturnVo add(OrderDto orderDto) {
        orderService.add(orderDto);
        return BaseReturnVo.success();
    }
}
