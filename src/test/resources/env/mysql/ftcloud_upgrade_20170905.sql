INSERT INTO `sysconfig` VALUES ('81',1 , '机房编码前缀', 'ci_jf_code', 'sy-jf-');
INSERT INTO `sysconfig` VALUES ('82',1 , '机柜编码前缀', 'ci_rack_code', 'fa-rak-');
INSERT INTO `sysconfig` VALUES ('83',1 , '供电环控编码前缀', 'ci_gdhk_code', 'sy-gdhl-');
INSERT INTO `sysconfig` VALUES ('84',1 , '物理服务器编码前缀', 'ci_physical_server_code', 'sy-psr-');
INSERT INTO `sysconfig` VALUES ('85',1 , '网络设备编码前缀', 'ci_network_device_code', 'ne-pnd-');
INSERT INTO `sysconfig` VALUES ('86',1 , '存储编码前缀', 'ci_storage_code', 'sy-qdd-');
INSERT INTO `sysconfig` VALUES ('87',1 , '集群编码前缀', 'ci_cluster_code', 'sy-clu-');
INSERT INTO `sysconfig` VALUES ('88',1 , '逻辑服务器编码前缀', 'ci_logic_server_code', 'sy-lsr-');
INSERT INTO `sysconfig` VALUES ('89',1 , '小型机子表编码前缀', 'ci_mini_server_code', 'sy-xj-');
INSERT INTO `sysconfig` VALUES ('90',1 , '虚拟机子表编码前缀', 'ci_vm_code', 'sys-vm-');
INSERT INTO `sysconfig` VALUES ('91',1 , '存储子表编码前缀', 'ci_storage_sub_code', 'sy-stor-');
INSERT INTO `sysconfig` VALUES ('92',1 , '应用系统编码前缀', 'ci_application_code', 'ap-sys-');
INSERT INTO `sysconfig` VALUES ('93',1 , '中间件编码前缀', 'ci_middleware_code', 'sy-mwa-');
INSERT INTO `sysconfig` VALUES ('94',1 , '数据库编码前缀', 'ci_database_code', 'sy-db-');
INSERT INTO `sysconfig` VALUES ('99',1 , '配置项关系编码前缀', 'ci_relation_code', 'cr');
INSERT INTO `sys_module` VALUES ('201',1 , NULL, '逻辑拓扑', 99, '/ciLogicTopo', NULL, 'ciLogicTopo');
INSERT INTO `sys_module` VALUES ('202',1 , NULL, '3D机房', 99, '/ciThreeD', NULL, 'ciThreeD');
INSERT INTO `sys_module` VALUES ('203',1 , NULL, '机房配置项', 99, '/ciJf', NULL, 'ciJf');
INSERT INTO `sys_module` VALUES ('204',1 , NULL, '机柜', 99, '/ciRack', NULL, 'ciRack');
INSERT INTO `sys_module` VALUES ('205',1 , NULL, '供电环控配置项', 99, '/ciGdhk', NULL, 'ciGdhk');
INSERT INTO `sys_module` VALUES ('206',1 , NULL, '物理服务器配置项', 99, '/ciPhysicalServer', NULL, 'ciPhysicalServer');
INSERT INTO `sys_module` VALUES ('207',1 , NULL, '网络设备配置项', 99, '/ciNetworkDevice', NULL, 'ciNetworkDevice');
INSERT INTO `sys_module` VALUES ('208',1 , NULL, '存储配置项', 99, '/ciStorage', NULL, 'ciStorage');
INSERT INTO `sys_module` VALUES ('209',1 , NULL, '集群配置项', 99, '/ciCluster', NULL, 'ciCluster');
INSERT INTO `sys_module` VALUES ('210',1 , NULL, '逻辑服务器配置项', 99, '/ciLogicServer', NULL, 'ciLogicServer');
INSERT INTO `sys_module` VALUES ('211',1 , NULL, '小机配置项', 99, '/ciMiniServer', NULL, 'ciMiniServer');
INSERT INTO `sys_module` VALUES ('212',1 , NULL, '虚拟机机配置项', 99, '/ciVm', NULL, 'ciVm');
INSERT INTO `sys_module` VALUES ('213',1 , NULL, '存储子表', 99, '/ciStorageSub', NULL, 'ciStorageSub');
INSERT INTO `sys_module` VALUES ('214',1 , NULL, '应用系统配置项', 99, '/ciApplication', NULL, 'ciApplication');
INSERT INTO `sys_module` VALUES ('215',1 , NULL, '中间件配置置项', 99, '/ciMiddleware', NULL, 'ciMiddleware');
INSERT INTO `sys_module` VALUES ('216',1 , NULL, '数据库配置项', 99, '/ciDataBase', NULL, 'ciDataBase');
INSERT INTO `sys_module` VALUES ('217',1 , NULL, '配置项关系', 99, '/ciRelation', NULL, 'ciRelation');
INSERT INTO `sys_module` VALUES ('218',1 , NULL, '变更记录', 99, '/ciLog', NULL, 'ciLog');
INSERT INTO `sys_module` VALUES ('219',1 , NULL, '扩展属性', 99, '/ciExtProperty', NULL, 'ciExtProperty');
CREATE TABLE `ci_jf` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `location` varchar(80) NOT NULL COMMENT '地点',
  `floor` int(2) NOT NULL COMMENT '楼层',
  `area` double(11,2) DEFAULT NULL COMMENT '面积',
  `description` varchar(80) DEFAULT NULL COMMENT '主要用途',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_jf_ext` (
  `uuid` varchar(128) NOT NULL,
  `jf_uuid` varchar(128) NOT NULL COMMENT '机房编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `jf_uuid` (`jf_uuid`) USING BTREE,
  CONSTRAINT `ci_jf_ext_ibfk_1` FOREIGN KEY (`jf_uuid`) REFERENCES `ci_jf` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_jf_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `location` varchar(80) NOT NULL COMMENT '地点',
  `floor` int(2) NOT NULL COMMENT '楼层',
  `area` double(11,2) DEFAULT NULL COMMENT '面积',
  `description` varchar(80) DEFAULT NULL COMMENT '主要用途',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_rack` (
  `uuid` varchar(128) NOT NULL,
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL,
  `position` varchar(20) NOT NULL,
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `model` varchar(64) DEFAULT NULL COMMENT '设备型号',
  `power1` varchar(64) DEFAULT NULL COMMENT '第一路电源',
  `power2` varchar(64) DEFAULT NULL COMMENT '第二路电源',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) DEFAULT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_rack_ext` (
  `uuid` varchar(128) NOT NULL,
  `rack_uuid` varchar(128) NOT NULL COMMENT '机柜编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `rack_uuid` (`rack_uuid`) USING BTREE,
  CONSTRAINT `ci_rack_ext_ibfk_1` FOREIGN KEY (`rack_uuid`) REFERENCES `ci_rack` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_rack_record` (
  `uuid` varchar(128) NOT NULL,
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL,
  `position` varchar(20) NOT NULL,
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `model` varchar(64) DEFAULT NULL COMMENT '设备型号',
  `power1` varchar(64) DEFAULT NULL COMMENT '第一路电源',
  `power2` varchar(64) DEFAULT NULL COMMENT '第二路电源',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) DEFAULT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `jf_name` varchar(64) DEFAULT NULL COMMENT '机房名称',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_gdhk` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `product_no` varchar(16) DEFAULT NULL COMMENT '产品编号',
  `fms` varchar(512) DEFAULT NULL COMMENT 'FMS编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `sn` varchar(64) DEFAULT NULL COMMENT '序列号',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_gdhk_ext` (
  `uuid` varchar(128) NOT NULL,
  `gdhk_uuid` varchar(128) NOT NULL COMMENT '供电环控编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `gdhk_uuid` (`gdhk_uuid`) USING BTREE,
  CONSTRAINT `ci_gdhk_ext_ibfk_1` FOREIGN KEY (`gdhk_uuid`) REFERENCES `ci_gdhk` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_gdhk_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `product_no` varchar(16) DEFAULT NULL COMMENT '产品编号',
  `fms` varchar(512) DEFAULT NULL COMMENT 'FMS编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `sn` varchar(64) DEFAULT NULL COMMENT '序列号',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `jf_name` varchar(64) DEFAULT NULL COMMENT '机房名称',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_physical_server` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `fms` varchar(512) DEFAULT NULL COMMENT 'FMS编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `sn` varchar(64) NOT NULL COMMENT '序列号',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `config_detail` varchar(256) DEFAULT NULL COMMENT '配置详情',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号',
  `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机柜编号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_physical_server_ext` (
  `uuid` varchar(128) NOT NULL,
  `physical_server_uuid` varchar(128) NOT NULL COMMENT '物理服务器编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `physical_server_uuid` (`physical_server_uuid`) USING BTREE,
  CONSTRAINT `ci_physical_server_ext_ibfk_1` FOREIGN KEY (`physical_server_uuid`) REFERENCES `ci_physical_server` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_physical_server_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `fms` varchar(512) DEFAULT NULL COMMENT 'FMS编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `sn` varchar(64) NOT NULL COMMENT '序列号',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `config_detail` varchar(256) DEFAULT NULL COMMENT '配置详情',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `jf_name` varchar(64) DEFAULT NULL COMMENT '机房名称',
  `rack_name` varchar(64) DEFAULT NULL COMMENT '机柜名称',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_network_device` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `sn` varchar(64) NOT NULL COMMENT '序列号',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `ios_version` varchar(256) DEFAULT NULL COMMENT 'IOS版本',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `network_type` varchar(64) DEFAULT NULL COMMENT '网络类别',
  `network_area` varchar(64) DEFAULT NULL COMMENT '网络区域',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号',
  `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机柜编号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_network_device_ext` (
  `uuid` varchar(128) NOT NULL,
  `network_device_uuid` varchar(128) NOT NULL COMMENT '网络设备编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `network_device_uuid` (`network_device_uuid`) USING BTREE,
  CONSTRAINT `ci_network_device_ext_ibfk_1` FOREIGN KEY (`network_device_uuid`) REFERENCES `ci_network_device` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_network_device_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `sn` varchar(64) NOT NULL COMMENT '序列号',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `ios_version` varchar(256) DEFAULT NULL COMMENT 'IOS版本',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `network_type` varchar(64) DEFAULT NULL COMMENT '网络类别',
  `network_area` varchar(64) DEFAULT NULL COMMENT '网络区域',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `jf_name` varchar(64) DEFAULT NULL COMMENT '机房名称',
  `rack_name` varchar(64) DEFAULT NULL COMMENT '机柜名称',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `sn` varchar(64) DEFAULT NULL COMMENT '序列号',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `alcapacity` varchar(8) DEFAULT NULL COMMENT '设备容量',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号',
  `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机柜编号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage_ext` (
  `uuid` varchar(128) NOT NULL,
  `storage_uuid` varchar(128) NOT NULL COMMENT '存储编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `storage_uuid` (`storage_uuid`) USING BTREE,
  CONSTRAINT `ci_storage_ext_ibfk_1` FOREIGN KEY (`storage_uuid`) REFERENCES `ci_storage` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `position` varchar(20) NOT NULL COMMENT '位置',
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `sn` varchar(64) DEFAULT NULL COMMENT '序列号',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `alcapacity` varchar(8) DEFAULT NULL COMMENT '设备容量',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `jf_name` varchar(64) DEFAULT NULL COMMENT '机房名称',
  `rack_name` varchar(64) DEFAULT NULL COMMENT '机柜名称',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_cluster` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `hostnames` varchar(128) DEFAULT NULL COMMENT '所在主机',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_cluster_ext` (
  `uuid` varchar(128) NOT NULL,
  `cluster_uuid` varchar(128) NOT NULL COMMENT '集群编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `cluster_uuid` (`cluster_uuid`) USING BTREE,
  CONSTRAINT `ci_cluster_ext_ibfk_1` FOREIGN KEY (`cluster_uuid`) REFERENCES `ci_cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_cluster_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `hostnames` varchar(128) DEFAULT NULL COMMENT '所在主机',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_logic_server` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `cpu_num` varchar(64) DEFAULT NULL COMMENT 'CPU数量',
  `memsize` varchar(64) DEFAULT NULL COMMENT '内存大小',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `physical_server_uuid` varchar(128) DEFAULT NULL COMMENT '物理服务器编号',
  `storage_uuid` varchar(128) DEFAULT NULL COMMENT '存储编号',
  `physical_server_sn` varchar(64) DEFAULT NULL COMMENT '所属设备序列号',
  `storage_sn` varchar(64) DEFAULT NULL COMMENT '所属存储序列号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_logic_server_ext` (
  `uuid` varchar(128) NOT NULL,
  `logic_server_uuid` varchar(128) NOT NULL COMMENT '逻辑服务器编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `logic_server_uuid` (`logic_server_uuid`) USING BTREE,
  CONSTRAINT `ci_logic_server_ext_ibfk_1` FOREIGN KEY (`logic_server_uuid`) REFERENCES `ci_logic_server` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_logic_server_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `cpu_num` varchar(64) DEFAULT NULL COMMENT 'CPU数量',
  `memsize` varchar(64) DEFAULT NULL COMMENT '内存大小',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `physical_server_sn` varchar(64) DEFAULT NULL COMMENT '设备序列号',
  `storage_sn` varchar(64) DEFAULT NULL COMMENT '存储序列号',
  `applications` text COMMENT '应用系统',
  `clusters` text COMMENT '集群',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_mini_server` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `cpu_num` varchar(64) DEFAULT NULL COMMENT 'CPU数量',
  `memsize` varchar(64) DEFAULT NULL COMMENT '内存大小',
  `project_chief` varchar(16) DEFAULT NULL COMMENT '项目负责人',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `physical_server_uuid` varchar(128) DEFAULT NULL COMMENT '物理服务器编号',
  `physical_server_sn` varchar(64) DEFAULT NULL COMMENT '所属设备序列号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_mini_server_ext` (
  `uuid` varchar(128) NOT NULL,
  `mini_server_uuid` varchar(128) NOT NULL COMMENT '小机编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `mini_server_uuid` (`mini_server_uuid`) USING BTREE,
  CONSTRAINT `ci_mini_server_ext_ibfk_1` FOREIGN KEY (`mini_server_uuid`) REFERENCES `ci_mini_server` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_mini_server_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `model` varchar(64) NOT NULL COMMENT '型号',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `cpu_num` varchar(64) DEFAULT NULL COMMENT 'CPU数量',
  `memsize` varchar(64) DEFAULT NULL COMMENT '内存大小',
  `project_chief` varchar(16) DEFAULT NULL COMMENT '项目负责人',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `physical_server_sn` varchar(64) DEFAULT NULL COMMENT '设备序列号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_vm` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `cluster_name` varchar(64) DEFAULT NULL COMMENT '集群名称',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `os` varchar(64) DEFAULT NULL COMMENT '操作系统',
  `cpu_num` varchar(64) DEFAULT NULL COMMENT 'CPU',
  `memsize` varchar(64) DEFAULT NULL COMMENT 'MEM',
  `sys_storage` varchar(64) DEFAULT NULL COMMENT '系统存储',
  `data_storage` varchar(64) DEFAULT NULL COMMENT '数据存储',
  `application` varchar(64) DEFAULT NULL COMMENT '应用系统',
  `app_maintainer` varchar(64) DEFAULT NULL COMMENT '应用维护人员',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_vm_ext` (
  `uuid` varchar(128) NOT NULL,
  `vm_uuid` varchar(128) NOT NULL COMMENT '虚机编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `vm_uuid` (`vm_uuid`) USING BTREE,
  CONSTRAINT `ci_vm_ext_ibfk_1` FOREIGN KEY (`vm_uuid`) REFERENCES `ci_vm` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_vm_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `cluster_name` varchar(64) DEFAULT NULL COMMENT '集群名称',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `os` varchar(64) DEFAULT NULL COMMENT '操作系统',
  `cpu_num` varchar(64) DEFAULT NULL COMMENT 'CPU',
  `memsize` varchar(64) DEFAULT NULL COMMENT 'MEM',
  `sys_storage` varchar(64) DEFAULT NULL COMMENT '系统存储',
  `data_storage` varchar(64) DEFAULT NULL COMMENT '数据存储',
  `application` varchar(64) DEFAULT NULL COMMENT '应用系统',
  `app_maintainer` varchar(64) DEFAULT NULL COMMENT '应用维护人员',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage_sub` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `capacity` varchar(64) DEFAULT NULL COMMENT '容量',
  `model` varchar(64) DEFAULT NULL COMMENT '设备型号',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `storage_uuid` varchar(128) DEFAULT NULL COMMENT '存储编号',
  `storage_sn` varchar(64) DEFAULT NULL COMMENT '所属存储序列号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage_sub_ext` (
  `uuid` varchar(128) NOT NULL,
  `storage_sub_uuid` varchar(128) NOT NULL COMMENT '存储字表编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `storage_sub_uuid` (`storage_sub_uuid`) USING BTREE,
  CONSTRAINT `ci_storage_sub_ext_ibfk_1` FOREIGN KEY (`storage_sub_uuid`) REFERENCES `ci_storage_sub` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_storage_sub_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `capacity` varchar(64) DEFAULT NULL COMMENT '容量',
  `model` varchar(64) DEFAULT NULL COMMENT '设备型号',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `storage_sn` varchar(128) DEFAULT NULL COMMENT '存储',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_application` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `deployment_type` varchar(16) NOT NULL COMMENT '部署类型',
  `system_level` varchar(4) NOT NULL COMMENT '系统级别',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_application_ext` (
  `uuid` varchar(128) NOT NULL,
  `application_uuid` varchar(128) NOT NULL COMMENT '应用系统编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `application_uuid` (`application_uuid`) USING BTREE,
  CONSTRAINT `ci_application_ext_ibfk_1` FOREIGN KEY (`application_uuid`) REFERENCES `ci_application` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_application_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `deployment_type` varchar(16) NOT NULL COMMENT '部署类型',
  `system_level` varchar(4) NOT NULL COMMENT '系统级别',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_middleware` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `middleware_name` varchar(20) NOT NULL COMMENT '中间件名称',
  `hostnames` varchar(128) DEFAULT NULL COMMENT '所在主机',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_middleware_ext` (
  `uuid` varchar(128) NOT NULL,
  `middleware_uuid` varchar(128) NOT NULL COMMENT '中间件编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `middleware_uuid` (`middleware_uuid`) USING BTREE,
  CONSTRAINT `ci_middleware_ext_ibfk_1` FOREIGN KEY (`middleware_uuid`) REFERENCES `ci_middleware` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_middleware_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `middleware_name` varchar(20) NOT NULL COMMENT '中间件名称',
  `hostnames` varchar(128) DEFAULT NULL COMMENT '所在主机',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `applications` text COMMENT '应用系统',
  `clusters` text COMMENT '集群',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_database` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `sid` varchar(64) NOT NULL COMMENT '实例名称',
  `hostnames` varchar(128) DEFAULT NULL COMMENT '所在主机',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_database_ext` (
  `uuid` varchar(128) NOT NULL,
  `database_uuid` varchar(128) NOT NULL COMMENT '数据库编号',
  `property` varchar(32) NOT NULL COMMENT '扩展属性',
  `value` varchar(512) DEFAULT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`uuid`),
  KEY `database_uuid` (`database_uuid`) USING BTREE,
  CONSTRAINT `ci_database_ext_ibfk_1` FOREIGN KEY (`database_uuid`) REFERENCES `ci_database` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_database_record` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `sid` varchar(64) NOT NULL COMMENT '实例名称',
  `hostnames` varchar(128) DEFAULT NULL COMMENT '所在主机',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `ci_desc` text COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(16) DEFAULT NULL COMMENT '操作状态',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `applications` text COMMENT '应用系统',
  `clusters` text COMMENT '集群',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_relation` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `sn` varchar(64) DEFAULT NULL COMMENT '序号',
  `ci_a` varchar(64) NOT NULL COMMENT '配置项A',
  `ci_b` varchar(64) NOT NULL COMMENT '配置项B',
  `rl_desc` text COMMENT '配置项关系描述',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_ext_property` (
  `uuid` varchar(128) NOT NULL,
  `module` varchar(32) NOT NULL COMMENT '扩展表名称',
  `property` varchar(32) NOT NULL COMMENT '属性名称',
  `property_title` varchar(32) NOT NULL COMMENT '属性名称（显示名称）',
  `property_sort` int(11) NOT NULL COMMENT '属性排序',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_log` (
  `uuid` varchar(128) NOT NULL,
  `realname` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `operation_uuid` varchar(128) DEFAULT NULL COMMENT '操作的编号，只有删除时记录',
  `name` varchar(128) DEFAULT NULL COMMENT '配置项名称',
  `type` int(2) NOT NULL COMMENT '类型，1：机房；2：机柜；3：供电环控；4：物理服务器；5：网络设备；6：存储；7：集群；8：逻辑服务器；9：应用系统；10：中间件；11：数据库；12：关系',
  `operation` int(2) NOT NULL COMMENT '操作，1：添加；2：修改，3：删除，4：导入',
  `result` int(2) DEFAULT NULL COMMENT '操作结果，1：成功；0：失败',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
