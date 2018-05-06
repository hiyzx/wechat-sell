package com.zero.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/09/18
 */
@Data
@ApiModel("商品类目vo对象")
public class ProductCategoryVo {

    @ApiModelProperty(value = "类目id")
    private Integer id;

    @ApiModelProperty(value = "类目名称")
    private String name;

    @ApiModelProperty(value = "展示顺序")
    private Integer showIndex;
}
