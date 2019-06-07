INSERT INTO `sys_module` (`uuid`, `visible`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('95', '1', NULL, '系统工具', '99', '/util', NULL, 'util');
ALTER TABLE `sys_user`
MODIFY COLUMN `type`  int(2) UNSIGNED NULL DEFAULT NULL COMMENT '用户类别：1、管理员，2、普通用户,3自服务外部，4企业管理员，5 VDC管理员，6 VPC管理员，7 VPC用户' AFTER `password`;
delete from `system_service_type`;
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('1', 'talker', 'Talker');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('10', '3-6', 'H3C');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('11', '3-7', 'ChinaCache');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('12', '3-8', 'OVIRT');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('13', '3-9', 'HALSIGN');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('14', '3-10', 'OceanStore');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('15', '3-11', 'IPMI');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('16', '3-12', 'LoadBalance');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('17', '3-13', 'OO');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('18', '3-14', 'PowerVC');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('19', '3-15', 'HMC');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('2', 'resource-job', 'ResourceJob');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('20', '3-16', 'QingCloud');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('21', '3-17', 'PATCH');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('22', '3-18', 'Check');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('3', '1', 'OpenStack');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('4', '2', 'CloudStack');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('5', '3-1', 'VMware');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('6', '3-2', 'XENSERVER');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('7', '3-3', 'KVM');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('8', '3-4', 'Hyper-V');
INSERT INTO `system_service_type` (`uuid`, `service_id`, `service_name`) VALUES ('9', '3-5', 'FusionCompute');

INSERT INTO `sys_module` VALUES ('101', 1, NULL, 'VDC', 99, '/vdc', NULL, 'vdc');
CREATE TABLE `vdc` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `isolation_mode` int(2) DEFAULT NULL COMMENT '隔离状态（0：专享；1：共享）',
  `description` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  CONSTRAINT `vdc_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `vdc_resource` (
  `uuid` varchar(128) NOT NULL,
  `isolation_mode` int(2) DEFAULT NULL COMMENT '隔离状态（0：专享；1：共享）',
  `resource_type` int(2) DEFAULT NULL,
  `resource_uuid` varchar(128) DEFAULT NULL,
  `vdc_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `vdc_uuid` (`vdc_uuid`),
  CONSTRAINT `vdc_resource_ibfk_1` FOREIGN KEY (`vdc_uuid`) REFERENCES `vdc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `vdc_quota` (
  `uuid` varchar(128) NOT NULL,
  `quota_type` int(2) DEFAULT NULL,
  `quota_num` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `vdc_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `vdc_uuid` (`vdc_uuid`),
  CONSTRAINT `vdc_quota_ibfk_1` FOREIGN KEY (`vdc_uuid`) REFERENCES `vdc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `instance_volume_vm_snapshot` (
  `uuid` varchar(128) NOT NULL,
  `instance_volume_uuid` varchar(128) DEFAULT NULL,
  `vm_snapshot_uuid` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `instance_volume_uuid` (`instance_volume_uuid`) USING BTREE,
  KEY `snapshot_uuid` (`vm_snapshot_uuid`) USING BTREE,
  CONSTRAINT `instance_volume_vm_snapshot_ibfk_1` FOREIGN KEY (`instance_volume_uuid`) REFERENCES `instance_volume_info` (`uuid`),
  CONSTRAINT `instance_volume_vm_snapshot_ibfk_2` FOREIGN KEY (`vm_snapshot_uuid`) REFERENCES `vm_snapshot` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
