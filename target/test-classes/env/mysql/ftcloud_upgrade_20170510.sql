CREATE TABLE `software_install` (
  `uuid` varchar(128) NOT NULL,
  `instance_software_uuid` varchar(128) DEFAULT NULL COMMENT '云主机软件编号',
  `software_flow_uuid` varchar(128) DEFAULT NULL COMMENT '软件流编号',
  `software_flow_id` varchar(256) DEFAULT NULL COMMENT '流ID',
  `software_flow_name` varchar(128) DEFAULT NULL COMMENT '流名称',
  `flow_execute_id` varchar(128) DEFAULT NULL COMMENT '流执行ID',
  `state` int(2) DEFAULT NULL COMMENT '状态（0：异常；1：未安装；2：完成；3：安装中）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` VALUES ('92', NULL, '软件安装记录', 99, '/softwareInstall', NULL, 'softwareInstall');
