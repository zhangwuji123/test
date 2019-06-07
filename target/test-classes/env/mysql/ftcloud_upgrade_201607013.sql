ALTER TABLE `operation_log`
MODIFY COLUMN `object_type`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '对象类型：host/vm' AFTER `object_name`;
ALTER TABLE `operation_log`
MODIFY COLUMN `operation`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'operation:create(创建)/update(修改)/delete(删除)/take(接管)' AFTER `uuid`;


