package com.cloudsell.product.enums;

import lombok.Getter;

/**
 * @author Haiyu
 * @date 2018/10/17 9:57
 */
@Getter
public enum ResultEnum implements CodeEnum{
    /** 创建订单时前台传过来的参数不正确 */
    SUCCESS(0, "成功"),
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "商品库存不足"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
