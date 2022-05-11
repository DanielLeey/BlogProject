package com.lee.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liyansong
 * @Date: 2022/5/11 18:31
 * @Version: 1.0
 * @Description: Redis缓存异常切面注解
 * 注解在redis宕机需要捕获异常的方法上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CacheException {
}
