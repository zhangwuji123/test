CREATE TABLE `vpc_user_relation` (
  `uuid` varchar(128) NOT NULL,
  `vpc_uuid` varchar(128) DEFAULT NULL COMMENT '关联vpc',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '关联用户',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `sys_user`
MODIFY COLUMN `status`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态enabled/disabled/lock/modifying/creating/deleting' AFTER `salt`;