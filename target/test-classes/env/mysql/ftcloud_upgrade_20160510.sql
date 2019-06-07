ALTER TABLE `elastic_scaling`
ADD COLUMN `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP;

INSERT INTO `sys_module` VALUES ('38', NULL, '自动伸缩组管理', 99, '/elasticScaling', NULL, 'elasticScaling');

CREATE TABLE `rack` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `datacenter_uuid` varchar(128) DEFAULT NULL COMMENT '数据中心',
  `zone_uuid` varchar(128) DEFAULT NULL COMMENT '区域',
  `location` varchar(128) DEFAULT NULL COMMENT '位置',
  `height` int(2) DEFAULT NULL COMMENT '高度',
  `number` varchar(128) DEFAULT NULL COMMENT '编号',
  `manufacturer` varchar(128) DEFAULT NULL COMMENT '厂商',
  `price` double(11,2) DEFAULT NULL COMMENT '价格',
  `model` varchar(128) DEFAULT NULL COMMENT '型号',
  `principal` varchar(128) DEFAULT NULL COMMENT '负责人',
  `image` varchar(256) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `bladecenter` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `number` varchar(128) DEFAULT NULL COMMENT '编号',
  `rows` int(2) DEFAULT NULL COMMENT '行数',
  `cols` int(2) DEFAULT NULL COMMENT '列数',
  `datacenter_uuid` varchar(128) DEFAULT NULL COMMENT '数据中心',
  `zone_uuid` varchar(128) DEFAULT NULL COMMENT '区域',
  `rack_uuid` varchar(128) DEFAULT NULL COMMENT '所属机架',
  `slot` varchar(128) DEFAULT NULL COMMENT '槽位号',
  `manufacturer` varchar(128) DEFAULT NULL COMMENT '厂商',
  `model` varchar(128) DEFAULT NULL COMMENT '型号',
  `price` double(11,2) DEFAULT NULL COMMENT '价格',
  `principal` varchar(128) DEFAULT NULL COMMENT '负责人',
  `image` varchar(256) DEFAULT '' COMMENT '图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `PK_Rack` (`rack_uuid`),
  CONSTRAINT `PK_Rack` FOREIGN KEY (`rack_uuid`) REFERENCES `rack` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `assets` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `type` varchar(128) DEFAULT NULL COMMENT '1:刀片服务器;2:交换机;3:防火墙;4:路由器;5:物理服务器;6:存储服务器',
  `number` varchar(128) DEFAULT NULL COMMENT '编号',
  `datacenter_uuid` varchar(128) DEFAULT NULL COMMENT '数据中心',
  `zone_uuid` varchar(128) DEFAULT NULL COMMENT '区域',
  `host_uuid` varchar(128) DEFAULT NULL COMMENT '对应主机',
  `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机架或刀箱',
  `bladecenter_uuid` varchar(128) DEFAULT NULL COMMENT '刀箱',
  `manufacturer` varchar(128) DEFAULT NULL COMMENT '厂商',
  `model` varchar(128) DEFAULT NULL COMMENT '型号',
  `slot` varchar(256) DEFAULT NULL COMMENT '槽位号',
  `price` double(11,2) DEFAULT NULL COMMENT '价格',
  `purpose` varchar(128) DEFAULT NULL COMMENT '用途',
  `cpu` varchar(128) DEFAULT NULL COMMENT '处理器',
  `memory` varchar(128) DEFAULT NULL COMMENT '内存',
  `disk` varchar(128) DEFAULT NULL COMMENT '硬盘',
  `network` varchar(128) DEFAULT NULL COMMENT '网络',
  `power` varchar(128) DEFAULT NULL COMMENT '电源',
  `image` varchar(256) DEFAULT NULL COMMENT '图片',
  `principal` varchar(128) DEFAULT NULL COMMENT '负责人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `pk_rack_1` (`rack_uuid`),
  KEY `pk_bc_1` (`bladecenter_uuid`),
  CONSTRAINT `pk_bc_1` FOREIGN KEY (`bladecenter_uuid`) REFERENCES `bladecenter` (`uuid`),
  CONSTRAINT `pk_rack_1` FOREIGN KEY (`rack_uuid`) REFERENCES `rack` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_module` VALUES ('41', NULL, '概览', 99, '/assetsOverview', NULL, 'assetsOverview');
INSERT INTO `sys_module` VALUES ('42', NULL, '数字机架', 99, '/assetsCharts', NULL, 'assetsCharts');
INSERT INTO `sys_module` VALUES ('43', NULL, '机架管理', 99, '/rack', NULL, 'rack');
INSERT INTO `sys_module` VALUES ('44', NULL, '刀箱管理', 99, '/bladeCenter', NULL, 'bladeCenter');
INSERT INTO `sys_module` VALUES ('45', NULL, '设备管理', 99, '/assets', NULL, 'assets');

INSERT INTO `sys_user` (`uuid`, `username`, `password`, `type`, `approve`, `salt`, `status`, `realname`, `email`, `mobile`, `comment`, `create_time`) VALUES ('2', 'backgroud', NULL, '1', '0', NULL, 'enabled', 'backgroud', NULL, NULL, NULL, NULL);