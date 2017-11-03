package com.zero.customer.vo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentProductMessage implements Serializable {

    private Integer productId;

    private Integer score;
}
