package com.lee.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lee.annotation.FlagValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Email;
import java.util.Date;

/**
 * 用户表(User)表实体类
 *
 * @author lee
 * @since 2022-04-03 12:32:54
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {
    //主键@TableId
    private Long id;

    //用户名
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    //昵称
    @NotEmpty(message = "昵称不能为空")
    private String nickName;
    //密码
    @NotEmpty(message = "密码不能为空")
    private String password;
    //用户类型：0代表普通用户，1代表管理员
    private String type;
    //账号状态（0正常 1停用）
    private String status;
    //邮箱
    @NotEmpty(message = "邮箱不能为空")
    @Email
    private String email;
    //手机号
    private String phonenumber;
    //用户性别（0男，1女，2未知）
    @FlagValidator(value = "{'0', '1'}", message = "性别状态不正确")
    private String sex;
    //头像
    private String avatar;
    //创建人的用户id
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;


}
