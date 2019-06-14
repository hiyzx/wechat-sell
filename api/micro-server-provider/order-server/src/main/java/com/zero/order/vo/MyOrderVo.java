package com.zero.order.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/10/25
 */
@ApiModel("我的订单vo对象")
@Data
public class MyOrderVo {

    private String orderId;

    @ApiModelProperty("下单时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;

    @ApiModelProperty("第一个商品图片")
    private String productImage;

    @ApiModelProperty("第一个商品名称")
    private String productName;

    @ApiModelProperty("商品总数")
    private Integer totalCount;

    @ApiModelProperty("总金额")
    private Double totalAmount;

    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    @ApiModelProperty("支付状态")
    private Integer payStatus;

    public String getOrderStatusDisplay() {
        String rtn;
        if (orderStatus == 0 && payStatus == 0) {
            rtn = "未支付";
        } else if (orderStatus == 0 && payStatus == 1) {
            rtn = "已付款";
        } else if (orderStatus == 1) {
            rtn = "已完成";
        } else if (orderStatus == 2) {
            rtn = "已取消";
        } else {
            rtn = null;
        }
        return rtn;
    }
}
