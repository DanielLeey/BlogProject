/*Table structure for table `sg_link` */

DROP TABLE IF EXISTS `sg_link`;

CREATE TABLE `sg_link` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `name` varchar(256) DEFAULT NULL,
   `logo` varchar(256) DEFAULT NULL,
   `description` varchar(512) DEFAULT NULL,
   `address` varchar(128) DEFAULT NULL COMMENT '网站地址',
   `status` char(1) DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
   `create_by` bigint(20) DEFAULT NULL,
   `create_time` datetime DEFAULT NULL,
   `update_by` bigint(20) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='友链';

/*Data for the table `sg_link` */

insert  into `sg_link`(`id`,`name`,`logo`,`description`,`address`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'sda','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','sda','https://www.baidu.com','1',NULL,'2022-01-13 08:25:47',NULL,'2022-01-13 08:36:14',0),(2,'sda','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','dada','https://www.qq.com','1',NULL,'2022-01-13 09:06:10',NULL,'2022-01-13 09:07:09',0),(3,'sa','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','da','https://www.taobao.com','1',NULL,'2022-01-13 09:23:01',NULL,'2022-01-13 09:23:01',0);



/*Table structure for table `sg_comment` */

DROP TABLE IF EXISTS `sg_comment`;

CREATE TABLE `sg_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `root_id` bigint(20) DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint(20) DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint(20) DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

/*Data for the table `sg_comment` */

insert  into `sg_comment`(`id`,`type`,`article_id`,`root_id`,`content`,`to_comment_user_id`,`to_comment_id`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'0',1,-1,'asS',-1,-1,1,'2022-01-29 07:59:22',1,'2022-01-29 07:59:22',0),(2,'0',1,-1,'[哈哈]SDAS',-1,-1,1,'2022-01-29 08:01:24',1,'2022-01-29 08:01:24',0),(3,'0',1,-1,'是大多数',-1,-1,1,'2022-01-29 16:07:24',1,'2022-01-29 16:07:24',0),(4,'0',1,-1,'撒大声地',-1,-1,1,'2022-01-29 16:12:09',1,'2022-01-29 16:12:09',0),(5,'0',1,-1,'你再说什么',-1,-1,1,'2022-01-29 18:19:56',1,'2022-01-29 18:19:56',0),(6,'0',1,-1,'hffd',-1,-1,1,'2022-01-29 22:13:52',1,'2022-01-29 22:13:52',0),(9,'0',1,2,'你说什么',1,2,1,'2022-01-29 22:18:40',1,'2022-01-29 22:18:40',0),(10,'0',1,2,'哈哈哈哈[哈哈]',1,9,1,'2022-01-29 22:29:15',1,'2022-01-29 22:29:15',0),(11,'0',1,2,'we全文',1,10,3,'2022-01-29 22:29:55',1,'2022-01-29 22:29:55',0),(12,'0',1,-1,'王企鹅',-1,-1,1,'2022-01-29 22:30:20',1,'2022-01-29 22:30:20',0),(13,'0',1,-1,'什么阿是',-1,-1,1,'2022-01-29 22:30:56',1,'2022-01-29 22:30:56',0),(14,'0',1,-1,'新平顶山',-1,-1,1,'2022-01-29 22:32:51',1,'2022-01-29 22:32:51',0),(15,'0',1,-1,'2222',-1,-1,1,'2022-01-29 22:34:38',1,'2022-01-29 22:34:38',0),(16,'0',1,2,'3333',1,11,1,'2022-01-29 22:34:47',1,'2022-01-29 22:34:47',0),(17,'0',1,2,'回复weqedadsd',3,11,1,'2022-01-29 22:38:00',1,'2022-01-29 22:38:00',0),(18,'0',1,-1,'sdasd',-1,-1,1,'2022-01-29 23:18:19',1,'2022-01-29 23:18:19',0),(19,'0',1,-1,'111',-1,-1,1,'2022-01-29 23:22:23',1,'2022-01-29 23:22:23',0),(20,'0',1,1,'你说啥？',1,1,1,'2022-01-30 10:06:21',1,'2022-01-30 10:06:21',0),(21,'0',1,-1,'友链添加个呗',-1,-1,1,'2022-01-30 10:06:50',1,'2022-01-30 10:06:50',0),(22,'1',1,-1,'友链评论2',-1,-1,1,'2022-01-30 10:08:28',1,'2022-01-30 10:08:28',0),(23,'1',1,22,'回复友链评论3',1,22,1,'2022-01-30 10:08:50',1,'2022-01-30 10:08:50',0),(24,'1',1,-1,'友链评论4444',-1,-1,1,'2022-01-30 10:09:03',1,'2022-01-30 10:09:03',0),(25,'1',1,22,'收到的',1,22,1,'2022-01-30 10:13:28',1,'2022-01-30 10:13:28',0),(26,'0',1,-1,'sda',-1,-1,1,'2022-01-30 10:39:05',1,'2022-01-30 10:39:05',0),(27,'0',1,1,'说你咋地',1,20,14787164048662,'2022-01-30 17:19:30',14787164048662,'2022-01-30 17:19:30',0),(28,'0',1,1,'sdad',1,1,14787164048662,'2022-01-31 11:11:20',14787164048662,'2022-01-31 11:11:20',0),(29,'0',1,-1,'你说是的ad',-1,-1,14787164048662,'2022-01-31 14:10:11',14787164048662,'2022-01-31 14:10:11',0),(30,'0',1,1,'撒大声地',1,1,14787164048662,'2022-01-31 20:19:18',14787164048662,'2022-01-31 20:19:18',0);



/*Table structure for table `sg_category` */

DROP TABLE IF EXISTS `sg_category`;

CREATE TABLE `sg_category` (
   `id` bigint(200) NOT NULL AUTO_INCREMENT,
   `name` varchar(128) DEFAULT NULL COMMENT '分类名',
   `pid` bigint(200) DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
   `description` varchar(512) DEFAULT NULL COMMENT '描述',
   `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
   `create_by` bigint(200) DEFAULT NULL,
   `create_time` datetime DEFAULT NULL,
   `update_by` bigint(200) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

/*Data for the table `sg_category` */

insert  into `sg_category`(`id`,`name`,`pid`,`description`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'java',-1,'wsd','0',NULL,NULL,NULL,NULL,0),(2,'PHP',-1,'wsd','0',NULL,NULL,NULL,NULL,0);



/*Table structure for table `sg_article` */

DROP TABLE IF EXISTS `sg_article`;

CREATE TABLE `sg_article` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint(20) DEFAULT NULL COMMENT '所属分类id',
  `article_tags` varchar(50) DEFAULT NULL COMMENT '标签',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint(200) DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

/*Data for the table `sg_article` */

insert  into `sg_article`(`id`,`title`,`content`,`summary`,`category_id`,`thumbnail`,`is_top`,`status`,`view_count`,`is_comment`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'SpringSecurity从入门到精通','## 课程介绍\n![image20211219121555979.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/e7131718e9e64faeaf3fe16404186eb4.png)\n\n## 0. 简介1\n\n​	**Spring Security** 是 Spring 家族中的一个安全管理框架。相比与另外一个安全框架**Shiro**，它提供了更丰富的功能，社区资源也比Shiro丰富。\n\n​	一般来说中大型的项目都是使用**SpringSecurity** 来做安全框架。小项目有Shiro的比较多，因为相比与SpringSecurity，Shiro的上手更加的简单。\n\n​	 一般Web应用的需要进行**认证**和**授权**。\n\n​		**认证：验证当前访问系统的是不是本系统的用户，并且要确认具体是哪个用户**\n\n​		**授权：经过认证后判断当前用户是否有权限进行某个操作**\n\n​	而认证和授权也是SpringSecurity作为安全框架的核心功能。\n\n\n\n## 1. 快速入门\n\n### 1.1 准备工作\n\n​	我们先要搭建一个简单的SpringBoot工程\n\n① 设置父工程 添加依赖\n\n~~~~\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.5.0</version>\n    </parent>\n    <dependencies>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <optional>true</optional>\n        </dependency>\n    </dependencies>\n~~~~\n\n② 创建启动类\n\n~~~~\n@SpringBootApplication\npublic class SecurityApplication {\n\n    public static void main(String[] args) {\n        SpringApplication.run(SecurityApplication.class,args);\n    }\n}\n\n~~~~\n\n③ 创建Controller\n\n~~~~java\n\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\npublic class HelloController {\n\n    @RequestMapping(\"/hello\")\n    public String hello(){\n        return \"hello\";\n    }\n}\n\n~~~~\n\n\n\n### 1.2 引入SpringSecurity\n\n​	在SpringBoot项目中使用SpringSecurity我们只需要引入依赖即可实现入门案例。\n\n~~~~xml\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-security</artifactId>\n        </dependency>\n~~~~\n\n​	引入依赖后我们在尝试去访问之前的接口就会自动跳转到一个SpringSecurity的默认登陆页面，默认用户名是user,密码会输出在控制台。\n\n​	必须登陆之后才能对接口进行访问。\n\n\n\n## 2. 认证\n\n### 2.1 登陆校验流程\n![image20211215094003288.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/414a87eeed344828b5b00ffa80178958.png)','SpringSecurity框架教程-Spring Security+JWT实现项目级前端分离认证授权',1,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/948597e164614902ab1662ba8452e106.png','1','0',105,'0',NULL,'2022-01-23 23:20:11',NULL,NULL,0),(2,'weq','adadaeqe','adad',2,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/fd2e9460c58a4af3bbeae5d9ed581688.png','1','0',22,'0',NULL,'2022-01-21 14:58:30',NULL,NULL,1),(3,'dad','asdasda','sadad',1,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/737a0ed0b8ea430d8700a12e76aa1cd1.png','1','0',33,'0',NULL,'2022-01-18 14:58:34',NULL,NULL,1),(5,'sdad','![Snipaste_20220115_165812.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/1d9d283f5d874b468078b183e4b98b71.png)\r\n\r\n## sda \r\n\r\n222\r\n### sdasd newnewnew',NULL,2,'','1','0',44,'0',NULL,'2022-01-17 14:58:37',NULL,NULL,0);



/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
    `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
    `type` char(1) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
    `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
    `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
    `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
    `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
    `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的用户id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`nick_name`,`password`,`type`,`status`,`email`,`phonenumber`,`sex`,`avatar`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'sg','sg333','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','23412332@qq.com','18888888888','1','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F3bf9c263bc0f2ac5c3a7feb9e218d07475573ec8.gi',NULL,'2022-01-05 09:01:56',1,'2022-01-30 15:37:03',0),(3,'sg3','weqe','$2a$10$ydv3rLkteFnRx9xelQ7elOiVhFvXOooA98xCqk/omh7G94R.K/E3O','1','0',NULL,NULL,'0',NULL,NULL,'2022-01-05 13:28:43',NULL,'2022-01-05 13:28:43',0),(4,'sg2','dsadd','$2a$10$kY4T3SN7i4muBccZppd2OOkhxMN6yt8tND1sF89hXOaFylhY2T3he','1','0','23412332@qq.com','19098790742','0',NULL,NULL,NULL,NULL,NULL,0),(5,'sg2233','tteqe','','1','0',NULL,'18246845873','1',NULL,NULL,'2022-01-06 03:51:13',NULL,'2022-01-06 07:00:50',0),(6,'sangeng','sangeng','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','2312321','17777777777','0',NULL,NULL,'2022-01-16 06:54:26',NULL,'2022-01-16 07:06:34',0),(14787164048662,'weixin','weixin','$2a$10$y3k3fnMZsBNihsVLXWfI8uMNueVXBI08k.LzWYaKsW8CW7xXy18wC','0','0','weixin@qq.com',NULL,NULL,NULL,-1,'2022-01-30 17:18:44',-1,'2022-01-30 17:18:44',0);





-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
INSERT INTO `sys_user_role_relation` VALUES ('26', '3', '5');
INSERT INTO `sys_user_role_relation` VALUES ('27', '6', '1');
INSERT INTO `sys_user_role_relation` VALUES ('28', '7', '2');
INSERT INTO `sys_user_role_relation` VALUES ('29', '1', '5');
INSERT INTO `sys_user_role_relation` VALUES ('30', '4', '5');

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `user_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '商品管理员', '只能查看及操作商品', '0', '2020-02-03 16:50:37', '1', '0');
INSERT INTO `sys_role` VALUES ('2', '订单管理员', '只能查看及操作订单', '0', '2018-09-30 15:53:45', '1', '0');
INSERT INTO `sys_role` VALUES ('5', '超级管理员', '拥有所有查看和操作功能', '0', '2020-02-02 15:11:05', '1', '0');

-- ----------------------------
-- Table structure for sys_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource_relation`;
CREATE TABLE `sys_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

-- ----------------------------
-- Records of sys_role_resource_relation
-- ----------------------------
INSERT INTO `sys_role_resource_relation` VALUES ('103', '2', '8');
INSERT INTO `sys_role_resource_relation` VALUES ('104', '2', '9');
INSERT INTO `sys_role_resource_relation` VALUES ('105', '2', '10');
INSERT INTO `sys_role_resource_relation` VALUES ('106', '2', '11');
INSERT INTO `sys_role_resource_relation` VALUES ('107', '2', '12');
INSERT INTO `sys_role_resource_relation` VALUES ('142', '5', '1');
INSERT INTO `sys_role_resource_relation` VALUES ('143', '5', '2');
INSERT INTO `sys_role_resource_relation` VALUES ('144', '5', '3');
INSERT INTO `sys_role_resource_relation` VALUES ('145', '5', '4');
INSERT INTO `sys_role_resource_relation` VALUES ('146', '5', '5');
INSERT INTO `sys_role_resource_relation` VALUES ('147', '5', '6');
INSERT INTO `sys_role_resource_relation` VALUES ('148', '5', '8');
INSERT INTO `sys_role_resource_relation` VALUES ('149', '5', '9');
INSERT INTO `sys_role_resource_relation` VALUES ('150', '5', '10');
INSERT INTO `sys_role_resource_relation` VALUES ('151', '5', '11');
INSERT INTO `sys_role_resource_relation` VALUES ('152', '5', '12');
INSERT INTO `sys_role_resource_relation` VALUES ('153', '5', '13');
INSERT INTO `sys_role_resource_relation` VALUES ('154', '5', '14');
INSERT INTO `sys_role_resource_relation` VALUES ('155', '5', '15');
INSERT INTO `sys_role_resource_relation` VALUES ('156', '5', '16');
INSERT INTO `sys_role_resource_relation` VALUES ('157', '5', '17');
INSERT INTO `sys_role_resource_relation` VALUES ('158', '5', '18');
INSERT INTO `sys_role_resource_relation` VALUES ('159', '5', '19');
INSERT INTO `sys_role_resource_relation` VALUES ('160', '5', '20');
INSERT INTO `sys_role_resource_relation` VALUES ('161', '5', '21');
INSERT INTO `sys_role_resource_relation` VALUES ('162', '5', '22');
INSERT INTO `sys_role_resource_relation` VALUES ('163', '5', '23');
INSERT INTO `sys_role_resource_relation` VALUES ('164', '5', '24');
INSERT INTO `sys_role_resource_relation` VALUES ('165', '5', '25');
INSERT INTO `sys_role_resource_relation` VALUES ('166', '5', '26');
INSERT INTO `sys_role_resource_relation` VALUES ('167', '5', '27');
INSERT INTO `sys_role_resource_relation` VALUES ('168', '5', '28');
INSERT INTO `sys_role_resource_relation` VALUES ('169', '5', '29');
INSERT INTO `sys_role_resource_relation` VALUES ('170', '1', '1');
INSERT INTO `sys_role_resource_relation` VALUES ('171', '1', '2');
INSERT INTO `sys_role_resource_relation` VALUES ('172', '1', '3');
INSERT INTO `sys_role_resource_relation` VALUES ('173', '1', '4');
INSERT INTO `sys_role_resource_relation` VALUES ('174', '1', '5');
INSERT INTO `sys_role_resource_relation` VALUES ('175', '1', '6');
INSERT INTO `sys_role_resource_relation` VALUES ('176', '1', '23');
INSERT INTO `sys_role_resource_relation` VALUES ('177', '1', '24');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '2020-02-04 17:04:55', '商品品牌管理', '/brand/**', null, '1');
INSERT INTO `sys_resource` VALUES ('2', '2020-02-04 17:05:35', '商品属性分类管理', '/productAttribute/**', null, '1');
INSERT INTO `sys_resource` VALUES ('3', '2020-02-04 17:06:13', '商品属性管理', '/productAttribute/**', null, '1');
INSERT INTO `sys_resource` VALUES ('4', '2020-02-04 17:07:15', '商品分类管理', '/productCategory/**', null, '1');
INSERT INTO `sys_resource` VALUES ('5', '2020-02-04 17:09:16', '商品管理', '/product/**', null, '1');
INSERT INTO `sys_resource` VALUES ('6', '2020-02-04 17:09:53', '商品库存管理', '/sku/**', null, '1');
INSERT INTO `sys_resource` VALUES ('8', '2020-02-05 14:43:37', '订单管理', '/order/**', '', '2');
INSERT INTO `sys_resource` VALUES ('9', '2020-02-05 14:44:22', ' 订单退货申请管理', '/returnApply/**', '', '2');
INSERT INTO `sys_resource` VALUES ('10', '2020-02-05 14:45:08', '退货原因管理', '/returnReason/**', '', '2');
INSERT INTO `sys_resource` VALUES ('11', '2020-02-05 14:45:43', '订单设置管理', '/orderSetting/**', '', '2');
INSERT INTO `sys_resource` VALUES ('12', '2020-02-05 14:46:23', '收货地址管理', '/companyAddress/**', '', '2');
INSERT INTO `sys_resource` VALUES ('13', '2020-02-07 16:37:22', '优惠券管理', '/coupon/**', '', '3');
INSERT INTO `sys_resource` VALUES ('14', '2020-02-07 16:37:59', '优惠券领取记录管理', '/couponHistory/**', '', '3');
INSERT INTO `sys_resource` VALUES ('15', '2020-02-07 16:38:28', '限时购活动管理', '/flash/**', '', '3');
INSERT INTO `sys_resource` VALUES ('16', '2020-02-07 16:38:59', '限时购商品关系管理', '/flashProductRelation/**', '', '3');
INSERT INTO `sys_resource` VALUES ('17', '2020-02-07 16:39:22', '限时购场次管理', '/flashSession/**', '', '3');
INSERT INTO `sys_resource` VALUES ('18', '2020-02-07 16:40:07', '首页轮播广告管理', '/home/advertise/**', '', '3');
INSERT INTO `sys_resource` VALUES ('19', '2020-02-07 16:40:34', '首页品牌管理', '/home/brand/**', '', '3');
INSERT INTO `sys_resource` VALUES ('20', '2020-02-07 16:41:06', '首页新品管理', '/home/newProduct/**', '', '3');
INSERT INTO `sys_resource` VALUES ('21', '2020-02-07 16:42:16', '首页人气推荐管理', '/home/recommendProduct/**', '', '3');
INSERT INTO `sys_resource` VALUES ('22', '2020-02-07 16:42:48', '首页专题推荐管理', '/home/recommendSubject/**', '', '3');
INSERT INTO `sys_resource` VALUES ('23', '2020-02-07 16:44:56', ' 商品优选管理', '/prefrenceArea/**', '', '5');
INSERT INTO `sys_resource` VALUES ('24', '2020-02-07 16:45:39', '商品专题管理', '/subject/**', '', '5');
INSERT INTO `sys_resource` VALUES ('25', '2020-02-07 16:47:34', '后台用户管理', '/admin/**', '', '4');
INSERT INTO `sys_resource` VALUES ('26', '2020-02-07 16:48:24', '后台用户角色管理', '/role/**', '', '4');
INSERT INTO `sys_resource` VALUES ('27', '2020-02-07 16:48:48', '后台菜单管理', '/menu/**', '', '4');
INSERT INTO `sys_resource` VALUES ('28', '2020-02-07 16:49:18', '后台资源分类管理', '/resourceCategory/**', '', '4');
INSERT INTO `sys_resource` VALUES ('29', '2020-02-07 16:49:45', '后台资源管理', '/resource/**', '', '4');

-- ----------------------------
-- Table structure for sys_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_category`;
CREATE TABLE `sys_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

-- ----------------------------
-- Records of sys_resource_category
-- ----------------------------
INSERT INTO `sys_resource_category` VALUES ('1', '2020-02-05 10:21:44', '商品模块', '0');
INSERT INTO `sys_resource_category` VALUES ('2', '2020-02-05 10:22:34', '订单模块', '0');
INSERT INTO `sys_resource_category` VALUES ('3', '2020-02-05 10:22:48', '营销模块', '0');
INSERT INTO `sys_resource_category` VALUES ('4', '2020-02-05 10:23:04', '权限模块', '0');
INSERT INTO `sys_resource_category` VALUES ('5', '2020-02-07 16:34:27', '内容模块', '0');
INSERT INTO `sys_resource_category` VALUES ('6', '2020-02-07 16:35:49', '其他模块', '0');