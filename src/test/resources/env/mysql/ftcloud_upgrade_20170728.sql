CREATE TABLE `message` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '用户编号',
  `name` varchar(128) DEFAULT NULL COMMENT '消息名称',
  `resource_name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `error_message` varchar(128) DEFAULT NULL COMMENT '错误消息',
  `resource_type` varchar(128) DEFAULT NULL COMMENT '资源类型',
  `state` int(2) DEFAULT NULL COMMENT '状态 0：异常, 1:处理中,2:完成;',
  `progress` int(4) DEFAULT NULL COMMENT '进程的比例',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` VALUES ('98', NULL, '消息管理', 99, '/message', NULL, 'message');
CREATE TABLE `script_execute` (
  `uuid` varchar(128) NOT NULL,
  `script_uuid` varchar(128) DEFAULT NULL COMMENT '脚本编号',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '资源编号',
  `resource_name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `type` int(2) DEFAULT NULL COMMENT '类型（1：云主机，2：物理机）',
  `execute_id` varchar(128) DEFAULT NULL COMMENT '流执行ID',
  `state` int(2) DEFAULT NULL COMMENT '状态（0：异常；1：未执行；2：完成；3：执行中）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`uuid`),
  KEY `script_uuid` (`script_uuid`),
  CONSTRAINT `script_execute_ibfk_1` FOREIGN KEY (`script_uuid`) REFERENCES `script` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` VALUES ('99', NULL, '脚本执行记录', 99, '/scriptExecute', NULL, 'scriptExecute');

