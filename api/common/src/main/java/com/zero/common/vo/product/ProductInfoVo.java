package com.zero.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("商品信息vo对象")
public class ProductInfoVo implements Serializable {

    @ApiModelProperty(value = "商品id")
    private String id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "简介")
    private String info;

    @ApiModelProperty(value = "小图")
    private String icon;

    @ApiModelProperty(value = "销量")
    private Integer sellCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;
}