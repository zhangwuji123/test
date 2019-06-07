CREATE TABLE `ci_cluster` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `ip` varchar(64) NOT NULL COMMENT 'IP地址',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_application` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `deployment_type` varchar(16) NOT NULL COMMENT '部署类型',
  `system_level` varchar(4) NOT NULL COMMENT '系统级别',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_logic_server` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `cpu_num` int(2) NOT NULL COMMENT 'CPU数量',
  `memsize` int(2) NOT NULL COMMENT '内存大小',
  `device_sn` varchar(64) NOT NULL COMMENT '所在设备序列号',
  `storage_sn` varchar(64) DEFAULT COMMENT '外接存储序列号',
  `ip` varchar(64) NOT NULL COMMENT 'IP地址',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_middleware` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `middleware_name` varchar(20) NOT NULL COMMENT '中间件名称',
  `hostname` varchar(64) NOT NULL COMMENT '所在主机',
  `ip` varchar(64) NOT NULL COMMENT 'IP地址',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_database` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `sid` varchar(16) NOT NULL COMMENT '实例名称',
  `hostname` varchar(64) NOT NULL COMMENT '所在主机',
  `ip` varchar(64) NOT NULL COMMENT 'IP地址',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
