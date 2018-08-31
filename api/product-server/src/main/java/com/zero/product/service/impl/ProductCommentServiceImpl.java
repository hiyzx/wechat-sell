package com.zero.product.service.impl;

import com.zero.product.dao.ProductCommentMapper;
import com.zero.product.po.ProductComment;
import com.zero.product.service.ProductCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author zero
 * @date 2018/08/30
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class ProductCommentServiceImpl extends AbstractService<ProductComment> implements ProductCommentService {
    @Resource
    private ProductCommentMapper productCommentMapper;

}
