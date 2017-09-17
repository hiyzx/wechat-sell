package com.zero.common.po;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "order_master")
public class OrderMaster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    @ApiModelProperty(value = "买家名字")
    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单状态 0新下单")
    private Byte orderStatus;

    @ApiModelProperty(value = "支付状态,0未支付")
    private Byte payStatus;

    private Date createTime;

    private Date updateTime;
}