package com.zero.order.vo.message;

import com.zero.order.vo.dto.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/10/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayMessage implements Serializable {

    private Integer userId;

    private List<OrderDetailDto> orderDetailDtos;
}
