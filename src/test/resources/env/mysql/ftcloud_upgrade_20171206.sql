CREATE TABLE `reboot_strategy` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `execute_type` int(2) DEFAULT NULL COMMENT '执行类型（1：每月；2：每周；3：每天；4：一次）',
  `execute_date` varchar(128) DEFAULT NULL COMMENT '执行日期',
  `execute_time` varchar(128) DEFAULT NULL COMMENT '执行时间',
  `state` int(2) DEFAULT NULL COMMENT '状态(0：删除；1：正常；2：启用)',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
