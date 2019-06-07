INSERT INTO `sys_module` VALUES ('65', NULL, '存储配置项', 99, '/ciStorage', NULL, 'ciStorage');
INSERT INTO `sys_module` VALUES ('67', NULL, '供电环控配置项', 99, '/ciGdhk', NULL, 'ciGdhk');
INSERT INTO `sys_module` VALUES ('68', NULL, '机房配置项', 99, '/ciJf', NULL, 'ciJf');
INSERT INTO `sys_module` VALUES ('69', NULL, '集群配置项', 99, '/ciCluster', NULL, 'ciCluster');
INSERT INTO `sys_module` VALUES ('70', NULL, '逻辑服务器配置项', 99, '/ciLogicServer', NULL, 'ciLogicServer');
INSERT INTO `sys_module` VALUES ('71', NULL, '数据库配置项', 99, '/ciDataBase', NULL, 'ciDataBase');
INSERT INTO `sys_module` VALUES ('72', NULL, '网络设备配置项', 99, '/ciNetworkDevice', NULL, 'ciNetworkDevice');
INSERT INTO `sys_module` VALUES ('73', NULL, '物理服务器配置项', 99, '/ciPhysicalServer', NULL, 'ciPhysicalServer');
INSERT INTO `sys_module` VALUES ('74', NULL, '应用系统配置项', 99, '/ciApplication', NULL, 'ciApplication');
INSERT INTO `sys_module` VALUES ('75', NULL, '中间件配置置项', 99, '/ciMiddleware', NULL, 'ciMiddleware');
INSERT INTO `sys_module` VALUES ('76', NULL, '拓扑展示 ', 99, '/ciTopo', NULL, 'ciTopo');
INSERT INTO `sys_module` VALUES ('77', NULL, '机柜', 99, '/ciRack', NULL, 'ciRack');
INSERT INTO `sys_module` VALUES ('78', NULL, '变更记录', 99, '/ciChain', NULL, 'ciChain');
INSERT INTO `sys_module` VALUES ('79', NULL, '配置项关系', 99, '/ciRelation', NULL, 'ciRelation');
CREATE TABLE `ci_relation` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_a` varchar(14) NOT NULL COMMENT '配置项A',
  `ci_b` varchar(14) NOT NULL COMMENT '配置项B',
  `rl_desc` varchar(64) DEFAULT NULL COMMENT '配置项关系描述',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_jf` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL COMMENT '名称',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `location` varchar(80) NOT NULL COMMENT '地点',
  `floor` int(2) NOT NULL COMMENT '楼层',
  `area` double(11,2) DEFAULT NULL COMMENT '面积',
  `description` varchar(80) DEFAULT NULL COMMENT '主要用途',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ci_rack` (
  `uuid` varchar(128) NOT NULL,
  `ci_code` varchar(14) NOT NULL COMMENT '配置项编码',
  `ci_name` varchar(64) NOT NULL,
  `position` varchar(20) NOT NULL,
  `asset_no` varchar(16) DEFAULT NULL COMMENT '资产编号',
  `maintenance_unit` varchar(64) DEFAULT NULL,
  `startdate` varchar(16) DEFAULT NULL COMMENT '维保开始时间',
  `enddate` varchar(16) DEFAULT NULL COMMENT '维保结束时间',
  `mfrs` varchar(64) DEFAULT NULL COMMENT '制造商',
  `model` varchar(64) NOT NULL COMMENT '设备型号',
  `power1` varchar(64) DEFAULT NULL COMMENT '第一路电源',
  `power2` varchar(64) DEFAULT NULL COMMENT '第二路电源',
  `ci_desc` varchar(64) DEFAULT NULL COMMENT '配置项描述',
  `team` varchar(16) NOT NULL COMMENT '所属团队',
  `subclass` varchar(16) NOT NULL COMMENT '配置项子类',
  `status` varchar(8) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

