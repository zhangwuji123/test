INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('220', NULL, '软件参数', '99', '/softwareParameter', NULL, 'softwareParameter');
CREATE TABLE `software_parameter` (
  `uuid` varchar(128) NOT NULL,
  `software_type` varchar(128) DEFAULT NULL,
  `label_name` varchar(128) DEFAULT NULL,
  `parameter_name` varchar(128) DEFAULT NULL,
  `parameter_type` int(10) DEFAULT NULL COMMENT '0：input 1:checkbox 2:select',
  `parameter_value` varchar(128) DEFAULT NULL,
  `parameter_value_range` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` int(2) DEFAULT NULL COMMENT '0：不可用 1：可用',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
