package com.cloudsell.order.exception;

import com.cloudsell.order.enums.ResultEnum;

/**
 * @author Haiyu
 * @date 2019/3/16 15:10
 */
public class OrderException extends RuntimeException{
    private Integer code;
    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
