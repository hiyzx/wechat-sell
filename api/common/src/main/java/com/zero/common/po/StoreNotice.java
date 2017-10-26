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
@Table(name = "store_notice")
public class StoreNotice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "店铺id")
    private Integer storeId;

    @ApiModelProperty(value = "内容")
    private String content;

    private Date createTime;

    private Boolean isDelete;
}