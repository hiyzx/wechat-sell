package com.zero.admin.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/12/20
 */
@Data
public class ProductDto {

    @ApiModelProperty(value = "商品id")
    private String uid;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "简介")
    private String info;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "小图")
    private String icon;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "类目编号")
    private Integer categoryId;
}
