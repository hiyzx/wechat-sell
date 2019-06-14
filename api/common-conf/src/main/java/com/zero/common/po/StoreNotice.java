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
@TableName(value = "store_notice")
public class StoreNotice implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "店铺id")
    private Integer storeId;

    @ApiModelProperty(value = "内容")
    private String content;

    private Date createTime;

    private Integer isDelete;
}