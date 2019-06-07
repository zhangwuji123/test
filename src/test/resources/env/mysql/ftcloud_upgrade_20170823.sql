INSERT INTO `sysconfig` VALUES ('44', '1', '合规检查开关', 'compliance_switch', '1');
ALTER TABLE `vdc_quota`
ADD COLUMN `quota_usage`  bigint(20) NULL AFTER `quota_num`;
ALTER TABLE `vpc_quota`
ADD COLUMN `quota_usage`  bigint(20) NULL AFTER `quota_num`;
