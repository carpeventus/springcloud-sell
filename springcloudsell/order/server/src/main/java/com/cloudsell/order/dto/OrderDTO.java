package com.cloudsell.order.dto;

import com.cloudsell.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Haiyu
 * @date 2018/10/17 9:37
 */
@Data
public class OrderDTO {
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态，默认0是新下单 */
    private Integer orderStatus;

    /** 订单支付状态，默认未支付 */
    private Integer payStatus;

    /** 一个订单对应的多个订单详情 */
    private List<OrderDetail> orderDetailList;

}
