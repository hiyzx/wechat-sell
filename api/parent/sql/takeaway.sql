/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50720
Source Host           : 120.78.210.235:3306
Source Database       : takeaway

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-04 17:11:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` double(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '商品图片',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_id` varchar(64) NOT NULL COMMENT '买家唯一id',
  `order_amount` double(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` int(3) NOT NULL DEFAULT '0' COMMENT '订单状态 0新下单',
  `pay_status` int(3) NOT NULL DEFAULT '0' COMMENT '支付状态,0未支付',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_buyer_openid` (`buyer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '类目名称',
  `show_index` int(11) NOT NULL COMMENT '展示顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uqe_category_type` (`show_index`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8 COMMENT='商品类目表';

-- ----------------------------
-- Table structure for product_comment
-- ----------------------------
DROP TABLE IF EXISTS `product_comment`;
CREATE TABLE `product_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '评论人id',
  `score` int(11) NOT NULL COMMENT '分数',
  `content` varchar(128) DEFAULT NULL COMMENT '内容',
  `type` int(11) NOT NULL COMMENT '评论类型-1,推荐,2吐槽',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` varchar(32) NOT NULL COMMENT '商品id',
  `uid` varchar(40) DEFAULT NULL,
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `price` double(8,2) DEFAULT NULL COMMENT '单价',
  `sell_count` int(11) NOT NULL COMMENT '库存',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `info` varchar(64) DEFAULT NULL COMMENT '简介',
  `icon` varchar(128) DEFAULT NULL COMMENT '小图',
  `image` varchar(128) DEFAULT NULL COMMENT '图片',
  `category_id` int(11) NOT NULL COMMENT '类目编号',
  `total_score` int(11) DEFAULT '0',
  `average_score` double(11,2) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(128) NOT NULL COMMENT '头像',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `introduction` varchar(128) NOT NULL COMMENT '介绍',
  `delivery_type` tinyint(4) NOT NULL COMMENT '配送方式',
  `service_score` double DEFAULT NULL COMMENT '服务评分',
  `food_score` double DEFAULT NULL COMMENT '食品评分',
  `min_price` int(11) NOT NULL COMMENT '最低价',
  `product_count` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数量',
  `multi_picture` varchar(512) DEFAULT NULL COMMENT '多图',
  `address` varchar(128) NOT NULL COMMENT '地址',
  `join_time` datetime NOT NULL COMMENT '加入时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for store_notice
-- ----------------------------
DROP TABLE IF EXISTS `store_notice`;
CREATE TABLE `store_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NOT NULL COMMENT '店铺id',
  `content` varchar(128) NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL COMMENT '年龄',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `phone` varchar(50) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_check_count
-- ----------------------------
DROP TABLE IF EXISTS `user_check_count`;
CREATE TABLE `user_check_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '关联的用户id',
  `check_time` datetime NOT NULL COMMENT '签到时间',
  `continue_count` int(11) NOT NULL COMMENT '连续签到天数',
  `max_count` int(11) NOT NULL COMMENT '最长连续签到天数',
  `history` bigint(11) NOT NULL COMMENT '签到历史记录',
  `sum` int(11) NOT NULL COMMENT '总的签到天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_point
-- ----------------------------
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE `user_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_point_record
-- ----------------------------
DROP TABLE IF EXISTS `user_point_record`;
CREATE TABLE `user_point_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` int(11) DEFAULT NULL COMMENT '积分类型',
  `gain_point` int(11) DEFAULT NULL COMMENT '获得的积分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
