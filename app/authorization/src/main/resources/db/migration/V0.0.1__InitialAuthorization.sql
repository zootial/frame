CREATE DATABASE  IF NOT EXISTS `auth` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `auth`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: auth
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `act_value` int(11) NOT NULL COMMENT '动作码值',
  `act_name` varchar(16) NOT NULL COMMENT '动作名称',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `creator_code` varchar(32) DEFAULT NULL,
  `creator_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `action_u1` (`act_value`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='动作表';
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES 
(1,1,'显示','2018-01-30 14:15:37','system','系统'),
(2,2,'执行','2018-01-30 14:15:38','system','系统'),
(3,3,'查询','2018-01-30 14:15:38','system','系统'),
(4,4,'修改','2018-01-30 14:15:38','system','系统'),
(5,5,'新增','2018-01-30 14:15:38','system','系统'),
(6,6,'删除','2018-01-30 14:15:38','system','系统'),
(7,7,'导入','2018-01-30 14:15:38','system','系统'),
(8,8,'导出','2018-01-30 14:15:38','system','系统'),
(9,9,'上传','2018-01-30 14:15:38','system','系统'),
(10,10,'下载','2018-01-30 14:15:38','system','系统');
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_action`
--

DROP TABLE IF EXISTS `res_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_code` varchar(128) NOT NULL COMMENT '资源编码',
  `act_key` varchar(256) DEFAULT NULL COMMENT '动作Key,可为URI',
  `act_value` int(11) NOT NULL COMMENT '动作码值',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_code` varchar(32) DEFAULT NULL,
  `creator_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `res_action_u1` (`res_code`,`act_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源动作表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) NOT NULL COMMENT '资源编码',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `parent_code` varchar(128) DEFAULT NULL COMMENT '父级资源编码',
  `obj_code` varchar(32) DEFAULT NULL COMMENT '对象编码',
  `obj_type` varchar(16) NOT NULL COMMENT '对象类型[ENTITY,ELEMENT,FUNCTION,MENU,FILE]',
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_code` varchar(32) DEFAULT NULL COMMENT '创建人编码',
  `creator_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_date` datetime DEFAULT NULL,
  `reviser_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resouce_u1` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL COMMENT '组编码',
  `name` varchar(32) DEFAULT NULL COMMENT '组名',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_u1` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team_permission`
--

DROP TABLE IF EXISTS `team_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_code` varchar(32) NOT NULL COMMENT '组编码',
  `res_code` varchar(128) NOT NULL COMMENT '资源编码',
  `limit_set` varchar(32) NOT NULL COMMENT '动作权限集',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_permission_u1` (`team_code`,`res_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '用户编码',
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `status` char(2) NOT NULL COMMENT '状态[01正常,02锁定,03注销]',
  `account` varchar(16) NOT NULL COMMENT '账号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_u1` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(32) NOT NULL COMMENT '用户编码',
  `res_code` varchar(128) NOT NULL COMMENT '资源编码',
  `limit_set` varchar(32) NOT NULL COMMENT '动作权限集',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_permission_u1` (`user_code`,`res_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_team`
--

DROP TABLE IF EXISTS `user_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(32) NOT NULL COMMENT '用户编码',
  `team_code` varchar(32) NOT NULL COMMENT '组编码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_team_u1` (`user_code`,`team_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户所属组';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-30 14:38:27
