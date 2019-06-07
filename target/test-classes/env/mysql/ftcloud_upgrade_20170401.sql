INSERT INTO `ftcloud`.`sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('87', NULL, '小型机', '99', '/miniMachineInfo', NULL, 'miniMachineInfo');
INSERT INTO `ftcloud`.`sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('88', NULL, 'LPAR', '99', '/lparInfo', NULL, 'lparInfo');
CREATE TABLE `mini_machine_info` (
  `uuid` varchar(128) NOT NULL COMMENT '小机唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '小机名称',
  `ip` varchar(128) DEFAULT NULL COMMENT '小机IP地址',
  `cpu_total` int(11) DEFAULT NULL COMMENT 'cpu总核数（核）',
  `mem_total` int(11) DEFAULT NULL COMMENT '内存总大小（MB）',
  `cpu_avail` int(11) DEFAULT NULL COMMENT '可获得CPU总核数（核）',
  `mem_avail` int(11) DEFAULT NULL COMMENT '可获得内存总大小（MB）',
  `state` int(11) DEFAULT NULL COMMENT '0:异常；1：正在初始化；2：运行；3：正在进行断电；4：断电；6：已删除；',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外键关联到后端系统',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '所属集群',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小机信息表';
CREATE TABLE `mm_io_resource` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT 'IO资源名称',
  `unit_phys_loc` varchar(128) DEFAULT NULL COMMENT '位置码',
  `bus_id` varchar(128) DEFAULT NULL COMMENT '总线',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `is_required` int(4) DEFAULT NULL COMMENT '是否为必须(0:不必须，1:必须)',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外键关联到后端系统',
  `lpar_uuid` varchar(128) DEFAULT NULL COMMENT '关联到lpar',
  `mm_uuid` varchar(128) DEFAULT NULL COMMENT '关联到小机',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '关联到集群',
  PRIMARY KEY (`uuid`),
  KEY `FK_Reference_3` (`mm_uuid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`mm_uuid`) REFERENCES `mini_machine_info` (`uuid`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `FK_io_lpar_6` FOREIGN KEY (`lpar_uuid`) REFERENCES `lpar_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小机IO资源';
CREATE TABLE `lpar_info` (
  `uuid` varchar(128) NOT NULL,
  `display_name` varchar(128) DEFAULT NULL COMMENT '显示名称',
  `name` varchar(128) DEFAULT NULL COMMENT '后端名称',
  `min_mem` int(11) DEFAULT NULL COMMENT '最小内存（MB）',
  `max_mem` int(11) DEFAULT NULL COMMENT '最大内存（MB）',
  `desired_mem` int(11) DEFAULT NULL COMMENT '期望内存（MB）',
  `min_cpu` int(11) DEFAULT NULL COMMENT '最小cpu（核）',
  `max_cpu` int(11) DEFAULT NULL COMMENT '最大cpu（核）',
  `desired_cpu` int(11) DEFAULT NULL COMMENT '期望cpu（核）',
  `state` int(4) DEFAULT NULL COMMENT '0:异常；1：正在创建中；2：运行；3：关机中；4：已关机；5：删除中；6：已删除；7:开机中；9：重启中；11：暂停中；12：已暂停；13：恢复中；14：已恢复；15：接管中;16:迁移中;17:变更中;',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外键关联到后台系统',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '关联到集群',
  `mm_uuid` varchar(128) DEFAULT NULL COMMENT '关联到小机',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `os_name` varchar(128) DEFAULT NULL COMMENT '操作系统名称',
  `ip` varchar(128) DEFAULT NULL COMMENT 'IP地址',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`uuid`),
  KEY `FK_Reference_4` (`mm_uuid`),
  CONSTRAINT `FK_Refer_mm_8` FOREIGN KEY (`mm_uuid`) REFERENCES `mini_machine_info` (`uuid`),
  CONSTRAINT `FK_Refer_cl_7` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `FK_Refer_user_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='lpar';