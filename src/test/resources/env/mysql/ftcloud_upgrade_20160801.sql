ALTER TABLE `alert` ADD COLUMN `hypervisor_server_container_uuid`  varchar(128) NULL COMMENT '外键关联虚拟化配置表' AFTER `foreign_ref`;
ALTER TABLE `alert` ADD CONSTRAINT `alert_ibfk_1` FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `ftcloud`.`hypervisor_server_container` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `alert` ADD COLUMN `unionmon_state` int(2) DEFAULT NULL COMMENT '事件通知状态  1发送未处理通知 2发送处理通知' AFTER `user_uuid`;

CREATE TABLE `unionmon_information` (
  `uuid` varchar(128) NOT NULL DEFAULT 'ID',
  `unionmon_time` datetime DEFAULT NULL COMMENT '发送通知时间',
  `unionmon_xml` text COMMENT '发送通知消息体',
  `unionmon_state` int(2) DEFAULT '0' COMMENT '发送通知状态  0为默认 1为失败 2为成功',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;