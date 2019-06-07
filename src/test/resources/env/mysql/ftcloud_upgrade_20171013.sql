ALTER TABLE `software_relation` ADD COLUMN `resource_ip`  varchar(128) NULL COMMENT '资源IP地址' AFTER `resource_name`;
CREATE TABLE `vlanid_pool` (
  `uuid` varchar(128) NOT NULL,
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `network_device_uuid` varchar(128) DEFAULT NULL COMMENT '关联网络设备',
  `vlanid_range_start` int(11) DEFAULT NULL COMMENT 'vlanid段开始',
  `vlanid_range_end` int(11) DEFAULT NULL COMMENT 'vlanid段结束',
  `state` int(4) DEFAULT NULL COMMENT '0已删除，1可用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `fk_device_vlan_id_sdf` (`network_device_uuid`),
  CONSTRAINT `fk_device_vlan_id_sdf` FOREIGN KEY (`network_device_uuid`) REFERENCES `network_device` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` (`uuid`, `visible`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('104', '1', NULL, 'VLANID池', '99', '/vlanidPool', NULL, 'vlanidPool');
