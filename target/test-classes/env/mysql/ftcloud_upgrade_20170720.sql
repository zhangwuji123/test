ALTER TABLE `task_info`
MODIFY COLUMN `resource_type`  varchar(128) NULL DEFAULT NULL COMMENT '资源类型名称' AFTER `resource_uuid`;
ALTER TABLE `task_info`
ADD COLUMN `user_uuid`  varchar(128) NULL COMMENT '操作人' AFTER `resource_name`;

ALTER TABLE `task_info` ADD CONSTRAINT `fk_task_user_uuid` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;


