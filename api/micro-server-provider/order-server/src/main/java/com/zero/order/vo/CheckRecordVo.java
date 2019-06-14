package com.zero.order.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@Data
@ApiModel("签到vo")
public class CheckRecordVo {


    @ApiModelProperty("是否已经签到")
    private boolean hasCheck;

    @ApiModelProperty("上次签到时间")
    private Date lastCheckTime;

    @ApiModelProperty("签到历史")
    private String checkHistory;
}
