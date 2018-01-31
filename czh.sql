/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : czh

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-01-30 19:58:25
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
  `realname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `md5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `size` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '文件大小,zijie',
  `parsonPath` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '/' COMMENT '父目录',
  `parsonId` int(11) DEFAULT NULL,
  `time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('4', '1', 'pom.xml', null, 'http://localhost:8080/springtest/file/czh/1506514021158.xml', null, '1', 'code', null, '/', '0', '100');
INSERT INTO `tb_file` VALUES ('5', '1', '新建文件夹', null, null, null, '1', 'folder', null, '/', '0', '0');
INSERT INTO `tb_file` VALUES ('6', '1', 'github记账项目借口文档.xlsx', null, 'http://localhost:8080/springtest/file/czh/1507899759627.xlsx', null, '1', 'excel', null, '/', '0', '0');
INSERT INTO `tb_file` VALUES ('7', '1', '2017-11-03 21-33-20 的屏幕截图.png', null, 'localhost:8080/springtest/files/556c0b37b838411c9de6ee6a9f7484e9.png', 'cdaa0efeb41750478492efe6b89b3628', '1', 'image', '478167', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('8', '1', 'daotestupdate', null, 'localhost:8080/springtest/files/1/fabf647c15fe4c83b4cb85da78bbfd27.png', 'cdaa0efeb41750478492efe6b89b3628', '1', 'image', '478167', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('9', '1', '公交离线码支付平台接口方案_整体方案和小程序端.docx', null, 'localhost:8080/springtest/files/1/8876f3bfb8ee430fb679184d2a9050cd.docx', 'fc8d5a6d6cfac23ae4f0d3e1719cd692', '1', 'word', '720662', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('10', '1', 'todo.md', null, 'localhost:8080/springtest/files/1/08805f40eb5f4d8da2d444055e0652a3.md', 'b45ddf8c95ab1502ec5f587d0bbca71a', '1', '', '308', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('11', '1', 'C:\\gitclone\\springtest\\pom.xml', null, 'localhost:8080/springtest/files/1/6e452f0f340742cfa2fbe5103edd2334.xml', 'e1847f4c4a36c4d387d54547b84b5673', '1', 'code', '4169', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('12', '1', 'C:\\gitclone\\springtest\\readme.md', '85121cf344ee42b7b247c6351ed9ee29.md', 'localhost:8080/springtest/files/1/85121cf344ee42b7b247c6351ed9ee29.md', '40fc2f7258e015266b23ac359c306b1d', '1', '', '2215', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('13', '1', 'czh.sql', '8da0f4194126408ba4069b423da296ea.sql', 'localhost:8080/springtest/files/1/8da0f4194126408ba4069b423da296ea.sql', '533c442feeb4e41324abf4d53a7f895', '1', '', '5434', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('14', '1', 'index.html', '9f3f5e0248f245a193a85a2258749e7f.html', 'localhost:8080/springtest/files/1/9f3f5e0248f245a193a85a2258749e7f.html', '7412b7d2faeb3566e7dceb2e1fc4c42c', '1', 'code', '2491', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('15', '1', 'test.ftl', '6b3315e6443241e29c407debd58d56fd.ftl', 'localhost:8080/springtest/files/1/6b3315e6443241e29c407debd58d56fd.ftl', 'ab547888ce989d785387a9230dcec9bd', '1', '', '234', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('16', '1', 'index.html', '6a3c8524a76a41b2bd4053f5e118b88b.html', 'localhost:8080/springtest/files/1/6a3c8524a76a41b2bd4053f5e118b88b.html', '69ca3d190f6866605f3e72fe99ea0b97', '1', 'code', '2954', '/', '0', '0');
INSERT INTO `tb_file` VALUES ('17', '1', 'bootstrap.min.js', 'ba5797827591459196492805190e5f3f.js', 'localhost:8080/springtest/files/1/ba5797827591459196492805190e5f3f.js', '4c84852e9937b142ac73c285b895b85', '1', 'code', '37051', '新建文件夹', '5', '0');
INSERT INTO `tb_file` VALUES ('18', '1', 'jquery.js', 'c1878f3b06bc49868aad99e3c3e39c0c.js', 'localhost:8080/springtest/files/1/c1878f3b06bc49868aad99e3c3e39c0c.js', 'a5a8ab0a7c815e296c5421f9eea326db', '1', 'code', '278292', '新建文件夹', '5', '0');
INSERT INTO `tb_file` VALUES ('19', '1', '测试文件夹', null, null, null, '1', 'folder', null, '/', '0', '0');
INSERT INTO `tb_file` VALUES ('20', '1', 'util.js', '0583bb3e02eb4445b09b6cf508006ebb.js', 'localhost:8080/springtest/files/1/0583bb3e02eb4445b09b6cf508006ebb.js', '17d92edde317bd8fd88b982afb1b5631', '1', 'code', '2246', '新建文件夹', '5', '1516884626639');
INSERT INTO `tb_file` VALUES ('23', '1', '新建文件夹', null, null, null, '1', 'folder', '0', '/', '0', '1517102533828');
INSERT INTO `tb_file` VALUES ('24', '1', '测试文件加', null, null, null, '2', 'folder', '0', '/', '0', '1517106306686');
INSERT INTO `tb_file` VALUES ('25', '1', '新建文件111', null, null, null, '2', 'folder', '0', '/', '0', '1517106570972');
INSERT INTO `tb_file` VALUES ('26', '1', 'jquery.js', 'f951161436104eb7af37a5a4408ff81b.js', 'localhost:8080/springtest/files/1/f951161436104eb7af37a5a4408ff81b.js', 'a5a8ab0a7c815e296c5421f9eea326db', '1', 'code', '278292', '/', '0', '1517106575182');
INSERT INTO `tb_file` VALUES ('27', '1', 'bootstrap.min.js', 'c2ac7e71289d4f5786925798e833525d.js', 'localhost:8080/springtest/files/1/c2ac7e71289d4f5786925798e833525d.js', '4c84852e9937b142ac73c285b895b85', '1', 'code', '37051', '测试文件夹', '19', '1517106583640');
INSERT INTO `tb_file` VALUES ('28', '1', 'axios.js', '33bb5aa114aa4e9393ab52f55b1b9d5e.js', 'localhost:8080/springtest/files/1/33bb5aa114aa4e9393ab52f55b1b9d5e.js', 'a652dea4a9160432027a115bbd905252', '1', 'code', '44266', '测试文件夹', '19', '1517106585531');

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
