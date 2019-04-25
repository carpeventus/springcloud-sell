package com.cloudsell.order.enums;

import lombok.Getter;

/**
 * @author Haiyu
 * @date 2018/10/17 9:57
 */
@Getter
public enum ResultEnum implements CodeEnum{
    /** 创建订单时前台传过来的参数不正确 */
    SUCCESS(0, "成功"),
    ORDER_PARAM_ERROR(1, "订单参数不正确"),
    ORDER_CART_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXIST(3, "订单不存在"),
    ORDER_STATUS_ERROR(4,"订单状态不正确"),
    ORDER_DETAIL_EMPTY(5,"订单详情为空")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
