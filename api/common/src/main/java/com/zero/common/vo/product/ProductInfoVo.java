package com.zero.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("商品信息对象")
public class ProductInfoVo implements Serializable {

    @ApiModelProperty(value = "商品id")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "单价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "描述")
    private String productDescription;

    @ApiModelProperty(value = "小图")
    private String productIcon;
}