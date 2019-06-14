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
@TableName(value = "store")
public class Store implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "配送方式")
    private Byte deliveryType;

    @ApiModelProperty(value = "服务评分")
    private Double serviceScore;

    @ApiModelProperty(value = "食品评分")
    private Double foodScore;

    @ApiModelProperty(value = "最低价")
    private Integer minPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "多图")
    private String multiPicture;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "加入时间")
    private Date joinTime;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
}