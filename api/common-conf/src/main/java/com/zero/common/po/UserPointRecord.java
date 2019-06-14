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
@TableName(value = "user_point_record")
public class UserPointRecord implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "积分类型")
    private Integer type;

    @ApiModelProperty(value = "获得的积分")
    private Integer gainPoint;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}