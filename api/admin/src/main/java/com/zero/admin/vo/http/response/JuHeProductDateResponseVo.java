package com.zero.admin.vo.http.response;

import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/12/21
 */
@Data
public class JuHeProductDateResponseVo {

    private List<JuHeProductInfoResponseVo> data;

    private String totalNum;

    private Integer pn;

    private Integer rn;
}
