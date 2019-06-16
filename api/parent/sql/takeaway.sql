SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(32) NOT NULL,
  `order_uid` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` double(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '商品图片',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
BEGIN;
INSERT INTO `order_detail` VALUES (1, 'detail79fd239d88bb481dbddb', 'master3bb603b416974834aa4a', '1', '炸酱面', 16.00, 1, '无', '2019-06-15 16:05:32', '2019-06-15 16:05:32', 0);
INSERT INTO `order_detail` VALUES (2, 'detailfdb3f17b37e345738323', 'master3bb603b416974834aa4a', '1', '炸酱面', 16.00, 2, '无', '2019-06-15 16:05:32', '2019-06-15 16:05:32', 0);
INSERT INTO `order_detail` VALUES (25, 'detail94041f42a4614f639d89', 'master72a88e5615394814861e', '2', '蛋炒饭', 16.00, 4, '无', '2019-06-16 13:21:18', '2019-06-16 13:21:18', 0);
INSERT INTO `order_detail` VALUES (26, 'detailf960256a3f3747e2ae34', 'master72a88e5615394814861e', '1', '炸酱面', 16.00, 3, '无', '2019-06-16 13:21:18', '2019-06-16 13:21:18', 0);
INSERT INTO `order_detail` VALUES (27, 'detailaa81794e32a54734af07', 'master0eb9c83e8e674c72bd31', '2', '蛋炒饭', 16.00, 4, '无', '2019-06-16 13:26:48', '2019-06-16 13:26:48', 0);
INSERT INTO `order_detail` VALUES (28, 'detail770b4b7ed4ba403d8dd0', 'master0eb9c83e8e674c72bd31', '1', '炸酱面', 16.00, 3, '无', '2019-06-16 13:26:48', '2019-06-16 13:26:48', 0);
COMMIT;

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_id` varchar(64) NOT NULL COMMENT '买家唯一id',
  `total_count` int(11) NOT NULL COMMENT '订单数量',
  `total_amount` double(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` int(3) NOT NULL DEFAULT '0' COMMENT '订单状态 0新下单',
  `pay_status` int(3) NOT NULL DEFAULT '0' COMMENT '支付状态,0未支付',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
BEGIN;
INSERT INTO `order_master` VALUES (1, 'master3bb603b416974834aa4a', '叶先生', '18850341357', '宝安区固戍永泰', '2', 3, 48.00, 0, 1, '2019-06-15 16:05:32', '2019-06-15 16:05:32', 0);
INSERT INTO `order_master` VALUES (13, 'master72a88e5615394814861e', 'zero', '175204', '龙华区', '2', 7, 112.00, 0, 0, '2019-06-16 13:21:18', '2019-06-16 13:21:18', 0);
INSERT INTO `order_master` VALUES (14, 'master0eb9c83e8e674c72bd31', 'zero', '175204', '龙华区', '2', 7, 112.00, 0, 0, '2019-06-16 13:26:48', '2019-06-16 13:26:48', 0);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
BEGIN;
INSERT INTO `product_category` VALUES (1, '面食粥点', 1, '2019-06-15 14:54:16', '2019-06-15 14:54:18', 0);
INSERT INTO `product_category` VALUES (2, '小吃夜宵', 2, '2019-06-15 14:54:52', '2019-06-15 14:54:57', 0);
INSERT INTO `product_category` VALUES (3, '快餐便当', 3, '2019-06-15 14:55:07', '2019-06-15 14:55:09', 0);
COMMIT;

-- ----------------------------
-- Table structure for product_comment
-- ----------------------------
DROP TABLE IF EXISTS `product_comment`;
CREATE TABLE `product_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '评论人id',
  `score` int(11) NOT NULL COMMENT '分数',
  `content` varchar(128) DEFAULT NULL COMMENT '内容',
  `product_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
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
  `stock_count` int(11) NOT NULL DEFAULT '0' COMMENT '每日库存',
  `total_score` int(11) DEFAULT '0',
  `average_score` double(11,2) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
BEGIN;
INSERT INTO `product_info` VALUES ('1', '1', '炸酱面', 16.00, 1, '无', '无', '无', '无', 1, 0, 10, 10.00, 0, '2019-06-15 14:56:51', '2019-06-15 14:57:00', 0);
INSERT INTO `product_info` VALUES ('2', '2', '蛋炒饭', 16.00, 1, '无', '无', '无', '无', 2, 0, 10, 10.00, 0, '2019-06-15 14:56:51', '2019-06-15 14:57:00', 0);
COMMIT;

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
-- Records of store
-- ----------------------------
BEGIN;
INSERT INTO `store` VALUES (1, 'http://upload1.95171.cn/albumpicimages/20161203/F14B18V42110/18dec399-703f-48b8-9a86-dae0cf220f04.jpg', '川湘小厨', '川湘小厨，上海最火爆的人气餐厅之一，主要以为主，深受广大食客喜爱。同时还不定期的举行团购优惠活动，是您吃饭聚会宴请的好选择。', 1, 10, 10, 10, 100, 100, 'http://upload1.95171.cn/albumpicimages/20161203/F14B18V42110/18dec399-703f-48b8-9a86-dae0cf220f04.jpg', '上海普陀区 大渡河路845号(近长风集贸市场)', '2019-06-14 16:49:29', 0);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (2, 20, 'hiyzx', '188', '123456', '2019-06-16 11:34:27', 0);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_check_count
-- ----------------------------
BEGIN;
INSERT INTO `user_check_count` VALUES (2, 2, '2019-06-14 18:48:39', 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for user_point
-- ----------------------------
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE `user_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_point
-- ----------------------------
BEGIN;
INSERT INTO `user_point` VALUES (2, 2, 0);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_point_record
-- ----------------------------
BEGIN;
INSERT INTO `user_point_record` VALUES (4, 2, 2, 2, '2019-06-14 18:48:40');
INSERT INTO `user_point_record` VALUES (5, 2, 1, 2, '2019-06-15 14:36:58');
INSERT INTO `user_point_record` VALUES (6, 2, 1, 2, '2019-06-16 11:34:08');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
