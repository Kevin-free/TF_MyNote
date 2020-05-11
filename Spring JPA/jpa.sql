/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : jpa

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 27/04/2020 08:59:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Jack', 'Bauer');
INSERT INTO `customer` VALUES (2, 'Chloe', 'O\'Brian');
INSERT INTO `customer` VALUES (3, 'Kim', 'Bauer');
INSERT INTO `customer` VALUES (4, 'David', 'Palmer');
INSERT INTO `customer` VALUES (5, 'Michelle', 'Dessler');

-- ----------------------------
-- Table structure for customer_order
-- ----------------------------
DROP TABLE IF EXISTS `customer_order`;
CREATE TABLE `customer_order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_id` int(0) NULL DEFAULT NULL,
  `price` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_order
-- ----------------------------
INSERT INTO `customer_order` VALUES (1, 1, 23, '2020-04-24 14:07:49');
INSERT INTO `customer_order` VALUES (2, 2, 21, '2020-04-24 14:07:52');
INSERT INTO `customer_order` VALUES (3, 3, 26, '2020-04-24 14:07:57');
INSERT INTO `customer_order` VALUES (4, 4, 23, '2020-04-24 14:08:00');
INSERT INTO `customer_order` VALUES (5, 5, 24, '2020-04-24 14:08:06');
INSERT INTO `customer_order` VALUES (6, 1, 33, '2020-04-24 16:03:06');
INSERT INTO `customer_order` VALUES (7, 1, 156, '2020-04-24 16:03:10');
INSERT INTO `customer_order` VALUES (8, 2, 65, '2020-04-24 17:40:37');
INSERT INTO `customer_order` VALUES (9, 2, 44, '2020-04-24 17:40:41');
INSERT INTO `customer_order` VALUES (10, 2, 78, '2020-04-24 17:40:48');
INSERT INTO `customer_order` VALUES (11, 5, 13, '2020-04-24 17:40:54');
INSERT INTO `customer_order` VALUES (12, 3, 99, '2020-04-24 17:41:01');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (2);
INSERT INTO `hibernate_sequence` VALUES (2);

SET FOREIGN_KEY_CHECKS = 1;
