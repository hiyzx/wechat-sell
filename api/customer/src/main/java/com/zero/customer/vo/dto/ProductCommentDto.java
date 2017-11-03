package com.zero.customer.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/11/03
 */
@ApiModel("商品评论dto对象")
@Data
public class ProductCommentDto {

    @ApiModelProperty("得分")
    private Integer score;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty("内容")
    private String content;

}
