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
@TableName(value = "user_check_count")
public class UserCheckCount implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联的用户id")
    private Integer userId;

    @ApiModelProperty(value = "签到时间")
    private Date checkTime;

    @ApiModelProperty(value = "连续签到天数")
    private Integer continueCount;

    @ApiModelProperty(value = "最长连续签到天数")
    private Integer maxCount;

    @ApiModelProperty(value = "签到历史记录")
    private Long history;

    @ApiModelProperty(value = "总的签到天数")
    private Integer sum;
}