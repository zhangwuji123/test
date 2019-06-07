ALTER TABLE alert ADD COLUMN assign_user_uuid varchar(128) NULL DEFAULT NULL COMMENT '指派用户' after user_uuid;
ALTER TABLE alert ADD COLUMN assign_time datetime NULL DEFAULT NULL COMMENT '指派时间' after resolved_time;
INSERT INTO `sysconfig` VALUES ('23', '启用部门配额', 'isQuotaEnable', '0');