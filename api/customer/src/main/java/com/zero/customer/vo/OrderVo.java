package com.zero.customer.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Data
@ApiModel("订单vo对象")
public class OrderVo {

    @ApiModelProperty("下单人")
    private String buyerId;

    @ApiModelProperty("姓名")
    private String buyerName;

    @ApiModelProperty("手机号")
    private String buyerPhone;

    @ApiModelProperty("地址")
    private String buyerAddress;

    @ApiModelProperty("订单总金额")
    private Double totalAmount;

    private List<OrderDetailVo> orderDetailDtos;
}
