INSERT INTO `sys_module` VALUES ('89', NULL, '软件', 99, '/software', NULL, 'software');
INSERT INTO `sys_module` VALUES ('90', NULL, 'OO资源管理器', 99, '/ooResourceManager', NULL, 'ooResourceManager');
CREATE TABLE `software` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) NOT NULL COMMENT '软件名称',
  `image_file` varchar(256) DEFAULT NULL COMMENT '图片',
  `version` varchar(128) DEFAULT NULL COMMENT '版本',
  `description` text COMMENT '描述信息',
  `state` int(2) NOT NULL COMMENT '状态（0：删除；1：正常）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `software_flow` (
  `uuid` varchar(128) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '主键',
  `software_uuid` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '软件编号',
  `id` varchar(256) CHARACTER SET utf8 NOT NULL COMMENT '流编号',
  `name` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `sn` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`uuid`),
  KEY `software_uuid` (`software_uuid`),
  CONSTRAINT `software_flow_ibfk_1` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `instance_software` MODIFY COLUMN `type` int(2) DEFAULT NULL COMMENT '软件的类型（1：oracle；2：weblogic）';
ALTER TABLE `instance_software` ADD COLUMN `software_uuid` varchar(128) DEFAULT NULL COMMENT '软件编号' AFTER uuid;
ALTER TABLE `instance_software` ADD CONSTRAINT `instance_software_ibfk_2` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`);
