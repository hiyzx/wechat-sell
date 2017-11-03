package com.zero.customer.web.controller;

import com.github.pagehelper.PageInfo;
import com.zero.common.exception.BaseException;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.customer.annotation.Authorize;
import com.zero.customer.service.OrderService;
import com.zero.customer.util.SessionHelper;
import com.zero.customer.vo.MyOrderVo;
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
    @Resource
    private SessionHelper sessionHelper;

    @Authorize
    @PostMapping("/list.json")
    @ApiOperation("我的订单列表")
    public ReturnVo<PageInfo<MyOrderVo>> list(@RequestParam String sessionId,
            @ApiParam(value = "当前页", required = true) @RequestParam Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam Integer pageSize) throws BaseException {
        return ReturnVo.success(orderService.list(sessionHelper.getUserId(sessionId), page, pageSize));
    }

    @Authorize
    @PostMapping("/add.json")
    @ApiOperation("下单")
    public ReturnVo<String> add(@RequestParam String sessionId, @RequestBody OrderDto orderDto) throws BaseException {
        return ReturnVo.success(orderService.add(sessionHelper.getUserId(sessionId), orderDto));
    }

    @Authorize
    @GetMapping("/get.json")
    @ApiOperation("查询单个订单")
    public ReturnVo<OrderVo> get(@RequestParam String sessionId, @ApiParam("订单id") @RequestParam String orderId)
            throws BaseException {
        return ReturnVo.success(orderService.getByOrderId(orderId));
    }

    @Authorize
    @PostMapping("/cancel.json")
    @ApiOperation("取消某个订单")
    public BaseReturnVo cancel(@ApiParam("订单人") @RequestParam String sessionId,
            @ApiParam("订单id") @RequestParam String orderId) throws BaseException {
        orderService.cancel(sessionHelper.getUserId(sessionId), orderId);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/pay.json")
    @ApiOperation("支付")
    public BaseReturnVo pay(@ApiParam("订单人") @RequestParam String sessionId,
            @ApiParam("订单id") @RequestParam String orderId) throws BaseException {
        orderService.pay(sessionHelper.getUserId(sessionId), orderId);
        return BaseReturnVo.success();
    }
}
