CREATE TABLE `physical_machine_software` (
  `uuid` varchar(128) NOT NULL,
  `software_uuid` varchar(128) DEFAULT NULL COMMENT '软件编号',
  `physical_machine_uuid` varchar(128) NOT NULL COMMENT '物理机编号',
  `state` int(2) NOT NULL COMMENT '软件的安装状态（0：异常；1：未安装；2：完成；3：安装中）',
  PRIMARY KEY (`uuid`),
  KEY `physical_machine_software_ibfk_1` (`physical_machine_uuid`),
  KEY `physical_machine_software_ibfk_2` (`software_uuid`),
  CONSTRAINT `physical_machine_software_ibfk_1` FOREIGN KEY (`physical_machine_uuid`) REFERENCES `physical_machine` (`uuid`),
  CONSTRAINT `physical_machine_software_ibfk_2` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物理机软件';
ALTER TABLE `software_install` ADD COLUMN `physical_machine_software_uuid` varchar(128) DEFAULT NULL COMMENT '物理机软件编号' AFTER `instance_software_uuid`;
ALTER TABLE `software_install` ADD CONSTRAINT `software_install_ibfk2` FOREIGN KEY (`physical_machine_software_uuid`) REFERENCES `physical_machine_software` (`uuid`);
