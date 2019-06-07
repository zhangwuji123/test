CREATE TABLE `cm_type` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(32) NOT NULL,
  `code` varchar(32) DEFAULT NULL COMMENT '配置项编码前缀',
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cm_type_attr` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(32) NOT NULL,
  `type` int(11) NOT NULL COMMENT '类型：1：文本；2：日期；3：关联',
  `relation_type_uuid` varchar(128) DEFAULT NULL COMMENT '关联编号',
  `type_uuid` varchar(128) NOT NULL COMMENT '配置项编号',
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`uuid`),
  KEY `type_uuid` (`type_uuid`),
  KEY `relation_type_uuid` (`relation_type_uuid`),
  CONSTRAINT `cm_type_attr_ibfk_1` FOREIGN KEY (`type_uuid`) REFERENCES `cm_type` (`uuid`),
  CONSTRAINT `cm_type_attr_ibfk_2` FOREIGN KEY (`relation_type_uuid`) REFERENCES `cm_type` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cm_data` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `cm_code` varchar(32) NOT NULL COMMENT '配置项编码',
  `cm_name` varchar(64) NOT NULL COMMENT '名称',
  `cm_desc` text COMMENT '配置项描述',
  `team` varchar(16) DEFAULT NULL COMMENT '所属团队',
  `subclass` varchar(16) DEFAULT NULL COMMENT '配置项子类',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  `create_user` varchar(128) DEFAULT NULL COMMENT '配置项创建用户编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(128) DEFAULT NULL COMMENT '配置项修改用户编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `type_uuid` varchar(128) NOT NULL COMMENT '配置项编号',
  PRIMARY KEY (`uuid`),
  KEY `type_uuid` (`type_uuid`),
  CONSTRAINT `cm_data_ibfk_1` FOREIGN KEY (`type_uuid`) REFERENCES `cm_type` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cm_data_attr` (
  `uuid` varchar(128) NOT NULL,
  `data_uuid` varchar(128) NOT NULL COMMENT '主数据编号',
  `type_attr_uuid` varchar(128) NOT NULL COMMENT '配置项属性编号',
  `value` varchar(128) DEFAULT NULL COMMENT '属性值',
  `relation_uuid` varchar(128) DEFAULT NULL COMMENT '关联编号',
  PRIMARY KEY (`uuid`),
  KEY `data_uuid` (`data_uuid`),
  KEY `type_attr_uuid` (`type_attr_uuid`),
  KEY `relation_uuid` (`relation_uuid`),
  CONSTRAINT `cm_data_attr_ibfk_1` FOREIGN KEY (`data_uuid`) REFERENCES `cm_data` (`uuid`),
  CONSTRAINT `cm_data_attr_ibfk_2` FOREIGN KEY (`type_attr_uuid`) REFERENCES `cm_type_attr` (`uuid`),
  CONSTRAINT `cm_data_attr_ibfk_3` FOREIGN KEY (`relation_uuid`) REFERENCES `cm_data` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` VALUES ('231', 0, NULL, '配置项维护', 99, '/cmType', NULL, 'cmType');
INSERT INTO `sys_module` VALUES ('232', 0, NULL, '配置项属性维护', 99, '/cmTypeAttr', NULL, 'cmTypeAttr');
INSERT INTO `sys_module` VALUES ('233', 1, NULL, '机房配置项', 99, '/cmJf', NULL, 'cmJf');
INSERT INTO `sys_module` VALUES ('234', 1, NULL, '供电设备', 99, '/cmGd', NULL, 'cmGd');
INSERT INTO `sys_module` VALUES ('235', 1, NULL, '环控设备', 99, '/cmHk', NULL, 'cmHk');
INSERT INTO `sys_module` VALUES ('236', 1, NULL, '物理网络设备', 99, '/cmPhysicalNetwork', NULL, 'cmPhysicalNetwork');
INSERT INTO `sys_module` VALUES ('237', 1, NULL, '前端设备', 99, '/cmFront', NULL, 'cmFront');
INSERT INTO `sys_module` VALUES ('238', 1, NULL, '存储类设备', 99, '/cmStorage', NULL, 'cmStorage');
INSERT INTO `sys_module` VALUES ('239', 1, NULL, '物理服务器', 99, '/cmPhysicalServer', NULL, 'cmPhysicalServer');
INSERT INTO `sys_module` VALUES ('240', 1, NULL, '宿主机集群', 99, '/cmHostCluster', NULL, 'cmHostCluster');
INSERT INTO `sys_module` VALUES ('241', 1, NULL, '逻辑服务器', 99, '/cmLogicServer', NULL, 'cmLogicServer');
INSERT INTO `sys_module` VALUES ('242', 1, NULL, '应用系统', 99, '/cmApplication', NULL, 'cmApplication');
INSERT INTO `cm_type` VALUES ('APPLICATION', '应用系统', 'ap-sys-', 9);
INSERT INTO `cm_type` VALUES ('FRONT', '前端设备', 'sy-qdd-', 4);
INSERT INTO `cm_type` VALUES ('GD', '供电设备', 'fa-esd-', 1);
INSERT INTO `cm_type` VALUES ('HK', '环控设备', 'fa-emd-', 2);
INSERT INTO `cm_type` VALUES ('HOST_CLUSTER', '宿主机集群', 'sy-cls-', 7);
INSERT INTO `cm_type` VALUES ('JF', '机房', 'fa-srm-', 0);
INSERT INTO `cm_type` VALUES ('LOGIC_SERVER', '逻辑服务器', 'sy-lsr-', 8);
INSERT INTO `cm_type` VALUES ('PHYSICAL_NETWORK', '物理网络设备', 'ne-pnd-', 3);
INSERT INTO `cm_type` VALUES ('PHYSICAL_SERVER', '物理服务器', 'sy-psr-', 6);
INSERT INTO `cm_type` VALUES ('STORAGE', '存储类设备', 'sy-str-', 5);
