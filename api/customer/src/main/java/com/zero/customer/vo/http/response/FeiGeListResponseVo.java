package com.zero.customer.vo.http.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Data
@ApiModel("飞鸽返回用户列表vo对象")
public class FeiGeListResponseVo {

    private Integer count;

    private List<FeiGeListUserInfoVo> list;
}
