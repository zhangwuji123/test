INSERT INTO `sys_module` VALUES ('96', NULL, '软件资源', 99, '/softwareResource', NULL, 'softwareResource');
CREATE TABLE `software_resource` (
  `uuid` varchar(128) NOT NULL,
  `state` int(2) NOT NULL COMMENT '状态：（0异常；1未安装；2已安装；3安装中）',
  `create_time` datetime NOT NULL COMMENT '创建时间，运营审核通过交付时间',
  `software_uuid` varchar(128) DEFAULT NULL COMMENT '软件编号',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '用户编号',
  `order_uuid` varchar(128) DEFAULT NULL COMMENT '订单编号',
  `charging_rules_uuid` varchar(128) DEFAULT NULL COMMENT '计费规则编号',
  PRIMARY KEY (`uuid`),
  KEY `software_uuid` (`software_uuid`),
  KEY `user_uuid` (`user_uuid`),
  KEY `order_uuid` (`order_uuid`),
  KEY `charging_rules_uuid` (`charging_rules_uuid`),
  CONSTRAINT `software_resource_ibfk_1` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`),
  CONSTRAINT `software_resource_ibfk_2` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `software_resource_ibfk_3` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`),
  CONSTRAINT `software_resource_ibfk_4` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



