/*
 Navicat Premium Data Transfer

 Source Server         : 业务连接
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : bill

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 31/07/2020 18:38:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `create_time` date NOT NULL COMMENT '日期',
  `briefly` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要',
  `type` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `income` decimal(64, 2) NULL DEFAULT NULL COMMENT '收入',
  `expenditure` decimal(64, 2) NULL DEFAULT NULL COMMENT '支出',
  `founder` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '持有者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `U_name`(`founder`) USING BTREE COMMENT '索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('4df32f40f53945108f89e79144394dc1', '2009-02-05', '饭店', '支出', NULL, 6564122.00, '刘总');
INSERT INTO `account` VALUES ('4e278d1bb2f54957880af43ed85e4187', '2001-05-09', '饭店', '收入', 895652.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('4e4881d7cdce412fa9a54647c57bf151', '2005-04-04', '工资', '支出', NULL, 6564122.00, '郑总');
INSERT INTO `account` VALUES ('4f8c8a31d92a4ac89f9f67b3b4680eed', '2005-04-30', '团建', '支出', NULL, 895652.00, '郑总');
INSERT INTO `account` VALUES ('5398ceccfe3d4fb190763a908f1cb1c3', '2016-09-03', '股票', '收入', 6564122.00, NULL, '王总');
INSERT INTO `account` VALUES ('565eb2e72594464f8d67166bc0dfc0d7', '2019-06-07', '饭店', '收入', 12031.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('5e1e5687379a4db0acddd87422fd5903', '2009-04-08', '股票', '支出', NULL, 542331.00, '蔡总');
INSERT INTO `account` VALUES ('5e6dcc508bd54c8e95e1f8d38454c623', '2011-07-05', '工资', '收入', 5651.00, NULL, '王总');
INSERT INTO `account` VALUES ('5e7b277ab7f243c48154978ec7ca275c', '2010-04-20', '饭店', '支出', NULL, 895652.00, '王总');
INSERT INTO `account` VALUES ('6014af4461e840699e0e0beafcfad5e3', '2002-05-20', '团建', '收入', 895652.00, NULL, '李总');
INSERT INTO `account` VALUES ('6053dac2c5ba4f1f95d8d000875ef2e2', '2003-06-12', '兼职', '支出', NULL, 12031.00, '蔡总');
INSERT INTO `account` VALUES ('623221fa75bf4e529601bd0cda2ee31a', '2004-06-02', '股票', '收入', 542331.00, NULL, '王总');
INSERT INTO `account` VALUES ('6b4ace5d4689426094f7dbe9d6b1600f', '2007-02-11', '工资', '收入', 12031.00, NULL, '王总');
INSERT INTO `account` VALUES ('6be75996292e400ca80275d21fa05510', '2014-05-01', '饭店', '收入', 542331.00, NULL, '刘总');
INSERT INTO `account` VALUES ('6c6c614b7c5243788e21d6018e20ed53', '2008-02-23', '团建', '收入', 12031.00, NULL, '郑总');
INSERT INTO `account` VALUES ('6e259e722d8a459abe780c6675692248', '2017-10-16', '接收项目', '支出', NULL, 542331.00, '郑总');
INSERT INTO `account` VALUES ('7385e0871f5f40a8bba809dcbfb63411', '2015-12-03', '工资', '收入', 6564122.00, NULL, '郑总');
INSERT INTO `account` VALUES ('745d2e70f5e443139b3dea2603afb831', '2011-01-11', '股票', '支出', NULL, 12031.00, '刘总');
INSERT INTO `account` VALUES ('793814e3b57841e9912dd947bcc0120d', '2006-04-13', '工资', '收入', 895652.00, NULL, '刘总');
INSERT INTO `account` VALUES ('794035026d3c4657b281debe765845ee', '2019-09-20', '股票', '支出', NULL, 542331.00, '刘总');
INSERT INTO `account` VALUES ('7d45e1129a5841c4bd8ef6e71ba5860a', '2010-02-23', '团建', '收入', 895652.00, NULL, '刘总');
INSERT INTO `account` VALUES ('7db4005869a043e9b71368a4f4b6d3f2', '2009-11-06', '兼职', '收入', 6564122.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('7e10e8d0c9cc410e8757f16b8a7f1edd', '2001-12-16', '饭店', '支出', NULL, 12031.00, '王总');
INSERT INTO `account` VALUES ('801ad9845cdc489b98ebd9c0bc256a4b', '2015-07-22', '饭店', '收入', 5651.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('827e9d0452da45b48fb812b9489b5f61', '2011-02-01', '接收项目', '支出', NULL, 5651.00, '蔡总');
INSERT INTO `account` VALUES ('85afc042f549415f90a8fadc0659668c', '2018-10-11', '接收项目', '支出', NULL, 542331.00, '王总');
INSERT INTO `account` VALUES ('8974054dc3f445d188977eaefd794b0b', '2001-09-04', '团建', '收入', 6564122.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('8e786fe219114fad83646bda231b1fba', '2002-04-14', '股票', '收入', 6564122.00, NULL, '郑总');
INSERT INTO `account` VALUES ('8f2f1418567a4f08a234a4d6d4ba3179', '2006-06-12', '兼职', '收入', 895652.00, NULL, '郑总');
INSERT INTO `account` VALUES ('99eedd815623485398880fa6b59fd23b', '2011-01-07', '接收项目', '收入', 542331.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('9a975b624bb64261986ae08466a0c475', '2007-01-29', '饭店', '收入', 542331.00, NULL, '郑总');
INSERT INTO `account` VALUES ('a0b0a0ebfa474fc29ac697516111f8e4', '2015-01-22', '工资', '收入', 12031.00, NULL, '郑总');
INSERT INTO `account` VALUES ('a13bcc9033b74325ae80e2915dd6c449', '2011-02-02', '股票', '收入', 12031.00, NULL, '王总');
INSERT INTO `account` VALUES ('a1ee324dc709409bbaf17db84055a28d', '2007-12-19', '股票', '收入', 895652.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('a613517bd01242d182056f45a2f549c5', '2001-12-01', '接收项目', '收入', 6564122.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('a68c734a90b74ce4b654c9ddefda6b26', '2004-11-18', '饭店', '收入', 542331.00, NULL, '王总');
INSERT INTO `account` VALUES ('a8118d8127d3425f929d5212351ed5ee', '2011-05-22', '接收项目', '收入', 895652.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('ab7a50689e834559939ea2e581410073', '2012-02-19', '兼职', '支出', NULL, 5651.00, '王总');
INSERT INTO `account` VALUES ('b1ff709ea40c481385c7564d2fb8d77c', '2014-01-24', '工资', '支出', NULL, 542331.00, '郑总');
INSERT INTO `account` VALUES ('b209c4c1e44645e1b3397f105b1d7f1f', '2014-04-23', '工资', '支出', NULL, 6564122.00, '郑总');
INSERT INTO `account` VALUES ('b7d0fcb14bd8416ea4673177cb7568e4', '2003-03-10', '股票', '收入', 895652.00, NULL, '李总');
INSERT INTO `account` VALUES ('b7ff687cebce4b778302d42e7122d51e', '2012-04-04', '股票', '收入', 5651.00, NULL, '王总');
INSERT INTO `account` VALUES ('c4ff00480d7b45acb4dc10078a1b201c', '2015-12-27', '团建', '支出', NULL, 542331.00, '刘总');
INSERT INTO `account` VALUES ('c6acb61de8464ce3bcfffd9e38b76fd6', '2003-08-10', '接收项目', '收入', 5651.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('c8b8450f5a08498aa874d06b0b6eb5cc', '2020-01-03', '团建', '支出', NULL, 6564122.00, '王总');
INSERT INTO `account` VALUES ('ce05ffcfdc724ce5b980130f13d41fbb', '2003-09-06', '接收项目', '收入', 6564122.00, NULL, '王总');
INSERT INTO `account` VALUES ('ce16dcb9354f4b7a89dc2c059375e216', '2018-04-19', '工资', '收入', 6564122.00, NULL, '李总');
INSERT INTO `account` VALUES ('d153a16d75584a4da30832347891d5a0', '2008-10-06', '饭店', '收入', 5651.00, NULL, '刘总');
INSERT INTO `account` VALUES ('d227d4d6a81f4e1582c132cb6cd569e4', '2003-06-29', '接收项目', '支出', NULL, 5651.00, '蔡总');
INSERT INTO `account` VALUES ('d2df011d772c420b9f7faac768850219', '2008-07-15', '股票', '收入', 6564122.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('d7bdb36568224fb2ace64682c061931c', '2017-07-19', '股票', '支出', NULL, 6564122.00, '蔡总');
INSERT INTO `account` VALUES ('dada42a569584b9c96c5d9a0390e1995', '2015-01-12', '工资', '收入', 6564122.00, NULL, '刘总');
INSERT INTO `account` VALUES ('dd300d48f40c4dadb8a2194ff61f4d80', '2016-11-18', '兼职', '收入', 542331.00, NULL, '蔡总');
INSERT INTO `account` VALUES ('deba27d819594fff96d03a6dff3c9bbf', '2008-03-12', '兼职', '收入', 12031.00, NULL, '李总');
INSERT INTO `account` VALUES ('e02174a2944e4217975fc87a48ed6c90', '2014-11-15', '股票', '支出', NULL, 12031.00, '刘总');
INSERT INTO `account` VALUES ('e8558c5bf8b24ef7be7d7f575b0947f6', '2011-04-15', '股票', '支出', NULL, 542331.00, '蔡总');
INSERT INTO `account` VALUES ('eb0a1563a1cb4a72b51d44973bcdbf64', '2011-12-12', '团建', '收入', 5651.00, NULL, '王总');
INSERT INTO `account` VALUES ('ed21ab9b64194a99a9057c791f8f43fb', '2011-09-09', '团建', '支出', NULL, 542331.00, '刘总');
INSERT INTO `account` VALUES ('f1cdd7624b7b4539973908fa092a6478', '2013-09-07', '股票', '支出', NULL, 5651.00, '刘总');
INSERT INTO `account` VALUES ('f57576bfcfab410283eb41480201e9a9', '2015-10-16', '股票', '支出', NULL, 895652.00, '刘总');
INSERT INTO `account` VALUES ('f725cfd58b4d46a3865c7b584d1e63fa', '2006-04-15', '饭店', '支出', NULL, 6564122.00, '郑总');
INSERT INTO `account` VALUES ('f8cc794f4cfd448482b76a2f11e32fe2', '2018-12-26', '团建', '支出', NULL, 895652.00, '蔡总');
INSERT INTO `account` VALUES ('ffe10dfe10d74799bb5101f1bffacbd1', '2011-10-23', '团建', '收入', 6564122.00, NULL, '刘总');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `grade` int(2) NOT NULL COMMENT '等级',
  `question` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问题',
  `answer` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '答案',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '李总', 'e10adc3949ba59abbe56e057f20f883e', 0, '你的生日是什么？', '11-21', '2020-05-28 23:12:50', '2020-05-28 23:13:49');
INSERT INTO `user` VALUES (5, 'liu', 'e10adc3949ba59abbe56e057f20f883e', 1, '你的生日是什么？', 'e9763f12569459734ed2a4fa96fac44e', '2020-05-28 21:23:20', '2020-05-28 21:23:20');
INSERT INTO `user` VALUES (6, 'lin', 'e10adc3949ba59abbe56e057f20f883e', 1, '你的生日是什么？', 'MTEtMjE=', '2020-05-28 22:45:50', '2020-05-29 22:08:18');
INSERT INTO `user` VALUES (7, 'liang', 'e10adc3949ba59abbe56e057f20f883e', 1, '你的生日是什么？', 'OC0yMQ==', '2020-05-28 23:14:21', '2020-05-28 23:14:21');
INSERT INTO `user` VALUES (8, 'li', 'e10adc3949ba59abbe56e057f20f883e', 0, '你好吗', '7eca689f0d3389d9dea66ae112e5cfd7', '2020-07-05 16:03:55', '2020-07-05 16:03:55');
INSERT INTO `user` VALUES (9, 'duo', 'e10adc3949ba59abbe56e057f20f883e', 0, '123456', 'e10adc3949ba59abbe56e057f20f883e', '2020-07-26 20:51:42', '2020-07-26 20:51:42');

-- ----------------------------
-- Procedure structure for selectByAll
-- ----------------------------
DROP PROCEDURE IF EXISTS `selectByAll`;
delimiter ;;
CREATE PROCEDURE `selectByAll`(IN `offset` int,IN `limit` int)
BEGIN
	SELECT * FROM account LIMIT `offset`,`limit`;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for selectByMonth
-- ----------------------------
DROP PROCEDURE IF EXISTS `selectByMonth`;
delimiter ;;
CREATE PROCEDURE `selectByMonth`(IN `offset` int,IN `limit` int)
BEGIN
	set @@sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
	SELECT 
	LEFT(a.create_time,7) as create_time, SUM(a.income) as income,SUM(a.expenditure) as expenditure,a.founder
	FROM account a
	GROUP BY LEFT(create_time,7)
	ORDER BY a.founder DESC
	LIMIT `offset`,`limit`;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for selectByYear
-- ----------------------------
DROP PROCEDURE IF EXISTS `selectByYear`;
delimiter ;;
CREATE PROCEDURE `selectByYear`(IN `offset` int,IN `limit` int)
BEGIN
	set @@sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
	SELECT 
	LEFT(a.create_time,4) as create_time, SUM(a.income) as income,SUM(a.expenditure) as expenditure,a.founder
	FROM account a
	GROUP BY LEFT(create_time,4)
	ORDER BY a.founder DESC
	LIMIT `offset`,`limit`;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
