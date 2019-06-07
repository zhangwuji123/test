CREATE TABLE `security_groups` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `vpc_id` varchar(128) DEFAULT NULL COMMENT 'vpc表ID',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT 'openstack端ID',
  `create_time` date DEFAULT NULL,
  `state` int(8) DEFAULT '1' COMMENT '0:异常 1：操作中 2：正常 3：已删除',
  PRIMARY KEY (`uuid`),
  KEY `FK_VDC_1` (`vpc_id`),
  CONSTRAINT `FK_VDC_1` FOREIGN KEY (`vpc_id`) REFERENCES `vpc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `security_group_rules` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `direction` varchar(32) DEFAULT NULL COMMENT '方向（入口/出口) Ingress or egress',
  `protocol` varchar(32) DEFAULT NULL COMMENT '协议（icmp, tcp, udp, or null）',
  `ethertype` varchar(32) DEFAULT NULL COMMENT '以太网类型（IPV4/IPV6）',
  `port_range_min` int(8) DEFAULT NULL COMMENT '最小端口',
  `port_range_max` int(8) DEFAULT NULL COMMENT '最大端口',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `security_group_id` varchar(128) DEFAULT NULL COMMENT '安全组ID',
  `create_time` date DEFAULT NULL,
  `state` int(8) DEFAULT '1' COMMENT '0:异常 1：操作中 2：正常 3：已删除',
  `is_default` int(8) DEFAULT '0' COMMENT '是否默认规则  0:否 1：是',
  PRIMARY KEY (`uuid`),
  KEY `FK_SECURITY_GROUP_1` (`security_group_id`),
  CONSTRAINT `FK_SECURITY_GROUP_1` FOREIGN KEY (`security_group_id`) REFERENCES `security_groups` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `instance_security_group` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `instance_uuid` varchar(128) NOT NULL COMMENT '云主机编号',
  `security_group_uuid` varchar(128) NOT NULL COMMENT '安全组编号',
  PRIMARY KEY (`uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  KEY `security_group_uuid` (`security_group_uuid`),
  CONSTRAINT `instance_security_group_ibfk_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`),
  CONSTRAINT `instance_security_group_ibfk_2` FOREIGN KEY (`security_group_uuid`) REFERENCES `security_groups` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
