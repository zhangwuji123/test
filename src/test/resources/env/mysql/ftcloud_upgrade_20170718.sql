ALTER TABLE `physical_machine` ADD COLUMN `business_uuid` varchar(128) DEFAULT NULL COMMENT '业务' AFTER `delete_time`;
ALTER TABLE `physical_machine` ADD CONSTRAINT `physical_machine_ibfk_9` FOREIGN KEY (`business_uuid`) REFERENCES `business` (`uuid`);

CREATE TABLE `task_info_order_relation` (
  `uuid` varchar(128) NOT NULL,
  `order_info_uuid` varchar(128) DEFAULT NULL,
  `task_info_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

