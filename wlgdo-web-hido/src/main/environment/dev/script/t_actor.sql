# Host: 172.16.135.148  (Version 5.6.27-log)
# Date: 2017-12-06 18:17:59
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "t_actor"
#

CREATE TABLE `t_actor` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `openid` varchar(45) DEFAULT NULL COMMENT '微信的openid',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `org_id` varchar(20) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(125) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL COMMENT '性别：0未知，1：男，2：女',
  `head_img` varchar(256) DEFAULT NULL COMMENT '用户头像uri',
  `wx_body` varchar(512) DEFAULT NULL COMMENT '微信信息使用标准json格式存储',
  `employee_no` varchar(50) DEFAULT NULL COMMENT '雇员号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` int(2) DEFAULT NULL COMMENT '参与者状态0:未参与，1:手机未认证，2：正常',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `org_id` (`org_id`,`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参与者';
