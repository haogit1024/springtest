/*
Navicat MySQL Data Transfer

Source Server         : 测试链接
Source Server Version : 50719
Source Host           : 119.23.153.64:3307
Source Database       : czh

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-17 16:50:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `budget` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_book
-- ----------------------------

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `filename` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `md5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `size` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '文件大小,zijie',
  `parsonPath` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '/' COMMENT '父目录',
  `parsonId` int(11) DEFAULT NULL,
  `time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('4', '1', 'pom.xml', 'http://localhost:8080/springtest/file/czh/1506514021158.xml', null, '1', 'code', null, '/', '0', '100');
INSERT INTO `tb_file` VALUES ('5', '1', '新建文件夹', null, null, '1', 'folder', null, '/', '0', '0');
INSERT INTO `tb_file` VALUES ('6', '1', 'github记账项目借口文档.xlsx', 'http://localhost:8080/springtest/file/czh/1507899759627.xlsx', null, '1', 'excel', null, '/', '0', '0');
INSERT INTO `tb_file` VALUES ('7', '1', '2017-11-03 21-33-20 的屏幕截图.png', 'localhost:8080/springtest/files/556c0b37b838411c9de6ee6a9f7484e9.png', 'cdaa0efeb41750478492efe6b89b3628', '1', 'image', '478167', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('8', '1', 'daotestupdate', 'localhost:8080/springtest/files/1/fabf647c15fe4c83b4cb85da78bbfd27.png', 'cdaa0efeb41750478492efe6b89b3628', '1', 'image', '478167', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('9', '1', '公交离线码支付平台接口方案_整体方案和小程序端.docx', 'localhost:8080/springtest/files/1/8876f3bfb8ee430fb679184d2a9050cd.docx', 'fc8d5a6d6cfac23ae4f0d3e1719cd692', '1', 'word', '720662', '/', '0', '0');

-- ----------------------------
-- Table structure for tb_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL,
  `createTime` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `money` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_tickler
-- ----------------------------
DROP TABLE IF EXISTS `tb_tickler`;
CREATE TABLE `tb_tickler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `inputTime` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT '1' COMMENT '真实时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_tickler
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null);
