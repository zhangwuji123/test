ALTER TABLE `single_instance_template` ADD COLUMN `software_uuid` varchar(128) DEFAULT NULL COMMENT '软件编号' AFTER `charging_rules_uuid`;
ALTER TABLE `single_instance_template` ADD CONSTRAINT `single_instance_template_software_ibfk` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`);

INSERT INTO `sysconfig` VALUES ('39', '邮件发送开关', 'mail_on_off', '0');

ALTER TABLE `disk_snapshot` DROP FOREIGN KEY `fkey_snap_disk01`;
ALTER TABLE `instance_volume_info`
ADD COLUMN `disk_type_uuid`  varchar(128) NULL COMMENT 'OpenStack的时候磁盘类型' AFTER `type`;
ALTER TABLE `disk_snapshot`
CHANGE COLUMN `disk_uuid` `data_disk_uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `updated_at`;
ALTER TABLE `disk_snapshot` ADD CONSTRAINT `fk_data_disk_01` FOREIGN KEY (`data_disk_uuid`) REFERENCES `data_disk` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `instance_volume_info` ADD CONSTRAINT `fk_reference_23_volume_disk_type_key` FOREIGN KEY (`disk_type_uuid`) REFERENCES `disk_types` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
delete from sys_module where uuid = '24';
UPDATE `sys_module` SET `name`='块存储快照管理' WHERE (`uuid`='25');



CREATE TABLE `system_service_type` (
  `uuid` varchar(128) NOT NULL,
  `service_id` varchar(64) DEFAULT NULL COMMENT '服务标识（talker,ftstack-vmware等)',
  `service_name` varchar(64) DEFAULT NULL COMMENT '服务名称，例如：ftcloud-adapter-vmware',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('1', 'talker', '应答任务');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('2', 'resource-job', '资源JOB');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('3', '1', 'OpenStack');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('4', '2', 'CloudStack');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('5', '3-1', 'Vmware');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('6', '3-2', 'XENSERVER');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('7', '3-3', 'KVM');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('8', '3-4', 'HyperV');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('9', '3-5', 'FusionCompute');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('10', '3-6', 'H3C');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('11', '3-7', '蓝讯');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('12', '3-8', 'OVIRT');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('13', '3-9', 'HALSIGN');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('14', '3-10', '华为云存储');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('15', '3-11', '物理机');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('16', '3-12', '负载均衡');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('17', '3-13', 'HP-OO');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('18', '3-14', 'POWERVC');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('19', '3-15', 'HMC');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('20', '3-16', '青云');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('21', '3-17', '补丁');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('22', '3-18', '合规检查');


INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('94', NULL, '系统服务管理', '99', '/systemService', NULL, 'systemService');



