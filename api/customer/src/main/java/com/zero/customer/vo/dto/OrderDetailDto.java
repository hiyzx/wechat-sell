package com.zero.customer.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Data
@ApiModel("订单从表dto对象")
public class OrderDetailDto {

    @ApiModelProperty("商品id")
    private String productInfoId;

    @ApiModelProperty("下单数量")
    private Integer count;
}
