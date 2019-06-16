package com.zero.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yezhaoxing
 * @date 2019/6/14
 */
@ApiModel("减少库存dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDecreaseStockCountDto {

	@ApiModelProperty("产品id")
	private Integer productId;

	@ApiModelProperty("数量")
	private Integer count;
}
