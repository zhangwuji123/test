CREATE TABLE `task_msg_info` (
  `uuid` varchar(128) NOT NULL,
  `task_info_uuid` varchar(128) DEFAULT NULL COMMENT 'task_info表中的主键',
  `operator` varchar(255) DEFAULT NULL COMMENT '消息发送者：1代表web端，2代表adapter',
  `operate_time` datetime DEFAULT NULL COMMENT '消息发送时间',
  `message` blob COMMENT '二进制的消息内容',
  `message_str` text DEFAULT NULL COMMENT '字符串格式的消息内容',
  PRIMARY KEY (`uuid`),
  KEY `FK_TASK_INFO` (`task_info_uuid`),
  CONSTRAINT `FK_TASK_INFO` FOREIGN KEY (`task_info_uuid`) REFERENCES `task_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;