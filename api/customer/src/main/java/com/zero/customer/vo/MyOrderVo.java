package com.zero.customer.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yezhaoxing
 * @date 2017/10/25
 */
@ApiModel("我的订单vo对象")
@Data
public class MyOrderVo {

    @ApiModelProperty("下单时间")
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
}
