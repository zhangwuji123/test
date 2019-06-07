ALTER TABLE `order_info`
ADD COLUMN `submit_time`  datetime NULL COMMENT '资源交付时间' AFTER `code`;
ALTER TABLE `physical_machine`
MODIFY COLUMN `create_time`  datetime NOT NULL COMMENT '创建时间' AFTER `due_time`,
ADD COLUMN `network_card_number`  int(11) NULL AFTER `disk_size`,
ADD COLUMN `os`  varchar(128) NULL AFTER `network_card_number`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`uuid`, `create_time`);
ALTER TABLE `physical_machine`
MODIFY COLUMN `network_card_number`  int(11) NULL DEFAULT NULL COMMENT '网卡数量' AFTER `disk_size`,
MODIFY COLUMN `os`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统的名称' AFTER `network_card_number`;
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('52', NULL, '小型机', '99', '/miniMachine', NULL, 'miniMachine');


