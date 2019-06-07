CREATE TABLE `instance_life_cycle` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `instance_uuid` varchar(128) NOT NULL COMMENT '云主机编号',
  `action` int(11) NOT NULL COMMENT '动作(1:创建云主机，2：删除云主机，3：修改云主机)',
  `cpu` int(11) NOT NULL DEFAULT '0' COMMENT 'CPU变化量',
  `mem` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '内存变化量',
  `disk` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '硬盘变化量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `instance_life_cycle_ibfk_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
