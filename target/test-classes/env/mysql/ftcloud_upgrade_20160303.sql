ALTER TABLE `sys_user`
MODIFY COLUMN `type`  int(2) UNSIGNED NULL DEFAULT NULL COMMENT '用户类别：1、管理员，2、普通用户（内部），3、普通用户（外部）' AFTER `password`;