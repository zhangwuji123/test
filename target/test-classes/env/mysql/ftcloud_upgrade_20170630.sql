INSERT INTO `sys_module` VALUES ('93', NULL, '工单管理', 99, '/workOrder', NULL, 'workOrder');
CREATE TABLE `work_order` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `description` varchar(1000) NOT NULL COMMENT '描述信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `resolve_time` datetime DEFAULT NULL COMMENT '解决时间',
  `state` int(4) DEFAULT NULL COMMENT '状态，0 未处理， 1处理中，2已处理',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '所属用户UUID',
  `resolve_user_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_user_uuid_ref` (`user_uuid`),
  KEY `fk_reslove_user_uuid_ref` (`resolve_user_uuid`),
  CONSTRAINT `fk_reslove_user_uuid_ref` FOREIGN KEY (`resolve_user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `fk_user_uuid_ref` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `work_order_answer` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '所属用户',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述信息',
  `answer_time` datetime DEFAULT NULL COMMENT '回复时间',
  `work_order_uuid` varchar(128) DEFAULT NULL COMMENT '关联到工单',
  PRIMARY KEY (`uuid`),
  KEY `fk_user_uuid_ref001` (`user_uuid`),
  KEY `fk_work_order_uuid_ref` (`work_order_uuid`),
  CONSTRAINT `fk_user_uuid_ref001` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `fk_work_order_uuid_ref` FOREIGN KEY (`work_order_uuid`) REFERENCES `work_order` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;