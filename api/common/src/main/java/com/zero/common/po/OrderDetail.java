package com.zero.common.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uid;

    @ApiModelProperty(value = "订单主表uid")
    private String orderUid;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private Double productPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "商品图片")
    private String productIcon;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDelete;
}