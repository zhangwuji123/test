INSERT INTO `sys_module` VALUES ('56', NULL, '历史账单', 99, '/chargeDetailDay', NULL, 'chargeDetailDay');



CREATE TABLE `mail_information` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `host` varchar(128) DEFAULT NULL COMMENT '服务器地址',
  `sender` varchar(128) DEFAULT NULL COMMENT '发件人的邮箱',
  `receiver` varchar(128) DEFAULT NULL COMMENT '收件人的邮箱',
  `name` varchar(128) DEFAULT NULL COMMENT '发件人昵称',
  `user_name` varchar(128) DEFAULT NULL COMMENT '账号',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `subject` varchar(128) DEFAULT NULL COMMENT '邮件主题',
  `message` varchar(256) DEFAULT NULL COMMENT '邮件信息(支持HTML)',
  `send_state` int(2) DEFAULT NULL COMMENT '邮件发送状态 0未发送 1为成功 2为失败',
  `send_time` datetime DEFAULT NULL COMMENT '邮件发送时间',
  `send_num` int(2) DEFAULT NULL COMMENT '邮件次数',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '用户ID',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


