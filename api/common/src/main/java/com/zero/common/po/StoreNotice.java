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

    private Byte isDelete;
}