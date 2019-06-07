INSERT INTO `sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('101', '1', 'ctirix自动审批固定规格最大台数', 'ctirix_auto_size', '2');
ALTER TABLE `citrix_desktop_machine`
ADD COLUMN `order_uuid`  varchar(128) NULL AFTER `organization_uuid`;
ALTER TABLE `citrix_machine_catalog`
ADD COLUMN `planned_desktops`  int(11) NULL COMMENT '计划容纳桌面数' AFTER `foreign_ref`,
ADD COLUMN `used_desktops`  int(11) NULL COMMENT '已开通桌面数' AFTER `foreign_ref`;


ALTER TABLE `citrix_desktop_group`
ADD COLUMN `desktop_kind`  varchar(50) NULL COMMENT '桌面类型' AFTER `foreign_ref`,
ADD COLUMN `inmaintenancemode`  varchar(100) NULL COMMENT '维护模式' AFTER `foreign_ref`,
ADD COLUMN `enabled`  varchar(50) NULL COMMENT '是否开启' AFTER `foreign_ref`，
ADD COLUMN `tags`  varchar(50) NULL COMMENT '标签' AFTER `foreign_ref`;

ALTER TABLE `citrix_desktop_machine`
MODIFY COLUMN `os`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统' AFTER `order_uuid`,
ADD COLUMN `cpu`  int(5) NULL DEFAULT NULL COMMENT 'cpu 核数' AFTER `os`,
ADD COLUMN `memory`  int(5) NULL DEFAULT NULL COMMENT '   内存大小' AFTER `cpu`,
ADD COLUMN `rootdisksize`  int(5) NULL DEFAULT NULL COMMENT '根磁盘大小' AFTER `memory`,
ADD COLUMN `datadisksize`  int(5) NULL DEFAULT NULL COMMENT ' 数据磁盘大小' AFTER `rootdisksize`;

INSERT INTO `cloud`.`sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('102', '1', ' 创建云桌面初始密码', 'initPasswd', '123456');

ALTER TABLE `citrix_desktop_machine`
MODIFY COLUMN `uuid`  varchar(128) CHARACTER SET swe7 NOT NULL COMMENT 'OA账号' FIRST ,
ADD COLUMN `oaname`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `datadisksize`;


ALTER TABLE `citrix_desktop_machine`
ADD COLUMN `desktop_type`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桌面类型' AFTER `datadisksize`;

alter table sys_user add unique(username);

INSERT INTO `cloud`.`sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('106', '1', 'user desktop maxCount', 'user_desktopCount', '2');
ALTER TABLE `pool`
ADD COLUMN `state`  int(2) NULL DEFAULT 1 COMMENT '1代表使用0代表删除' AFTER `organization_uuid`;

ALTER TABLE `zone`
ADD COLUMN `state`  int(2) NULL DEFAULT 1 COMMENT '1表示使用0表示删除' AFTER `organization_uuid`;

ALTER TABLE `datacenter`
ADD COLUMN `state`  int(2) NULL DEFAULT 1 COMMENT '1代表使用0代表删除' AFTER `organization_uuid`;

ALTER TABLE `citrix_desktop_machine`
ADD COLUMN `serial_number`  varchar(128) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT 'itoms订单的真实流水号' AFTER `finish_time`;

ALTER TABLE `citrix_desktop_machine`
ADD COLUMN `expire_pierod`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'citrix云桌面有效期' AFTER `serial_number`;
ALTER TABLE `citrix_desktop_machine`
MODIFY COLUMN `expire_pierod`  datetime NULL DEFAULT NULL COMMENT 'citrix云桌面有效期' AFTER `serial_number`;



ALTER TABLE `citrix_machine_catalog`
ADD COLUMN `allocated_cpu`  int(11) NULL DEFAULT 1 COMMENT '已分配cpu' AFTER `cpu_count`,
ADD COLUMN `allocated_memory`  int(11) NULL DEFAULT 1 COMMENT '已分配内存' AFTER `memory_size`,
ADD COLUMN `allocated_disk`  int(11) NULL DEFAULT 1 COMMENT '已分配磁盘大小' AFTER `disk_size`;

ALTER TABLE `citrix_machine_catalog`
ADD COLUMN `total_cpu`  int(11) NULL DEFAULT NULL COMMENT 'cpu总量' AFTER `allocated_cpu`,
ADD COLUMN `total_memory`  int(11) NULL DEFAULT NULL COMMENT '内存总量' AFTER `allocated_memory`,
ADD COLUMN `total_disk`  int(11) NULL DEFAULT NULL COMMENT '磁盘总量' AFTER `allocated_disk`;

INSERT INTO `cloud`.`sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('107', '1', 'citrix cpu 比率', 'cpu_ratio', '0.2');
INSERT INTO `cloud`.`sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('108', '1', 'citrix memory 比率', 'memory_ratio', '0.2');
INSERT INTO `cloud`.`sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('109', '1', 'citrix disk 比率', 'disk_ratio', '0.2');


CREATE TABLE `citrix_user_password` (
  `uuid` varchar(128) NOT NULL,
  `state` int(2) DEFAULT NULL COMMENT '0.异常 1：修改中；2：修改成功；',
  `username` varchar(128) NOT NULL COMMENT '桌面用户名',
  `citrix_desktop_machine_uuid` varchar(128) NOT NULL COMMENT '桌面UUID',
  `newpassword` varchar(128) NOT NULL COMMENT '重置新密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`uuid`),
  KEY `password_citrix_desktop_machine_uuid_reference_13` (`citrix_desktop_machine_uuid`),
  CONSTRAINT `password_citrix_desktop_machine_uuid_reference_13` FOREIGN KEY (`citrix_desktop_machine_uuid`) REFERENCES `citrix_desktop_machine` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `citrix_user_password`
ADD COLUMN `itoms_state`  int(2) NULL COMMENT '0异常，1修改中，2修改成功' AFTER `finish_time`;

ALTER TABLE `citrix_user_password`
ADD COLUMN `code`  varchar(128) CHARACTER SET utf8 NULL COMMENT 'itoms重置密码的订单号' AFTER `itoms_state`;
ALTER TABLE `citrix_user_password`
ADD COLUMN `oaname`  varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL AFTER `code`;

ALTER TABLE `citrix_desktop_machine`
MODIFY COLUMN `uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL FIRST ,
ADD COLUMN `change_user_state`  int(2) NULL DEFAULT NULL COMMENT '0 失败, 1 变更中 ,2 成功,' AFTER `last_connection_time`;

INSERT INTO `cloud`.`sysconfig` (`uuid`, `visible`, `module_name`, `param_name`, `value`) VALUES ('114', '1', '最大允许创建citrix桌面数量', 'citrix_batch_max_count', '10');

ALTER TABLE `citrix_machine_catalog`
ADD COLUMN `publish_state`  int(2) NULL DEFAULT 1 COMMENT '计算机目录是否发布  0 未发布 ；1 已发布；' AFTER `vm_alloced_count`;


