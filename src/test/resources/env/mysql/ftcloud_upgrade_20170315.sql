ALTER TABLE `host_info`
MODIFY COLUMN `ip`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理ip地址';