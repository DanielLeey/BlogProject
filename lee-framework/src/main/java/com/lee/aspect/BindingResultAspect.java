package com.lee.aspect;

import com.lee.domain.ResponseResult;
import com.lee.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


/**
 * @Author: liyansong
 * @Date: 2022/5/15 13:31
 * @Version: 1.0
 * @Description: 前台传入参数使用Validation切面类
 * 注意：在全局异常处理类中处理validation类型异常，此切面类不再使用
 */
//@Component
//@Aspect
@Slf4j
@Order
public class BindingResultAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(BindingResultAspect.class);

    @Pointcut("execution(public * com.lee.controller.*Controller.*(..))")
    public void pt() {

    }
    @Around("pt()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    //getFiledError()只返回第一个错误
                    FieldError fieldError = result.getFieldError();
                    if(fieldError!=null){
                        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, fieldError.getDefaultMessage());
                    }else{
                        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}
