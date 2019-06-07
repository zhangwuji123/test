CREATE TABLE `pxe_server` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '服务器名称',
  `ip_address` varchar(128) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `os_type` int(4) DEFAULT NULL COMMENT '系统类型：0 linux, 1 windows',
  `domain` varchar(128) DEFAULT NULL COMMENT '域，windows有效',
  `domain_account` varchar(128) DEFAULT NULL COMMENT '域账户，windows有效',
  `domain_password` varchar(128) DEFAULT NULL COMMENT '域密码，windows有效',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `operating_system` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `type` int(4) DEFAULT NULL COMMENT '类型 系统类型(0Linux, 1 Windows)',
  `version` varchar(128) DEFAULT NULL COMMENT '操作系统版本号',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `product_key` varchar(128) DEFAULT NULL COMMENT '产品密钥',
  `activation_code` varchar(128) DEFAULT NULL COMMENT '激活码',
  `answer_file` varchar(128) DEFAULT NULL COMMENT '应答文件',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `physical_machine_deploy` (
  `uuid` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '唯一标识',
  `name` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `admin_password` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '管理员密码',
  `username` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `disk_size` double DEFAULT NULL COMMENT '系统盘大小',
  `domain` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '域',
  `domain_account` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '域账号',
  `domain_password` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '域密码',
  `deploy_time` datetime DEFAULT NULL COMMENT '部署时间',
  `state` int(4) NOT NULL COMMENT '状态：1部署中，2完成',
  `physical_machine_uuid` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `pxe_server_uuid` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `operating_system_uuid` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`uuid`,`state`),
  KEY `physical_machine_uuid` (`physical_machine_uuid`),
  KEY `pxe_server_uuid` (`pxe_server_uuid`),
  KEY `operating_system_uuid` (`operating_system_uuid`),
  CONSTRAINT `physical_machine_deploy_ibfk_1` FOREIGN KEY (`physical_machine_uuid`) REFERENCES `physical_machine` (`uuid`),
  CONSTRAINT `physical_machine_deploy_ibfk_2` FOREIGN KEY (`pxe_server_uuid`) REFERENCES `pxe_server` (`uuid`),
  CONSTRAINT `physical_machine_deploy_ibfk_3` FOREIGN KEY (`operating_system_uuid`) REFERENCES `operating_system` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `physical_machine`
DROP COLUMN `username`,
DROP COLUMN `password`,
CHANGE COLUMN `ip` `ip_address`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该物理机的IP地址' AFTER `name`,
MODIFY COLUMN `mac`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物理机的mac地址' AFTER `ip_address`,
MODIFY COLUMN `state`  int(4) NULL DEFAULT NULL COMMENT '状态：0：异常，1：创建中，2：运行，3:  修改中，4：开机中，5：关机中，6：已关机，7：重启中，8：已删除，9部署中' AFTER `mac`,
MODIFY COLUMN `charging_rules_uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计费规则' AFTER `delete_time`,
ADD COLUMN `dns`  varchar(64) NULL COMMENT '该物理机的dns' AFTER `ip_address`,
ADD COLUMN `gateway`  varchar(64) NULL COMMENT '该物理机的网关' AFTER `dns`,
ADD COLUMN `username`  varchar(128) NULL COMMENT '物理机的用户名' AFTER `gateway`,
ADD COLUMN `password`  varchar(128) NULL COMMENT '物理机的密码' AFTER `username`,
ADD COLUMN `netmask`  varchar(64) NULL COMMENT '物理机的子网掩码' AFTER `password`,
ADD COLUMN `ipmi_ip`  varchar(64) NULL COMMENT 'ipmi的ip地址' AFTER `mac`,
ADD COLUMN `ipmi_account`  varchar(64) NULL COMMENT 'ipmi的账号' AFTER `ipmi_ip`,
ADD COLUMN `ipmi_password`  varchar(64) NULL COMMENT 'ipmi的密码' AFTER `ipmi_account`;
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('58', NULL, 'PXE服务器', '99', '/pxe', NULL, 'pxe');
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('59', NULL, '操作系统', '99', '/operatingSystem', NULL, 'operatingSystem');

