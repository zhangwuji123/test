ALTER TABLE single_instance_template MODIFY COLUMN `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '集群的主键';
ALTER TABLE single_instance_template MODIFY COLUMN `template_uuid` varchar(128) DEFAULT NULL COMMENT '镜像模板的主键';
ALTER TABLE single_instance_template MODIFY COLUMN `ptype` int(11) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack';
ALTER TABLE single_instance_template MODIFY COLUMN `htype` int(11) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion';
ALTER TABLE single_instance_template MODIFY COLUMN `cpu_num` int(11) DEFAULT NULL COMMENT 'CPU个数';
ALTER TABLE single_instance_template MODIFY COLUMN `mem_size` int(11) DEFAULT NULL COMMENT '内存大小';
ALTER TABLE single_instance_template MODIFY COLUMN `disk_size` int(11) DEFAULT NULL COMMENT '云主机、负载均衡的硬盘大小';
ALTER TABLE single_instance_template ADD COLUMN storage_max_size int(11) DEFAULT NULL COMMENT '系统存储、云存储最大值' AFTER disk_size;
ALTER TABLE single_instance_template ADD COLUMN storage_min_size int(11) DEFAULT NULL COMMENT '系统存储、云存储最小值' AFTER disk_size;

CREATE TABLE `elastic_scaling` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `business_uuid` varchar(128) DEFAULT NULL COMMENT '业务',
  `strategy` int(1) DEFAULT NULL COMMENT '伸缩策略：1:固定时间，2:性能策略',
  `step` int(1) DEFAULT NULL COMMENT '步长',
  `cooldown` int(5) DEFAULT NULL COMMENT '冷却时间',
  `vm_min` int(5) DEFAULT NULL COMMENT '最少实例数',
  `vm_max` int(5) DEFAULT NULL COMMENT '最多实例数',
  `elb_uuid` varchar(128) DEFAULT NULL COMMENT '负载均衡uuid',
  `user_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `elastic_gen_config` (
  `uuid` varchar(128) NOT NULL,
  `cpu_num` int(11) unsigned DEFAULT NULL COMMENT 'cpu 数量',
  `mem_size` double(11,2) unsigned DEFAULT NULL COMMENT '内存容量',
  `storage_uuid` varchar(128) NOT NULL COMMENT '存储uuid',
  `template_uuid` varchar(128) DEFAULT NULL COMMENT '资源镜像id',
  `host_uuid` varchar(128) DEFAULT NULL COMMENT '所属主机uuid',
  `cluster_uuid` varchar(128) DEFAULT NULL,
  `network_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  CONSTRAINT `elastic_gen_config_ibfk_1` FOREIGN KEY (`uuid`) REFERENCES `elastic_scaling` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `elastic_scaling_vms` (
  `uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0:现有虚机，1:伸缩创建虚机',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `scaling_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `scaling_uuid` (`scaling_uuid`),
  CONSTRAINT `elastic_scaling_vms_ibfk_1` FOREIGN KEY (`scaling_uuid`) REFERENCES `elastic_scaling` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `elastic_strategy_data` (
  `uuid` varchar(128) NOT NULL COMMENT '值与elastic_scaling表的uuid一致',
  `data` text COMMENT 'json数据',
  PRIMARY KEY (`uuid`),
  CONSTRAINT `elastic_strategy_data_ibfk_1` FOREIGN KEY (`uuid`) REFERENCES `elastic_scaling` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
