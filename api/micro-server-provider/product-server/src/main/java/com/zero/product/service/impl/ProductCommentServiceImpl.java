package com.zero.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.po.ProductComment;
import com.zero.product.dao.ProductCommentMapper;
import com.zero.product.service.ProductCommentService;
import org.springframework.stereotype.Service;

/**
 * @author zero
 * @date 2018/08/30
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment>
        implements ProductCommentService {

}
