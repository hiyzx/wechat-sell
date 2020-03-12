package com.zero.order.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zero.common.exception.BaseException;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.order.annotation.Authorize;
import com.zero.order.annotation.SecurityTag;
import com.zero.order.facade.OrderServerFacade;
import com.zero.order.util.JwtTokenUtil;
import com.zero.order.vo.MyOrderVo;
import com.zero.order.vo.OrderVo;
import com.zero.order.vo.dto.OrderDto;
import com.zero.product.dto.ProductCommentDto;
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
    private OrderServerFacade orderServerFacade;

    @Authorize
    @GetMapping("/list")
    @SecurityTag
    @ApiOperation("我的订单列表")
    public ReturnVo<IPage<MyOrderVo>> list(@RequestParam String sessionId,
        @ApiParam(value = "当前页", required = true) @RequestParam Integer page,
        @ApiParam(value = "每页大小", required = true) @RequestParam Integer pageSize) throws Exception {
        return ReturnVo.success(orderServerFacade.list(JwtTokenUtil.parseUserId(sessionId), page, pageSize));
    }

    @Authorize
    @PostMapping("/add")
    @SecurityTag
    @ApiOperation("下单")
    public ReturnVo<String> add(@RequestParam String sessionId, @RequestBody OrderDto orderDto) throws Exception {
        return ReturnVo.success(orderServerFacade.add(JwtTokenUtil.parseUserId(sessionId), orderDto));
    }

    @Authorize
    @GetMapping("/get")
    @SecurityTag
    @ApiOperation("查询单个订单")
    public ReturnVo<OrderVo> get(@RequestParam String sessionId, @ApiParam("订单id") @RequestParam String orderId)
        throws BaseException {
        return ReturnVo.success(orderServerFacade.getByOrderId(orderId));
    }

    @Authorize
    @PostMapping("/cancel")
    @SecurityTag
    @ApiOperation("取消某个订单")
    public BaseReturnVo cancel(@RequestParam String sessionId, @ApiParam("订单id") @RequestParam String orderId)
        throws Exception {
        orderServerFacade.cancel(JwtTokenUtil.parseUserId(sessionId), orderId);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/pay")
    @SecurityTag
    @ApiOperation("支付")
    public BaseReturnVo pay(@RequestParam String sessionId, @ApiParam("订单id") @RequestParam String orderId)
        throws Exception {
        orderServerFacade.pay(JwtTokenUtil.parseUserId(sessionId), orderId);
        return BaseReturnVo.success();
    }

    @Authorize
    @PostMapping("/comment")
    @SecurityTag
    @ApiOperation("评论商品")
    public BaseReturnVo comment(@RequestParam String sessionId, @RequestBody ProductCommentDto productCommentDto)
        throws Exception {
        orderServerFacade.comment(productCommentDto);
        return BaseReturnVo.success();
    }
}
