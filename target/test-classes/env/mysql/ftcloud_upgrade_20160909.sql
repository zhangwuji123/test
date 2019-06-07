CREATE TABLE `mini_machine` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT 'ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `ip_address` varchar(128) DEFAULT NULL COMMENT 'IP地址',
  `model` varchar(128) DEFAULT NULL COMMENT '型号',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `check_time` varchar(128) DEFAULT '1' COMMENT '未检测 或最后一次检测时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `security_detection_result` (
  `uuid` varchar(128) NOT NULL,
  `state` int(1) DEFAULT NULL COMMENT '实例状态；0:异常；1：检测中；2：正常；',
  `detect_time` datetime DEFAULT NULL,
  `result` text COMMENT '检测结果',
  `instance_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_instance_uuid1` (`instance_uuid`),
  CONSTRAINT `fk_reference_instance_uuid2` FOREIGN KEY (`instance_uuid`) REFERENCES `mini_machine` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE single_instance_template ADD COLUMN flow_max_size int(11) DEFAULT NULL COMMENT '带宽最大值' AFTER quota;
ALTER TABLE single_instance_template ADD COLUMN flow_min_size int(11) DEFAULT NULL COMMENT '带宽最小值' AFTER quota;