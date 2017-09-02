CREATE DATABASE  IF NOT EXISTS `czhdata` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `czhdata`;
-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: czhdata
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_book`
--

DROP TABLE IF EXISTS `tb_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `name` varchar(64) DEFAULT NULL COMMENT '名字',
  `uid` varchar(100) DEFAULT NULL COMMENT '用户id',
  `budget` double(10,2) DEFAULT NULL COMMENT '预算',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='记账本';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_book`
--

LOCK TABLES `tb_book` WRITE;
/*!40000 ALTER TABLE `tb_book` DISABLE KEYS */;
INSERT INTO `tb_book` VALUES (1,'name','fuckyou',1.20),(2,'name','fuck',1.20);
/*!40000 ALTER TABLE `tb_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_record`
--

DROP TABLE IF EXISTS `tb_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
  `tid` int(11) DEFAULT NULL COMMENT 'tickler表的id',
  `createTime` int(11) DEFAULT NULL COMMENT '创建时间',
  `time` int(11) DEFAULT NULL COMMENT '填入的时间',
  `grade` int(11) DEFAULT NULL COMMENT '分数',
  `money` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_record`
--

LOCK TABLES `tb_record` WRITE;
/*!40000 ALTER TABLE `tb_record` DISABLE KEYS */;
INSERT INTO `tb_record` VALUES (1,1,1148099333,1148099333,100,100),(2,1,1148181653,1148181653,100,10.5),(3,1,1148341177,1148341177,100,10.5),(4,1,1148458082,1148458082,100,10.5);
/*!40000 ALTER TABLE `tb_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tickler`
--

DROP TABLE IF EXISTS `tb_tickler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tickler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `inputTime` int(11) DEFAULT NULL COMMENT '导入时间，时间戳',
  `time` int(11) DEFAULT NULL COMMENT '时间发生时间，时间戳',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注，最多300字',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tickler`
--

LOCK TABLES `tb_tickler` WRITE;
/*!40000 ALTER TABLE `tb_tickler` DISABLE KEYS */;
INSERT INTO `tb_tickler` VALUES (12,1,1,11,'aaa',12);
/*!40000 ALTER TABLE `tb_tickler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` varchar(100) NOT NULL COMMENT 'uuid唯一标识',
  `account` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(64) DEFAULT NULL COMMENT '电话号码',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(64) DEFAULT NULL COMMENT '真名',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('96f360c4a1f444e09660ce10d6311ed7','admin','root','a.com','12345678901',1,'hao','czh',NULL),('testid','tese11','test11','testemail.com1','1234567',1,'testnick1','testReal1',0);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-30 15:48:37
