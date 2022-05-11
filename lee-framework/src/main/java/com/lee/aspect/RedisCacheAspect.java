package com.lee.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: liyansong
 * @Date: 2022/5/11 18:33
 * @Version: 1.0
 * @Description: 缓存切面类
 * 定义切入点（CacheException注解的方法）
 * 定义环绕通知，处理注解方法抛出的redis异常
 */
@Aspect
@Component
@Slf4j
public class RedisCacheAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    //@Pointcut("@annotation(com.lee.annotation.CacheException)")
    public void pt() {}

    //@Around("pt()")
    public Object doRound(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retValue = null;
        try {
             retValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            // CacheException注解的方法，如果redis宕机在此处捕获异常，并抛出
            // throw throwable;
            LOGGER.error(throwable.getMessage());
        }
        return retValue;

    }
}
