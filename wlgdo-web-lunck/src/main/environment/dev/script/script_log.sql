# 添加t_actor字段
# Date: 2017-12-07 21:46
# author:Ligang.Wang

ALTER TABLE `mktdb`.`t_actor` 
ADD COLUMN `create_time` DATETIME NULL COMMENT '创建时间' AFTER `status`,
ADD COLUMN `update_time` DATETIME NULL AFTER `create_time`;

