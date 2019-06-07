CREATE TABLE `network` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一 标识',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `cidr` varchar(18) DEFAULT NULL COMMENT 'CIDR',
  `gateway` varchar(15) DEFAULT NULL COMMENT '网关',
  `ip_assign_mode` int(4) DEFAULT NULL COMMENT 'IP分配方式：1.静态，2.动态',
  `ip_range` varchar(512) DEFAULT NULL COMMENT '可以有多个段组成，每个段由起始ip组成，中间由‘-’分割；多个段之间由‘，’分割，例如：192.168.1.2-192.168.1.10，192.168.1.100-192.168.1.200',
  `dns` varchar(128) DEFAULT NULL,
  `state` int(4) DEFAULT NULL COMMENT '0、不可用，1、可用，2、操作中',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新网络表';
CREATE TABLE `network_device` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `physical_device` varchar(255) DEFAULT NULL COMMENT '物理设备名称',
  `hypervisor_server_container_uuid` varchar(128) DEFAULT NULL COMMENT '连接配置uuid',
  `maintenance_type` int(4) DEFAULT NULL COMMENT '维护方式 1：人工；2：同步',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外键',
  `state` int(1) DEFAULT NULL COMMENT '1、正常，2、已删除',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `network_scop` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `network_uuid` varchar(128) DEFAULT NULL COMMENT '关联网络的uuid',
  `hypervisor_server_container_uuid` varchar(128) DEFAULT NULL COMMENT '关联链接配置的uuid',
  `net_device_type` int(4) DEFAULT NULL COMMENT '网络设备类型：vlan(1) , vxlan(2)',
  `net_device_uuid` varchar(128) DEFAULT NULL COMMENT '网络设备uuid',
  `vlanid` int(4) DEFAULT NULL COMMENT 'vlanid',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外键',
  `state` int(4) DEFAULT NULL COMMENT '状态：0.异常；1.正常；2.已删除；3.创建中；4.修改中；5.删除中',
  PRIMARY KEY (`uuid`),
  KEY `network_uuid` (`network_uuid`),
  KEY `hypervisor_server_container_uuid` (`hypervisor_server_container_uuid`),
  KEY `net_device_uuid` (`net_device_uuid`),
  CONSTRAINT `network_scop_ibfk_1` FOREIGN KEY (`network_uuid`) REFERENCES `network` (`uuid`),
  CONSTRAINT `network_scop_ibfk_2` FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `hypervisor_server_container` (`uuid`),
  CONSTRAINT `network_scop_ibfk_3` FOREIGN KEY (`net_device_uuid`) REFERENCES `network_device` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('80', NULL, '网络', '99', '/networks', NULL, 'networks');
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('81', NULL, '网络设备', '99', '/networkDevice', NULL, 'networkDevice');

