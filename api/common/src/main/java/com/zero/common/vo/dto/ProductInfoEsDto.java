package com.zero.common.vo.dto;

import com.zero.common.po.ProductInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(indexName = "takeaway", type = "product", shards = 2, replicas = 1, refreshInterval = "-1")
@Data
@ToString
public class ProductInfoEsDto implements Serializable {

    @Id
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private String uid;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "销量")
    private Integer sellCount;

    @ApiModelProperty(value = "简介")
    private String info;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "小图")
    private String icon;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "类目编号")
    private Integer categoryId;

    @ApiModelProperty(value = "总的评分")
    private Integer totalScore;

    @ApiModelProperty(value = "平均分")
    private Double averageScore;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    public static ProductInfoEsDto createEsDto(ProductInfo productInfo) {
        ProductInfoEsDto tmp = new ProductInfoEsDto();
        BeanUtils.copyProperties(productInfo, tmp);
        return tmp;
    }
}