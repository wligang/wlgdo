# Host: 172.16.135.148  (Version 5.6.27-log)
# Date: 2017-11-28 18:24:27
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "t_prize"
#

DROP TABLE IF EXISTS `t_prize`;
CREATE TABLE `t_prize` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(255) NOT NULL DEFAULT '' COMMENT '中奖用户表',
  `award_id` varchar(255) NOT NULL DEFAULT '' COMMENT '奖项ID',
  `create_time` datetime DEFAULT NULL COMMENT '中奖时间',
  `reward_index` bigint(20) NOT NULL DEFAULT '0' COMMENT '中奖的序列，纳秒级别',
  `receive_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '领取时间',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '奖品状态：0可领取，1暂时不可领取，2已经作废，3已领取',
  PRIMARY KEY (`Id`),
  KEY `uid` (`uid`,`award_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖品表';

#
# Data for table "t_prize"
#

INSERT INTO `t_prize` VALUES ('49f6ba5a5c434066b8db7b583511cd4f','810e29852abc4360a68628a1cad17068','34961f98f260434e8772f8b88bdb4c6e','2017-11-28 18:13:07',1067541279477033,NULL,'0'),('8d3803bc160248b9a0cb60358add3e52','85ca5cffed8045bb802484d3378b3242','34961f98f260434e8772f8b88bdb4c6e','2017-11-28 18:13:07',1067541279495215,NULL,'0'),('ca395050c5cd4d4d8e770e2e94e376b7','7a85550705714a38bc5b4d2a55a39a71','34961f98f260434e8772f8b88bdb4c6e','2017-11-28 18:13:07',1067541279287596,NULL,'0'),('cb8c69e64b3842f585f621ad322c1a7b','','540e81abafa248e6bea644cffda97c0e','2017-11-28 17:53:11',1066345721510330,NULL,'0');
