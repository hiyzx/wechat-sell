package com.zero.common.po;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "product_info")
public class ProductInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "商品id")
    private String id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "库存")
    private Integer sellCount;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "小图")
    private String icon;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "类目编号")
    private Integer categoryId;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Byte isDelete;
}