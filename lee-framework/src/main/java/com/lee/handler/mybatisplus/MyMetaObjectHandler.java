package com.lee.handler.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lee.domain.entity.Article;
import com.lee.enums.RedisSyncEnum;
import com.lee.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * 设置 插入更新时 自动填充字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//表示是自己创建
        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //如果Article有更新则更新
        //this.setFieldValByName("updateTime", new Date(), metaObject);
        //this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
        //严格更新，只有更新这些字段时才调用
        this.strictUpdateFill(metaObject, "updateTime", () -> new Date(), Date.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> Optional.ofNullable(SecurityUtils.getUserId()).orElse(null), Long.class);
    }
}
