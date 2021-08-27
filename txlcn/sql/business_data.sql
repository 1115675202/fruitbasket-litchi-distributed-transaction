/*
 Navicat Premium Data Transfer

 Source Server         : localhost_5_7
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : txlcn-a

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 27/08/2021 13:57:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for business_data
-- ----------------------------
DROP TABLE IF EXISTS `business_data`;
CREATE TABLE `business_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `information` varchar(255) DEFAULT NULL,
  `idempotence_key` varchar(32) NOT NULL COMMENT '用来解决消息幂等性',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ idempotence_key` (`idempotence_key`) USING BTREE COMMENT '用来解决消息幂等性'
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
