CREATE TABLE `iso` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `path` varchar(1000) DEFAULT NULL COMMENT 'iso路径',
  `type` int(4) DEFAULT NULL COMMENT '类型（1系统，2其他）',
  `size` double(11,0) DEFAULT NULL COMMENT '大小（G）',
  `last_modify_time` datetime DEFAULT NULL COMMENT '上一次修改时间',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外键',
  `storage_foreign_ref` varchar(128) DEFAULT NULL COMMENT '存储外键',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '关联集群',
  `hypervisor_server_container_uuid` varchar(128) DEFAULT NULL COMMENT '关联链接配置',
  PRIMARY KEY (`uuid`),
  KEY `fk_cluster_0001` (`cluster_uuid`),
  KEY `fk_hy_c_uuid_0001` (`hypervisor_server_container_uuid`),
  CONSTRAINT `fk_cluster_0001` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `fk_hy_c_uuid_0001` FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `hypervisor_server_container` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('95', NULL, 'ISO管理', '99', '/iso', NULL, 'iso');

CREATE TABLE `cd_rom` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '挂载时间',
  `state` int(4) DEFAULT NULL COMMENT '状态 ： 0 异常，1挂载中，2已挂载，3解挂中，4 未挂载',
  `iso_uuid` varchar(128) DEFAULT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_iso_ref_031` (`iso_uuid`),
  KEY `fk_ins_ref_031` (`instance_uuid`),
  CONSTRAINT `fk_ins_ref_031` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`),
  CONSTRAINT `fk_iso_ref_031` FOREIGN KEY (`iso_uuid`) REFERENCES `iso` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

