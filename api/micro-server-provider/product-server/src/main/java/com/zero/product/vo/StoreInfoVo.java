package com.zero.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2017/10/23
 */
@ApiModel("商家信息vo对象")
@Data
public class StoreInfoVo {

    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "配送方式")
    private Byte deliveryType;

    public String getDeliveryStr() {
        return deliveryType == 1 ? "蜂鸟配送" : "商家配送";
    }

    public String getShortIntro() {
        return introduction.length() > 10 ? String.format("%s%s",introduction.substring(0, 15),"。。。。") : introduction;
    }
}
