CREATE TABLE `instance_num` (
  `uuid` varchar(128) NOT NULL,
  `statistic_date` varchar(10) NOT NULL COMMENT '统计日期yyyy-mm-dd',
  `business_uuid` varchar(128) DEFAULT NULL,
  `instance_num` int(11) NOT NULL COMMENT '云主机数量',
  PRIMARY KEY (`uuid`),
  KEY `business_uuid` (`business_uuid`),
  CONSTRAINT `instance_num_ibfk_1` FOREIGN KEY (`business_uuid`) REFERENCES `business` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `resource_frame` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `id` varchar(128) DEFAULT NULL,
  `itype` int(4) DEFAULT NULL COMMENT '图像类型 1:instance, 2:conp, 3,link',
  `x` int(11) DEFAULT NULL COMMENT 'x坐标',
  `y` int(11) DEFAULT NULL COMMENT 'y坐标',
  `image` varchar(300) DEFAULT NULL COMMENT '图片路径',
  `zindex` int(11) DEFAULT NULL COMMENT '层次',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '设备的UUID',
  `otype` int(11) DEFAULT NULL COMMENT '1:rack, 2:bladecenter, 3:host, 4:mimiMachine, 5:physicalMachine, 6:systemStorage, 7:cloudStorage',
  `sn` int(11) DEFAULT NULL COMMENT '控制点序号',
  `form_sn` int(11) DEFAULT NULL COMMENT '开始点sn',
  `to_sn` int(11) DEFAULT NULL COMMENT '结束点sn',
  `lw` int(11) DEFAULT NULL COMMENT '线的宽度',
  `ars` int(11) DEFAULT NULL COMMENT '有无箭头',
  `stc` varchar(128) DEFAULT NULL COMMENT '线的颜色',
  `dp` int(4) DEFAULT NULL COMMENT '0:实线, 5：虚线',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;