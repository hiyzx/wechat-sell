package com.zero.product.po;

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
@Table(name = "product_comment")
public class ProductComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "评论人id")
    private Integer userId;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "分数")
    private Integer score;

    @ApiModelProperty(value = "内容")
    private String content;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;
}