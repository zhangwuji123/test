CREATE TABLE `script` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) NOT NULL COMMENT '脚本名称',
  `script_file` varchar(256) NOT NULL COMMENT '脚本文件',
  `version` varchar(128) DEFAULT NULL COMMENT '版本',
  `description` text COMMENT '描述信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` VALUES ('97', NULL, '脚本', 99, '/script', NULL, 'script');
INSERT INTO `sysconfig` VALUES ('40', '脚本流ID', 'script_flow_id', 'be5786e7-412e-46e5-87ad-bfc66d4c8872');
INSERT INTO `sysconfig` VALUES ('41', '脚本流名称', 'script_flow_name', '执行脚本');
