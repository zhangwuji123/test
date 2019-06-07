CREATE TABLE `router_info` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `state` int(4) DEFAULT NULL COMMENT '状态：0异常、1创建中、2正常、3删除中、4已删除、5关联网络中、6移除网络中',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `external_network_uuid` varchar(128) DEFAULT NULL COMMENT '外部网络UUID',
  `external_network_scop_uuid` varchar(128) DEFAULT NULL COMMENT '外部网络适配Uuid',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `create_time` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_network_router_1` (`external_network_uuid`),
  KEY `fk_network_scop_router_1` (`external_network_scop_uuid`),
  CONSTRAINT `fk_network_router_1` FOREIGN KEY (`external_network_uuid`) REFERENCES `network` (`uuid`),
  CONSTRAINT `fk_network_scop_router_1` FOREIGN KEY (`external_network_scop_uuid`) REFERENCES `network_scop` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `router_network_scop_relation` (
  `uuid` varchar(128) NOT NULL,
  `router_info_uuid` varchar(128) DEFAULT NULL COMMENT '关联路由',
  `network_scop_uuid` varchar(128) DEFAULT NULL COMMENT '关联网络适配',
  PRIMARY KEY (`uuid`),
  KEY `fk_router_1` (`router_info_uuid`),
  KEY `fk_network_scop_1` (`network_scop_uuid`),
  CONSTRAINT `fk_network_scop_1` FOREIGN KEY (`network_scop_uuid`) REFERENCES `network_scop` (`uuid`),
  CONSTRAINT `fk_router_1` FOREIGN KEY (`router_info_uuid`) REFERENCES `router_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `network_scop`
ADD COLUMN `type`  int(4) NULL COMMENT '网络类型：1内部，2外部' AFTER `vlanid`;

ALTER TABLE `vpc_resource`
MODIFY COLUMN `resource_type`  int(2) NULL DEFAULT NULL COMMENT '1主机，2物理机，3小机，4路由器，5外部网络，6私有网络, 7云主机，8块存储' AFTER `isolation_mode`;

