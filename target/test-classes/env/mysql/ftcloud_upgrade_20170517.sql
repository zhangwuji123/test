ALTER TABLE `report`
ADD COLUMN `business_uuid`  varchar(128) NULL COMMENT '业务类型UUID' AFTER `host_uuid`,
ADD COLUMN `business_name`  varchar(128) NULL COMMENT '业务类型名称' AFTER `business_uuid`;

CREATE TABLE `ip_range` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `start_ip` varchar(64) DEFAULT NULL COMMENT '起始ip',
  `end_ip` varchar(64) DEFAULT NULL COMMENT '结束ip',
  `network_uuid` varchar(128) DEFAULT NULL COMMENT '关联到网络',
  `hypervisor_server_container_uuid` varchar(128) DEFAULT NULL COMMENT '关联到链接配置',
  `network_scop_uuid` varchar(128) DEFAULT NULL COMMENT '关联到适配',
  PRIMARY KEY (`uuid`),
  KEY `ipr_net_fk_001` (`network_uuid`),
  KEY `ipr_hc_002` (`hypervisor_server_container_uuid`),
  CONSTRAINT `ipr_hc_002` FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `hypervisor_server_container` (`uuid`),
  CONSTRAINT `ipr_net_fk_001` FOREIGN KEY (`network_uuid`) REFERENCES `network` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `ip_range_relation` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `ip_range_uuid` varchar(128) DEFAULT NULL COMMENT 'ip段的唯一标识',
  `ip_pool_uuid` varchar(128) DEFAULT NULL COMMENT '对应ip池中ip的唯一标识',
  PRIMARY KEY (`uuid`),
  KEY `ip_pool_range_fk_001` (`ip_range_uuid`),
  KEY `ip_range_pool` (`ip_pool_uuid`),
  CONSTRAINT `ip_pool_range_fk_001` FOREIGN KEY (`ip_range_uuid`) REFERENCES `ip_range` (`uuid`),
  CONSTRAINT `ip_range_pool` FOREIGN KEY (`ip_pool_uuid`) REFERENCES `ip_pool` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ip和ip段关联表';

ALTER TABLE `network_scop`
ADD COLUMN `name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '适配名称对应后端虚拟化网络名称' AFTER `uuid`;