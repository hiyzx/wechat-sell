package com.zero.customer.web.controller;

import com.github.pagehelper.PageInfo;
import com.zero.common.exception.BaseException;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.customer.annotation.Authorize;
import com.zero.customer.annotation.SecurityTag;
import com.zero.customer.service.OrderService;
import com.zero.customer.service.ProductInfoService;
import com.zero.customer.util.JwtTokenUtil;
import com.zero.customer.vo.MyOrderVo;
import com.zero.customer.vo.OrderVo;
import com.zero.customer.vo.dto.OrderDto;
import com.zero.customer.vo.dto.ProductCommentDto;
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
    private ProductInfoService productInfoService;

    @Authorize
    @GetMapping("/list.json")
    @SecurityTag
    @ApiOperation("我的订单列表")
    public ReturnVo<PageInfo<MyOrderVo>> list(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization, @ApiParam(value = "当前页", required = true) @RequestParam Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam Integer pageSize) throws Exception {
        return ReturnVo.success(orderService.list(JwtTokenUtil.parseUserId(sessionId), page, pageSize));
    }

    @Authorize
    @PostMapping("/add.json")
    @SecurityTag
    @ApiOperation("下单")
    public ReturnVo<String> add(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization, @RequestBody OrderDto orderDto) throws Exception {
        return ReturnVo.success(orderService.add(JwtTokenUtil.parseUserId(sessionId), orderDto));
    }

    @Authorize
    @GetMapping("/get.json")
    @SecurityTag
    @ApiOperation("查询单个订单")
    public ReturnVo<OrderVo> get(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization, @ApiParam("订单id") @RequestParam String orderId) throws BaseException {
        return ReturnVo.success(orderService.getByOrderId(orderId));
    }

    @Authorize
    @PostMapping("/cancel.json")
    @SecurityTag
    @ApiOperation("取消某个订单")
    public BaseReturnVo cancel(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization, @ApiParam("订单id") @RequestParam String orderId) throws Exception {
        orderService.cancel(JwtTokenUtil.parseUserId(sessionId), orderId);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/pay.json")
    @SecurityTag
    @ApiOperation("支付")
    public BaseReturnVo pay(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization, @ApiParam("订单id") @RequestParam String orderId) throws Exception {
        orderService.pay(JwtTokenUtil.parseUserId(sessionId), orderId);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/comment.json")
    @SecurityTag
    @ApiOperation("评论商品")
    public BaseReturnVo comment(@RequestParam String sessionId, @RequestParam Long timestamp,
            @RequestParam String authorization, @RequestBody ProductCommentDto productCommentDto) throws Exception {
        productInfoService.comment(JwtTokenUtil.parseUserId(sessionId), productCommentDto);
        return BaseReturnVo.success();
    }
}
