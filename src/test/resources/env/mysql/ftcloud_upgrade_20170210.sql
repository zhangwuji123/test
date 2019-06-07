INSERT INTO `sys_module` VALUES ('84', NULL, '扩展属性', 99, '/ciExtProperty', NULL, 'ciExtProperty');
CREATE TABLE `ci_ext_property` (
  `uuid` varchar(128) NOT NULL,
  `module` varchar(32) NOT NULL COMMENT '扩展表名称',
  `property` varchar(32) NOT NULL COMMENT '属性名称',
  `property_title` varchar(32) NOT NULL COMMENT '属性名称（显示名称）',
  `property_sort` int(11) NOT NULL COMMENT '属性排序',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_jf_ext` (
  `uuid` varchar(128) NOT NULL,
  `jf_uuid` varchar(128) NOT NULL COMMENT '机房编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `jf_uuid` (`jf_uuid`),
  CONSTRAINT `ci_jf_ext_ibfk_1` FOREIGN KEY (`jf_uuid`) REFERENCES `ci_jf` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_rack_ext` (
  `uuid` varchar(128) NOT NULL,
  `rack_uuid` varchar(128) NOT NULL COMMENT '机柜编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `rack_uuid` (`rack_uuid`),
  CONSTRAINT `ci_rack_ext_ibfk_1` FOREIGN KEY (`rack_uuid`) REFERENCES `ci_rack` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_gdhk_ext` (
  `uuid` varchar(128) NOT NULL,
  `gdhk_uuid` varchar(128) NOT NULL COMMENT '供电环控编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `gdhk_uuid` (`gdhk_uuid`),
  CONSTRAINT `ci_gdhk_ext_ibfk_1` FOREIGN KEY (`gdhk_uuid`) REFERENCES `ci_gdhk` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_physical_server_ext` (
  `uuid` varchar(128) NOT NULL,
  `physical_server_uuid` varchar(128) NOT NULL COMMENT '物理服务器编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `physical_server_uuid` (`physical_server_uuid`),
  CONSTRAINT `ci_physical_server_ext_ibfk_1` FOREIGN KEY (`physical_server_uuid`) REFERENCES `ci_physical_server` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_network_device_ext` (
  `uuid` varchar(128) NOT NULL,
  `network_device_uuid` varchar(128) NOT NULL COMMENT '网络设备编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `network_device_uuid` (`network_device_uuid`),
  CONSTRAINT `ci_network_device_ext_ibfk_1` FOREIGN KEY (`network_device_uuid`) REFERENCES `ci_network_device` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage_ext` (
  `uuid` varchar(128) NOT NULL,
  `storage_uuid` varchar(128) NOT NULL COMMENT '存储编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `storage_uuid` (`storage_uuid`),
  CONSTRAINT `ci_storage_ext_ibfk_1` FOREIGN KEY (`storage_uuid`) REFERENCES `ci_storage` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_cluster_ext` (
  `uuid` varchar(128) NOT NULL,
  `cluster_uuid` varchar(128) NOT NULL COMMENT '集群编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `ci_cluster_ext_ibfk_1` FOREIGN KEY (`cluster_uuid`) REFERENCES `ci_cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_logic_server_ext` (
  `uuid` varchar(128) NOT NULL,
  `logic_server_uuid` varchar(128) NOT NULL COMMENT '逻辑服务器编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `logic_server_uuid` (`logic_server_uuid`),
  CONSTRAINT `ci_logic_server_ext_ibfk_1` FOREIGN KEY (`logic_server_uuid`) REFERENCES `ci_logic_server` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_application_ext` (
  `uuid` varchar(128) NOT NULL,
  `application_uuid` varchar(128) NOT NULL COMMENT '应用系统编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `application_uuid` (`application_uuid`),
  CONSTRAINT `ci_application_ext_ibfk_1` FOREIGN KEY (`application_uuid`) REFERENCES `ci_application` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_middleware_ext` (
  `uuid` varchar(128) NOT NULL,
  `middleware_uuid` varchar(128) NOT NULL COMMENT '中间件编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `middleware_uuid` (`middleware_uuid`),
  CONSTRAINT `ci_middleware_ext_ibfk_1` FOREIGN KEY (`middleware_uuid`) REFERENCES `ci_middleware` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_database_ext` (
  `uuid` varchar(128) NOT NULL,
  `database_uuid` varchar(128) NOT NULL COMMENT '数据库编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `database_uuid` (`database_uuid`),
  CONSTRAINT `ci_database_ext_ibfk_1` FOREIGN KEY (`database_uuid`) REFERENCES `ci_database` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
