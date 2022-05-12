package com.lee.aspect;

import com.lee.annotation.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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

    @Pointcut("execution(public * com.lee.service.*CacheService.*(..))")
    public void pt() {}

    @Around("pt()")
    public Object doRound(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object retValue = null;
        try {
             retValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            // CacheException注解的方法，如果redis宕机在此处捕获异常，并抛出异常给调用方，立即返回
            if (method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            } else {
                methodSignature.getParameterNames();
                method.getParameters();
                LOGGER.error(throwable.getMessage());
            }

        }
        return retValue;

    }
}
