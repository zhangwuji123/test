CREATE TABLE `software_relation` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `software_uuid` varchar(128) DEFAULT NULL COMMENT '软件编号',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '资源编号',
  `resource_name` varchar(128) DEFAULT NULL,
  `type` int(2) NOT NULL COMMENT '类型（1：云主机，2：物理机）',
  `state` int(2) NOT NULL COMMENT '软件的安装状态（0：异常；1：未创建未安装；2：完成；3：安装中）',
  PRIMARY KEY (`uuid`),
  KEY `software_uuid` (`software_uuid`),
  CONSTRAINT `software_relation_ibfk_1` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `software_install` DROP FOREIGN KEY `software_install_ibfk`;
ALTER TABLE `software_install` DROP FOREIGN KEY `software_install_ibfk2`;
ALTER TABLE `software_install` DROP COLUMN `instance_software_uuid`;
ALTER TABLE `software_install` DROP COLUMN `physical_machine_software_uuid`;
ALTER TABLE `software_install` ADD COLUMN `software_relation_uuid` varchar(128) DEFAULT NULL COMMENT '软件关系编号' AFTER `uuid`;
ALTER TABLE `software_install` ADD CONSTRAINT `software_install_ibfk` FOREIGN KEY (`software_relation_uuid`) REFERENCES `software_relation` (`uuid`);
DROP TABLE instance_software;
DROP TABLE physical_machine_software;
