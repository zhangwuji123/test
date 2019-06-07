ALTER TABLE `vdc` ADD COLUMN `state` int(2) NULL COMMENT '状态（0：删除；1：正常）' AFTER `isolation_mode`;
CREATE TABLE `organization` (
  `uuid` varchar(128) NOT NULL,
  `parent_uuid` varchar(128) DEFAULT NULL COMMENT '父节点',
  `name` varchar(64) DEFAULT NULL COMMENT '组织机构名称',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `organization_user_relation` (
  `uuid` varchar(128) NOT NULL,
  `organization_uuid` varchar(128) DEFAULT NULL,
  `user_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_user_uuid_112` (`user_uuid`),
  KEY `fk_org_uuid_133` (`organization_uuid`),
  CONSTRAINT `fk_org_uuid_133` FOREIGN KEY (`organization_uuid`) REFERENCES `organization` (`uuid`),
  CONSTRAINT `fk_user_uuid_112` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `vdc`
ADD COLUMN `order_info_uuid`  varchar(128) NULL COMMENT '关联订单表' AFTER `user_uuid`;
ALTER TABLE `vdc`
ADD COLUMN `due_time`  datetime NULL COMMENT '资源到期时间' AFTER `order_info_uuid`;
ALTER TABLE `vdc`
ADD COLUMN `charging_rules_uuid`  varchar(128) NULL COMMENT '关联计费' AFTER `due_time`;
INSERT INTO `charging_rules` (`uuid`, `name`, `state`, `product_type`, `charge_mode`, `price`, `create_time`, `comment`) VALUES ('ff8080815d404a60015d4e3afc330048', 'VDC计费规则关闭', '1', '10', '4', '0.000', '2017-08-10 19:26:08', NULL);
ALTER TABLE `vdc` ADD CONSTRAINT `vdc_ibfk_order_1` FOREIGN KEY (`order_info_uuid`) REFERENCES `order_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `vdc` ADD CONSTRAINT `vdc_ibfk_charging` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;


