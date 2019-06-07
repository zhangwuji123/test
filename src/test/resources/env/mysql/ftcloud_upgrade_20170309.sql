ALTER TABLE `vnet`
MODIFY COLUMN `ip`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip' AFTER `interface_index`;