CREATE TABLE `department_information` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '唯一标识',
  `department_id` varchar(128) DEFAULT NULL COMMENT '部门ID',
  `name` varchar(128) DEFAULT NULL COMMENT '部门名称',
  `code` varchar(128) DEFAULT NULL COMMENT '部门编码',
  `parent_code` varchar(128) DEFAULT NULL COMMENT '上级部门编码',
  `synch` int(2) DEFAULT '0' COMMENT '是否同步的',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `department_quota_uuid` varchar(128) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`uuid`),
  KEY `fk_depart_quota` (`department_quota_uuid`),
  CONSTRAINT `fk_depart_quota` FOREIGN KEY (`department_quota_uuid`) REFERENCES `department_quota` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `department_quota` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '机构表ID',
  `vm` int(10) DEFAULT NULL COMMENT '虚拟机配额',
  `pm` int(10) DEFAULT NULL COMMENT '物理机配额',
  `lb` int(10) DEFAULT NULL COMMENT '负载均载配额',
  `storage` int(10) DEFAULT NULL COMMENT '存储配额',
  `vm_used` int(10) DEFAULT NULL COMMENT '虚拟机使用',
  `pm_used` int(10) DEFAULT NULL COMMENT '物理机使用',
  `lb_used` int(10) DEFAULT NULL COMMENT '负载均载使用',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `sys_user` ADD COLUMN `department_uuid`  varchar(128) NULL COMMENT '外键关联机构表' AFTER `user_code`;