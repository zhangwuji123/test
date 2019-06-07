INSERT INTO `sys_module` VALUES ('103', 1, NULL, 'VPC', 99, '/vpc', NULL, 'vpc');
CREATE TABLE `vpc` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL,
  `isolation_mode` int(2) DEFAULT NULL COMMENT '隔离状态（0：专享；1：共享）',
  `state` int(2) DEFAULT NULL COMMENT '状态（0：删除；1：正常）',
  `description` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `vdc_uuid` varchar(128) DEFAULT NULL,
  `user_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `vdc_uuid` (`vdc_uuid`),
  KEY `user_uuid` (`user_uuid`),
  CONSTRAINT `vpc_ibfk_1` FOREIGN KEY (`vdc_uuid`) REFERENCES `vdc` (`uuid`),
  CONSTRAINT `vpc_ibfk_2` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `vpc_resource` (
  `uuid` varchar(128) NOT NULL,
  `isolation_mode` int(2) DEFAULT NULL COMMENT '隔离状态（0：专享；1：共享）',
  `resource_type` int(2) DEFAULT NULL,
  `resource_uuid` varchar(128) DEFAULT NULL,
  `vpc_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `vpc_uuid` (`vpc_uuid`),
  CONSTRAINT `vpc_resource_ibfk_1` FOREIGN KEY (`vpc_uuid`) REFERENCES `vpc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `vpc_quota` (
  `uuid` varchar(128) NOT NULL,
  `quota_type` int(2) DEFAULT NULL,
  `quota_num` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `vpc_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `vpc_uuid` (`vpc_uuid`),
  CONSTRAINT `vpc_quota_ibfk_1` FOREIGN KEY (`vpc_uuid`) REFERENCES `vpc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


