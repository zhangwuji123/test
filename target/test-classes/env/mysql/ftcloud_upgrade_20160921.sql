INSERT INTO `sysconfig` VALUES ('24', '内存比例', 'mem_proportion', '1.5');
ALTER TABLE `charging_rules` MODIFY COLUMN `price` double(11,3) NOT NULL COMMENT '单价';
ALTER TABLE `unit_price` MODIFY COLUMN `price` double(11,3) NOT NULL COMMENT '单价';

DROP TABLE  `bandwidth`;
CREATE TABLE `bandwidth` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（1、流量，2带宽）',
  `size` double(11,0) DEFAULT NULL COMMENT '流量/带宽大小',
  `time_long` int(11) DEFAULT NULL COMMENT '时长',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `due_time` datetime DEFAULT NULL COMMENT '到期时间',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '所属用户',
  `order_uuid` varchar(128) DEFAULT NULL COMMENT '所属订单',
  `charging_rules_uuid` varchar(128) DEFAULT NULL COMMENT '计费规则',
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  KEY `order_uuid` (`order_uuid`),
  KEY `charging_rules_uuid` (`charging_rules_uuid`),
  CONSTRAINT `bandwidth_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `bandwidth_ibfk_2` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`),
  CONSTRAINT `bandwidth_ibfk_3` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='带宽表';


