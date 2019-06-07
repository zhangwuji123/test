CREATE TABLE `charge_detail_day` (
  `uuid` varchar(128) NOT NULL,
  `charge_detail_id` varchar(128) DEFAULT NULL COMMENT '计费详情uuid',
  `statistic_date` varchar(10) DEFAULT NULL COMMENT '统计日期，格式为yyyy-dd-mm',
  `cost` decimal(18,9) DEFAULT '0.000000000' COMMENT '当天消费金额',
  PRIMARY KEY (`uuid`),
  KEY `fk_charge_detail_uuid` (`charge_detail_id`),
  CONSTRAINT `fk_charge_detail_uuid` FOREIGN KEY (`charge_detail_id`) REFERENCES `charge_detail` (`uuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;