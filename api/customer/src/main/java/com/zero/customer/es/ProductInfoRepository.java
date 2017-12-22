package com.zero.customer.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zero.common.vo.dto.ProductInfoEsDto;

public interface ProductInfoRepository extends ElasticsearchRepository<ProductInfoEsDto, Integer> {

    Page<ProductInfoEsDto> findByNameLikeOrInfoLikeOrDescriptionLike(String name, String info, String desc,
            Pageable pageable);
}