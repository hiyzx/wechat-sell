package com.zero.customer.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/09/19
 */
@Data
@ApiModel("订单主表dto对象")
public class OrderDto {

    @ApiModelProperty("姓名")
    private String buyerName;

    @ApiModelProperty("手机号")
    private String buyerPhone;

    @ApiModelProperty("地址")
    private String buyerAddress;

    private List<OrderDetailDto> orderDetailDtos;
}
