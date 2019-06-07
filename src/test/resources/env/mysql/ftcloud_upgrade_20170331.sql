ALTER TABLE `network`
ADD COLUMN `bg_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '后台名称' AFTER `uuid`;