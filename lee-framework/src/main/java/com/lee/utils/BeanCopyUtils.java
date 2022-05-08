package com.lee.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: admin
 * @Date: 2022/4/2 17:01
 * @Version: 1.0
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {}

    public static <T> T copyBean(Object source, Class<T> clazz) {
        //创建目标对象
        T result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
        }
        return result;
    }

    public static <S,T>  List<T> copyBeanList(List<S> list, Class<T> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
