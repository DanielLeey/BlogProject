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