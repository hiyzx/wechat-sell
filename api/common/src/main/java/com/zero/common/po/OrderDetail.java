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
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String detailId;

    private String orderId;

    private String productId;

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
}