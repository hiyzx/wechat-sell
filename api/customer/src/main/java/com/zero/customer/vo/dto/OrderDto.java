package com.zero.customer.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Data
public class OrderDto {

    @ApiModelProperty("下单人")
    private String buyerOpenid;

    @ApiModelProperty("姓名")
    private String buyerName;

    @ApiModelProperty("手机号")
    private String buyerPhone;

    @ApiModelProperty("地址")
    private String buyerAddress;

    private List<OrderDetailDto> orderDetailDtos;
}
