ALTER TABLE `vnet`
ADD COLUMN `foreign_ref`  varchar(128) NULL AFTER `instance_uuid`;

CREATE TABLE `instance_volume_info` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '卷名称',
  `type` varchar(10) DEFAULT NULL COMMENT '卷类型',
  `size` double(11,2) DEFAULT NULL COMMENT '卷的大小单位GB',
  `used_size` double(11,2) DEFAULT NULL COMMENT '已用卷的大小单位GB',
  `provision` varchar(255) DEFAULT NULL COMMENT '磁盘置备类型:thin,thick,eagerzeroedthick',
  `state` varchar(20) DEFAULT NULL COMMENT '状态',
  `instance_uuid` varchar(128) DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_21` (`instance_uuid`),
  CONSTRAINT `fk_reference_21_volume_key` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

