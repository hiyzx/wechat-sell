package com.zero.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName(value = "order_master")
public class OrderMaster implements Serializable {

    public static final int ORDER_STATUS_NEW = 0;
    public static final int ORDER_STATUS_FINISHED = 1;
    public static final int ORDER_STATUS_CANCEL = 2;
    public static final int PAY_STATUS_NOT = 0;
    public static final int PAY_STATUS_SUCCESS = 1;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String uid;

    @ApiModelProperty(value = "买家名字")
    private String buyerName;

    @ApiModelProperty(value = "买家电话")
    private String buyerPhone;

    @ApiModelProperty(value = "买家地址")
    private String buyerAddress;

    @ApiModelProperty(value = "买家唯一id")
    private Integer buyerId;

    @ApiModelProperty(value = "总数量")
    private Integer totalCount;

    @ApiModelProperty(value = "订单总金额")
    private Double totalAmount;

    @ApiModelProperty(value = "订单状态 0新下单")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态,0未支付")
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
}