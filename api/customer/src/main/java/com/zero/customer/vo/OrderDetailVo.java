package com.zero.customer.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("订单详情vo对象")
public class OrderDetailVo {

    @ApiModelProperty("商品id")
    private String productId;

    @ApiModelProperty("下单数量")
    private Integer productQuantity;
}
