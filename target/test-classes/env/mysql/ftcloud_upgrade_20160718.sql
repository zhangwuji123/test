ALTER TABLE hypervisor_server ADD COLUMN alias varchar(128) DEFAULT NULL COMMENT '别名' AFTER uuid;


ALTER TABLE sys_user ADD COLUMN user_code varchar(128) DEFAULT NULL COMMENT '同步其它系统用户Id' AFTER balance;

CREATE TABLE `notify_information` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT 'ID',
  `notify_type` int(2) DEFAULT NULL COMMENT '通知类型 0有审批未处理 1有告警未处理 2有资源开通 3有资源回收 4资源到期提醒',
  `notify_user_uuid` varchar(128) DEFAULT NULL COMMENT '通知用户ID',
  `notify_user_name` varchar(128) DEFAULT NULL COMMENT '通知用户名称',
  `notify_user_code` varchar(128) DEFAULT NULL COMMENT '通知用户code',
  `notify_time` datetime DEFAULT NULL COMMENT '通知时间',
  `notify_fail_num` int(3) DEFAULT NULL COMMENT '通知失败次数',
  `notify_message` varchar(512) DEFAULT NULL COMMENT '通知消息信息',
  `resource_type` int(3) DEFAULT NULL COMMENT '资源类型 1为云主机 2为负载均衡 3为云存储 4物理机 5为系统存储',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '资源ID',
  `resource_name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `resource_time` datetime DEFAULT NULL COMMENT '资源过期时间',
  `resource_manager` varchar(512) DEFAULT NULL COMMENT '资源管理员',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
