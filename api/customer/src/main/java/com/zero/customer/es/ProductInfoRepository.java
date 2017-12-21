package com.zero.customer.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zero.common.vo.dto.ProductInfoEsDto;

public interface ProductInfoRepository extends ElasticsearchRepository<ProductInfoEsDto, Integer> {

}