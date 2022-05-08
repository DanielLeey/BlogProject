package com.lee.aspect;

import com.alibaba.fastjson.JSON;
import com.lee.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 切面类
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    //切点，使用注解的方法就是切点
    @Pointcut("@annotation(com.lee.annotation.SystemLog)")
    public void pt() {
    }

    //通知方法，增强切点
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //前置处理

        Object ret;
        //目标方法执行
        try {
            handlerBefore(joinPoint);
            ret = joinPoint.proceed();
            handlerAfter(ret);
        } finally {
            //后置处理
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void handlerBefore(ProceedingJoinPoint joinPoint) {
        //获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法上的注解对象，对象用属性
        SystemLog systemLog = getSystemLog(joinPoint);
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURI());
        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法：类名 + 方法名
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringType(), ((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRequestURI());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //注解的方法封装成对象
        Method method = signature.getMethod();
        //获取method上的注解
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        return systemLog;
    }

    private void handlerAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(ret));
    }


}
