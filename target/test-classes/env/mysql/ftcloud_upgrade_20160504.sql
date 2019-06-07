CREATE TABLE `system_storage` (
  `uuid` varchar(128) NOT NULL COMMENT '主键',
  `name` varchar(64) DEFAULT '' COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `size` int(11) DEFAULT '0' COMMENT '数据盘大小（G）',
  `state` int(4) DEFAULT '1' COMMENT '状态：0 异常，2正常，3挂载中，4已经挂载，5删除中，6已删除，7解挂中，8修改中',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '所属购买用户',
  `instance_volume_uuid` varchar(128) DEFAULT NULL COMMENT '关联到实际的系统存储',
  `instance_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  KEY `instance_volume_uuid` (`instance_volume_uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `system_storage_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `system_storage_ibfk_2` FOREIGN KEY (`instance_volume_uuid`) REFERENCES `instance_volume_info` (`uuid`),
  CONSTRAINT `system_storage_ibfk_3` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统存储';
INSERT INTO `sys_module` (`uuid`, `name`, `priority`, `url`, `sn`) VALUES ('40', '系统存储', '99', '/dataDisk', 'dataDisk');
