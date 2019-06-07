ALTER TABLE `sys_user`
ADD COLUMN `balance`  double NULL COMMENT '账户余额' AFTER `create_time`;


CREATE TABLE `charge_detail` (
  `uuid` varchar(128)  NOT NULL,
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

CREATE TABLE `charge_detail_history` (
  `uuid` varchar(128) NOT NULL,
  `user_uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL,
  `instance_name` varchar(64) DEFAULT NULL,
  `instance_type` int(11) DEFAULT NULL COMMENT '1.VM 2.loadbalance 3.系统存储 4.云存储 5.物理机 6.弹性调度服务',
  `charge_mode` int(11) DEFAULT '0' COMMENT '1:按秒计费 2:按分计费 3:接时计费 4:按天计费 5:按次计费',
  `size` int(11) DEFAULT NULL COMMENT 'storage service G;IP num;Bandwidth M',
  `price` decimal(18,9) DEFAULT '0.000000000',
  `cost` decimal(18,9) DEFAULT '0.000000000',
  `cost_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `charge_detail_month` (
  `uuid` varchar(128) NOT NULL,
  `user_uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL,
  `instance_name` varchar(64) DEFAULT NULL,
  `instance_type` int(11) DEFAULT NULL COMMENT '1.VM 2.loadbalance 3.系统存储 4.云存储 5.物理机 6.弹性调度服务',
  `charge_mode` int(11) DEFAULT '0' COMMENT '1:按秒计费 2:按分计费 3:接时计费 4:按天计费 5:按次计费',
  `size` int(11) DEFAULT NULL COMMENT 'storage service G;IP num;Bandwidth M',
  `price` decimal(18,9) DEFAULT '0.000000000',
  `settlement_amount` decimal(18,9) DEFAULT '0.000000000',
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `cumulative_time` varchar(128) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `month` int(2) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
