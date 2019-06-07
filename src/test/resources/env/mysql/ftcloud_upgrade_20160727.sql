ALTER TABLE `cloudstore_directory`
MODIFY COLUMN `parent_id`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父目录id' AFTER `state`;