package com.zero.order.vo.message;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
