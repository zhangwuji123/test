ALTER TABLE `message`
MODIFY COLUMN `error_message`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '错误消息' AFTER `resource_name`;

