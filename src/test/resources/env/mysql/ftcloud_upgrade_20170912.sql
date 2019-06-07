ALTER TABLE `network_device`
ADD COLUMN `type`  int(4) NULL DEFAULT 0 COMMENT '1内部，2外部' AFTER `maintenance_type`;