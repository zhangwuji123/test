ALTER TABLE `instance_info`
ADD COLUMN `generation`  int(1) NULL DEFAULT NULL COMMENT 'Hyper-V 虚拟机代，可选值 1、 2' AFTER `charging_rules_uuid`,
ADD COLUMN `vm_id`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Hyper-V 控制台 VMID' AFTER `generation`;

ALTER TABLE `instance_volume_info`
ADD COLUMN `hard_disk_id`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Hyper-V 磁盘文件ID' AFTER `foreign_ref`;

ALTER TABLE `vpc`
ADD COLUMN `alias`  varchar(128) NULL COMMENT '别名' AFTER `uuid`;
