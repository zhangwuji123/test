ALTER TABLE `instance_volume_info`
MODIFY COLUMN `type`  int(1) NULL DEFAULT NULL COMMENT '卷类型,0:系统磁盘；1:数据盘' AFTER `name`;