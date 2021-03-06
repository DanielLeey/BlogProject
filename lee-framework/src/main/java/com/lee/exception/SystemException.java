package com.lee.exception;

import com.lee.enums.AppHttpCodeEnum;

/**
 * 自定义异常类，抛出对应的异常常量，在全局异常处理器中捕获返回ResponseResult
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
