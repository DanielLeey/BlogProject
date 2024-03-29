package com.lee.enums;

/**
 * @Author: admin
 * @Date: 2022/4/2 16:33
 * @Version: 1.0
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    USERNAME_NOT_EXIST(503,"用户不存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    NICKNAME_NOT_NULL(509, "昵称不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    ES_CREATE_FAIL(520, "es创建文档失败"),
    MONGODB_CREATE_FAIL(530,"mongoDB创建文档失败"),
    MONGODB_DELETE_FAIL(531,"mongoDB删除文档失败"),
    COMMENT_NOT_NULL(601, "评论不能为空"),
    FILE_TYPE_ERROR(701, "上传图片格式不为PNG");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
