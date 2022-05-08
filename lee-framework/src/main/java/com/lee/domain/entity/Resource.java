package com.lee.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 后台资源表(SysResource)表实体类
 *
 * @author lee
 * @since 2022-05-08 15:57:02
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_resource")
public class Resource {
    @TableId
    private Long id;

    //创建时间
    private Date createTime;
    //资源名称
    private String name;
    //资源URL
    private String url;
    //描述
    private String description;
    //资源分类ID
    private Long categoryId;



}
