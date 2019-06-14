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
@TableName(value = "product_category")
public class ProductCategory implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类目名称")
    private String name;

    @ApiModelProperty(value = "展示顺序")
    private Integer showIndex;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
}