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
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_module` (`uuid`, `visible`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('107', '1', NULL, '清理策略', '99', '/clearStrategy', NULL, 'clearStrategy');






