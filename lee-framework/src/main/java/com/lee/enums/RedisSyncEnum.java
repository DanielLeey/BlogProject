package com.lee.enums;

/**
 * @author Lee
 */

public enum RedisSyncEnum {
    // 浏览量
    ViewCount(100, "viewCount"),
    // 评论数
    CommentCount(101,"commentCount"),
    // 点赞数
    LikeCount(102, "likeCount"),
    // 收藏数
    collectCount(103, "collectCount");

    int code;
    String syncProperty;

    RedisSyncEnum(int code, String syncProperty) {
        this.code = code;
        this.syncProperty = syncProperty;
    }

    public int getCode() {
        return code;
    }

    public String getSyncProperty() {
        return syncProperty;
    }
}
