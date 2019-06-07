CREATE TABLE `recharge_record` (
  `uuid` varchar(128) NOT NULL COMMENT '主键',
  `user_uuid` varchar(128) NOT NULL COMMENT '关联用户',
  `money` double(8,2) NOT NULL COMMENT '充值金额',
  `create_time` datetime NOT NULL COMMENT '充值时间',
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  CONSTRAINT `recharge_record_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
