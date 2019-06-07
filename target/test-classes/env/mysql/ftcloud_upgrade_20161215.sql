INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('63', NULL, '时间段性能分布情况', '99', '/timesDistributed', NULL, 'timesDistributed');
ALTER TABLE `physical_machine_deploy`
ADD COLUMN `complet_time`  datetime NULL DEFAULT NULL COMMENT '完成时间' AFTER `domain_password`;
INSERT INTO `sysconfig` VALUES ('29', '系统名称', 'system_name', '运营管理平台');
