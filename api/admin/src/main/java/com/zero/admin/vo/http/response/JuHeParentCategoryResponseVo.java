package com.zero.admin.vo.http.response;

import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/12/21
 */
@Data
public class JuHeParentCategoryResponseVo {

    private String parentId;

    private String name;

    private List<JuHeChildCategoryResponseVo> list;
}
