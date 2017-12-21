package com.zero.admin.vo.http.response;

import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/12/21
 */
@Data
public class JuHeProductInfoResponseVo {

    private String id;

    private String title;

    private String tags;

    private String imtro;

    private String ingredients;

    private String burden;

    private List<String> albums;

    private List<JuHeProductStepResponseVo> steps;
}
