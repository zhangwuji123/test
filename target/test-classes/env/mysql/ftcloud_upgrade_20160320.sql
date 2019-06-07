DROP TABLE IF EXISTS `resource_protect_info`;
CREATE TABLE `resource_protect_info` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT 'uuid',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '资源ID',
  `resource_name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `resource_type` int(11) DEFAULT NULL COMMENT '资源类型',
  `protect_user_uuid` varchar(128) DEFAULT NULL COMMENT '对象用户uuid',
  `protect_username` varchar(128) DEFAULT NULL COMMENT '对象用户名',
  `protect_start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `protect_end_time` datetime DEFAULT NULL COMMENT '停止时间',
  `state` int(11) DEFAULT NULL COMMENT '资源状态 1为正常 2为锁定',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`uuid`),
  KEY `resource_uuid` (`resource_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `instance_info` ADD COLUMN `resource_protect_uuid` varchar(128) DEFAULT NULL;
ALTER TABLE `instance_info` ADD CONSTRAINT `fk_referenre_21` FOREIGN KEY (`resource_protect_uuid`) REFERENCES `resource_protect_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
