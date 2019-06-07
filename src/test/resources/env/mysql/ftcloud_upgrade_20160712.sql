CREATE TABLE `instance_software` (
  `uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) NOT NULL COMMENT '云主机编号',
  `state` int(2) NOT NULL COMMENT '软件的安装状态（0：异常；1：未创建未安装；2：完成；3：安装中）',
  `type` int(2) NOT NULL COMMENT '软件的类型（1：oracle；2：weblogic）',
  `message_detail` text COMMENT '消息明细',
  PRIMARY KEY (`uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `instance_software_ibfk_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='云主机软件';
INSERT INTO `sysconfig` VALUES ('20', '云主机默认登录名', 'vm_username', 'root');
