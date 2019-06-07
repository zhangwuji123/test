ALTER TABLE `single_instance_template` ADD COLUMN `charging_rules_uuid` varchar(128) NULL COMMENT '计费规则编号' AFTER `type`;
ALTER TABLE `single_instance_template` ADD FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `single_instance_template` ADD COLUMN `concurrency` int(2) NULL COMMENT '并发数' AFTER `disk_size`;
