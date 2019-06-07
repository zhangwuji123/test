ALTER TABLE `hypervisor_server`
MODIFY COLUMN `ip`  char(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `alias`;