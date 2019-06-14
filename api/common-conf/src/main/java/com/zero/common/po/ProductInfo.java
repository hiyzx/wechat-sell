package com.zero.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName(value = "product_info")
public class ProductInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private String uid;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "销量")
    private Integer sellCount;

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

    @ApiModelProperty(value = "总的评分")
    private Integer totalScore;

    @ApiModelProperty(value = "平均分")
    private Double averageScore;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
}