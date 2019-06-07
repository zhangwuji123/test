UPDATE sys_module SET `name` = '新开资源统计' where uuid = '37';
UPDATE sys_module SET `name` = '资源消费记录统计' where uuid = '56';

ALTER TABLE `physical_machine_deploy` DROP FOREIGN KEY `physical_machine_deploy_ibfk_1`;
ALTER TABLE `physical_machine_deploy`
DROP COLUMN `physical_machine_uuid`,
DROP INDEX `physical_machine_uuid`;
ALTER TABLE `physical_machine_deploy` ADD INDEX (`uuid`);
ALTER TABLE `physical_machine`
ADD COLUMN `physical_machine_deploy_uuid`  varchar(128) NULL AFTER `charging_rules_uuid`;
ALTER TABLE `physical_machine` ADD CONSTRAINT `physical_machine_ibfk_8` FOREIGN KEY (`physical_machine_deploy_uuid`) REFERENCES `physical_machine_deploy` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
