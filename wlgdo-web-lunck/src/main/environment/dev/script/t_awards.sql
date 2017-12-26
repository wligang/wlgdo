# Host: 172.16.135.148  (Version 5.6.27-log)
# Date: 2017-11-29 16:40:18
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "t_awards"
#

CREATE TABLE `t_awards` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `org_id` varchar(255) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '奖品名称',
  `level` varchar(255) DEFAULT NULL COMMENT '奖品等级',
  `total` varchar(255) NOT NULL DEFAULT '' COMMENT '奖品总数',
  `sent_num` varchar(255) NOT NULL DEFAULT '' COMMENT '派送出去的数量',
  `status` int(11) DEFAULT NULL COMMENT '奖品发送状态：0，未开始，1，已开始，2已结束',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `refuse_uid` varchar(1024) DEFAULT NULL COMMENT '拒绝用户列表',
  `keep_uid` varchar(1024) DEFAULT NULL COMMENT '保留中奖用户',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `org_id` (`org_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖项表';

#
# Data for table "t_awards"
#

REPLACE INTO `t_awards` VALUES ('2e430c6ac46d4e16959e87d6c90b91d0','1','三等奖','1','10','0',0,'2017-11-29 16:29:54',NULL,'166b722305a64445a56d2c0fb16e85e1,2be0bc02c197463aa2be2e0342a1aec4,a4d81f6ae8004d6d9e5cd11bca0b8197','85ca5cffed8045bb802484d3378b3242,a7f74285f738451b979f7240a46eeb53'),('34961f98f260434e8772f8b88bdb4c6e','0','三等奖','1','10','0',0,'2017-11-28 14:34:47',NULL,NULL,NULL),('540e81abafa248e6bea644cffda97c0e','0','一等奖','1','10','0',0,'2017-11-28 14:34:22',NULL,NULL,NULL),('b19d908b5a8e44ae9390a3919d51c71e','0','特等奖','1','10','0',0,'2017-11-28 14:35:06',NULL,NULL,NULL),('b9fb86c07a7942dfa346c6f00e3393fd','0','感恩节大回馈','1','10','0',0,'2017-11-28 14:33:16',NULL,NULL,NULL),('ebf1dfe36a7244b6888cde938239e5f4','0','二等奖','1','10','0',0,'2017-11-28 14:34:37',NULL,NULL,NULL);
