package com.cloudsell.product.exception;

import com.cloudsell.product.enums.ResultEnum;

/**
 * @author Haiyu
 * @date 2019/3/19 10:20
 */
public class ProductException extends RuntimeException {
    private Integer code;
    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
