ALTER TABLE `cluster`
ADD COLUMN `performance_data_sync`  int(4) NULL COMMENT '性能数据同步' AFTER `auto_sync`;