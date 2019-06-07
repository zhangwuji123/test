CREATE TABLE `vdc_cluster_relation` (
  `uuid` varchar(128) NOT NULL,
  `vdc_uuid` varchar(128) NOT NULL COMMENT 'VDC编号',
  `cluster_uuid` varchar(128) NOT NULL COMMENT '集群编号',
  PRIMARY KEY (`uuid`),
  KEY `vdc_uuid` (`vdc_uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `vdc_cluster_relation_ibfk_1` FOREIGN KEY (`vdc_uuid`) REFERENCES `vdc` (`uuid`),
  CONSTRAINT `vdc_cluster_relation_ibfk_2` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `vpc_cluster_relation` (
  `uuid` varchar(128) NOT NULL,
  `vpc_uuid` varchar(128) NOT NULL COMMENT 'VPC编号',
  `cluster_uuid` varchar(128) NOT NULL COMMENT '集群编号',
  PRIMARY KEY (`uuid`),
  KEY `vpc_uuid` (`vpc_uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `vpc_cluster_relation_ibfk_1` FOREIGN KEY (`vpc_uuid`) REFERENCES `vpc` (`uuid`),
  CONSTRAINT `vpc_cluster_relation_ibfk_2` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user_cluster_relation` (
  `uuid` varchar(128) NOT NULL,
  `user_uuid` varchar(128) DEFAULT NULL,
  `cluster_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_ucr_user_uuid` (`user_uuid`),
  KEY `fk_ucr_cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `fk_ucr_cluster_uuid` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `fk_ucr_user_uuid` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `sys_user`
ADD COLUMN `foreign_ref`  varchar(128) NULL AFTER `department_uuid`;
