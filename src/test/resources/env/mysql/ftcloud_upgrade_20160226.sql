ALTER TABLE `instance_info`	ADD COLUMN `order_uuid`  varchar(128) NULL AFTER `vnc_port`;
ALTER TABLE `instance_info` ADD CONSTRAINT `instance_info_iofk_1` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
