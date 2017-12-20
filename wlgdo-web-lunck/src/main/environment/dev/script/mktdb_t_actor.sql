-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mktdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `t_actor`
--

DROP TABLE IF EXISTS `t_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_actor` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `employee_no` varchar(50) DEFAULT NULL COMMENT '雇员号码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别：0未知，1：男，2：女',
  `status` int(2) DEFAULT NULL COMMENT '参与者状态0:未参与，1:手机未认证，2：正常',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `org_id` varchar(20) DEFAULT NULL COMMENT '机构ID',
  `head_img` varchar(256) DEFAULT '/img/1.jpg' COMMENT '用户头像uri',
  `openid` varchar(45) DEFAULT NULL COMMENT '微信的openid',
  `wx_body` varchar(256) DEFAULT NULL COMMENT '微信信息使用标准json格式存储',
  `nick_name` varchar(45) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参与者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_actor`
--

LOCK TABLES `t_actor` WRITE;
/*!40000 ALTER TABLE `t_actor` DISABLE KEYS */;
INSERT INTO `t_actor` VALUES ('166b722305a64445a56d2c0fb16e85e1','zg-020',NULL,'马云',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('2be0bc02c197463aa2be2e0342a1aec4','zg-009',NULL,'沈春阳',NULL,0,NULL,'0','1/2.jpg',NULL,NULL,NULL),('3ed244b89eac4bbe8080cd2a1334f6ba','zg-018',NULL,'普京',NULL,0,NULL,'0','1/3.jpg',NULL,NULL,NULL),('424d5b974db84f6e80baa1ad7d11012a','ZG-001',NULL,'李晨',NULL,0,NULL,'0','1/4.jpg',NULL,NULL,NULL),('4affd98fa0e647858e3212e106ff1afa','zg-008',NULL,'邓紫棋',NULL,0,NULL,'0','1/5.jpg',NULL,NULL,NULL),('55d46183aa2243fda76423f99b32cb83','zg-011',NULL,'周笔畅',NULL,0,NULL,'0','2/6.jpg',NULL,NULL,NULL),('57275d6b5c284a81a29811c3efc9e5ff','zg-004',NULL,'李白',NULL,0,NULL,'0','1/7.jpg',NULL,NULL,NULL),('67f8d7ba983545bcbc40aef74aa29333','zg-010',NULL,'王千源',NULL,0,NULL,'0','1/8.jpg',NULL,NULL,NULL),('6d8c40cea7794af2af5425ec26b0e8d5','zg-005',NULL,'赵丽颖',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('6e0bb73c92ad4f9eb5b9dbbee99bae4f','zg-015',NULL,'吴秀波',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('7a85550705714a38bc5b4d2a55a39a71','zg-013',NULL,'刘德华',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('810e29852abc4360a68628a1cad17068','zg-014',NULL,'周润发',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('85ca5cffed8045bb802484d3378b3242','zg-002',NULL,'范冰冰',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('910c5f73d5924aed882b67556c66f055','zg-112',NULL,'周杰伦',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('9e29d0264e4e469d8f2b1eac488d3c62','zg-006',NULL,'黄渤',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('a4d81f6ae8004d6d9e5cd11bca0b8197','zg-016',NULL,'奥巴马',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('a7f74285f738451b979f7240a46eeb53','zg-017',NULL,'特朗普',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('b447457dfc7244afb1c407ee55291819','zg-007',NULL,'赵雷',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('c1823627b58d4903ad88ea631b2b825c','zg-003',NULL,'孟浩然',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL),('eb0c8fef309e41d4baa00f353febc64f','zg-019',NULL,'周星驰',NULL,0,NULL,'0','1/1.jpg',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_actor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-05  2:41:05
