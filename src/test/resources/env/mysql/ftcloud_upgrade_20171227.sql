CREATE TABLE `clear_strategy` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `os_type` int(4) DEFAULT NULL COMMENT '操作系统类型',
  `time_rule` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '时间规则',
  `operation_type` int(4) DEFAULT NULL COMMENT '操作类型：1清理，2压缩，3收集',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `state` int(4) DEFAULT NULL COMMENT '状态：1未启用，2已启用，3已删除',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `clear_rule` (
  `uuid` varchar(128) CHARACTER SET utf8 NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '规则名称',
  `clear_strategy_uuid` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '所属策略',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `clear_r_s_fk_133` (`clear_strategy_uuid`),
  CONSTRAINT `clear_r_s_fk_133` FOREIGN KEY (`clear_strategy_uuid`) REFERENCES `clear_strategy` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `clear_rule_param` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数值',
  `value` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数值',
  `clear_rule_uuid` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '关联规则',
  PRIMARY KEY (`uuid`),
  KEY `c_r_p_cr_fk_122` (`clear_rule_uuid`),
  CONSTRAINT `c_r_p_cr_fk_122` FOREIGN KEY (`clear_rule_uuid`) REFERENCES `clear_rule` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `clear_resource_relation` (
  `uuid` varchar(128) NOT NULL,
  `resource_uuid` varchar(128) DEFAULT NULL,
  `resource_type` varchar(64) DEFAULT NULL,
  `resource_name` varchar(64) DEFAULT NULL,
  `clear_strategy_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `job_param_info` (
  `uuid` varchar(128) NOT NULL,
  `job_type_info_uuid` varchar(128) DEFAULT NULL COMMENT '任务信息UUID',
  `param_cn_name` varchar(128) DEFAULT NULL COMMENT '参数的中文名字',
  `param_en_name` varchar(128) DEFAULT NULL COMMENT '参数的英文名字',
  `type` varchar(64) DEFAULT NULL COMMENT 'string,list,select',
  `values` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_job_type_info_uuid` (`job_type_info_uuid`),
  CONSTRAINT `fk_job_type_info_uuid` FOREIGN KEY (`job_type_info_uuid`) REFERENCES `job_type_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `job_type_info` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_module` (`uuid`, `visible`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('107', '1', NULL, '清理策略', '99', '/clearStrategy', NULL, 'clearStrategy');
INSERT INTO `job_type_info` (`uuid`, `name`) VALUES ('1', '清理');
INSERT INTO `job_type_info` (`uuid`, `name`) VALUES ('2', '压缩');
INSERT INTO `job_type_info` (`uuid`, `name`) VALUES ('3', '收集');

INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('1', '3', 'ftp地址', 'ftpurl', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('10', '2', '文件名列表', 'filenames', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('11', '2', '最后一次修改时间前', 'lastModifyDate', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('12', '2', '文件类型', 'fileType', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('13', '2', '文件所在目录', 'filepath', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('14', '1', '文件名列表', 'filenames', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('15', '1', '最后一次修改时间前', 'lastModifyDate', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('16', '1', '文件类型', 'fileType', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('17', '1', '文件所在目录', 'filepath', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('2', '3', 'ftp端口', 'port', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('3', '3', 'ftp用户名', 'username', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('4', '3', 'ftp用户密码', 'password', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('5', '3', 'ftp文件路径', 'ftpfiles', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('6', '3', '文件名列表', 'filenames', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('7', '3', '最后一次修改时间前', 'lastModifyDate', 'string', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('8', '3', '文件类型', 'fileType', 'list', NULL);
INSERT INTO `job_param_info` (`uuid`, `job_type_info_uuid`, `param_cn_name`, `param_en_name`, `type`, `values`) VALUES ('9', '3', '文件所在目录', 'filepath', 'list', NULL);
