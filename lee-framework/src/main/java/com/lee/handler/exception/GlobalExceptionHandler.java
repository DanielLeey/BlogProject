package com.lee.handler.exception;

import com.lee.domain.ResponseResult;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * controller注入容器，增强器，在响应体中
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //处理系统自定义异常
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        //记录日志
        log.error("出现异常！{}", e);
        //返回给前端
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    //处理其他异常
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        //记录日志
        log.error("出现异常！{}", e);
        //返回给前端
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseResult validationExceptionHandler(MethodArgumentNotValidException e) {
        return handleBindingResult(e);
    }

    @ExceptionHandler({BindException.class})
    public ResponseResult handleValidException(BindException  e) {
        return handleBindingResult(e);
    }

    private ResponseResult handleBindingResult(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                String defaultMessage = fieldError.getDefaultMessage();
                message = fieldError.getField() + fieldError.getDefaultMessage();
                for(AppHttpCodeEnum appHttpCodeEnum : AppHttpCodeEnum.values()) {
                    if (defaultMessage.equals(appHttpCodeEnum.getMsg())) {
                        return ResponseResult.errorResult(appHttpCodeEnum, message);
                    }
                }
            }
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, message);
    }
}
