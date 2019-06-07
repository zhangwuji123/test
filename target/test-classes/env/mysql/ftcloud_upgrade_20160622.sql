DROP TABLE IF EXISTS `charge_detail`;
CREATE TABLE `charge_detail` (
  `uuid` varchar(128) NOT NULL,
  `user_uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) DEFAULT '',
  `instance_name` varchar(64) DEFAULT '',
  `instance_type` int(11) DEFAULT NULL COMMENT '1.VM 2.loadbalance 3.系统存储 4.云存储 5.物理机 6.弹性调度服务',
  `charge_mode` int(11) DEFAULT '0' COMMENT '1:按秒计费 2:按分计费 3:接时计费 4:按天计费 5:按次计费',
  `size` int(11) DEFAULT NULL COMMENT 'storage service G;IP num;Bandwidth M',
  `price` decimal(18,9) DEFAULT '0.000000000',
  `cost` decimal(18,9) DEFAULT '0.000000000',
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `last_check_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `cloudstore_user_store`
ADD COLUMN `order_uuid`  varchar(128) NULL AFTER `quota_ref_id`,
ADD COLUMN `charging_rules_uuid`  varchar(128) NULL AFTER `order_uuid`;
ALTER TABLE `cloudstore_user_store` ADD CONSTRAINT `cloudstore_user_store_ibfk_4` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `cloudstore_user_store` ADD CONSTRAINT `cloudstore_user_store_ibfk_5` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `data_disk`
ADD COLUMN `order_uuid`  varchar(128) NULL AFTER `instance_uuid`,
ADD COLUMN `charging_rules_uuid`  varchar(128) NULL AFTER `order_uuid`;
ALTER TABLE `data_disk` ADD CONSTRAINT `data_disk_ibfk_4` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `data_disk` ADD CONSTRAINT `data_disk_ibfk_5` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `physical_machine`
ADD COLUMN `charging_rules_uuid`  varchar(128) NULL AFTER `delete_time`;
ALTER TABLE `physical_machine` ADD CONSTRAINT `physical_machine_ibfk_7` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `instance_info`
ADD COLUMN `charging_rules_uuid`  varchar(128) NULL AFTER `dyna_mem_max`;
ALTER TABLE `instance_info` ADD CONSTRAINT `instance_info_crfk_1` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `order_info`
MODIFY COLUMN `state`  int(2) NULL DEFAULT NULL COMMENT '订单状态：0取消 1未处理 2处理中 3 审核通过 4审核不通过 5已交付 ' AFTER `shopping_detail`;

ALTER TABLE `charge_detail` ADD CONSTRAINT `cd_su_pk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;







