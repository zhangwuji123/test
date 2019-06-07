ALTER TABLE `sys_user`
ADD COLUMN `last_update_date`  varchar(10) NULL COMMENT '最后更新日期' AFTER `project`,
ADD COLUMN `sync_user`  int(1) NULL DEFAULT 0 COMMENT '是否为同步用户' AFTER `last_update_date`;
