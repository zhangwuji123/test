CREATE TABLE `ci_log` (
  `uuid` varchar(128) NOT NULL,
  `operator_uuid` varchar(128) DEFAULT NULL COMMENT '操作的编号，只有删除时记录',
  `name` varchar(128) NOT NULL COMMENT '配置项名称',
  `type` int(2) NOT NULL COMMENT '类型，1：机房；2：机柜；3：供电环控；4：物理服务器；5：网络设备；6：存储；7：集群；8：逻辑服务器；9：应用系统；10：中间件；11：数据库；12：关系',
  `operation` int(2) NOT NULL COMMENT '操作，1：添加；2：修改，3：删除，4：导入',
  `result` int(2) DEFAULT NULL COMMENT '操作结果，1：成功；0：失败',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
