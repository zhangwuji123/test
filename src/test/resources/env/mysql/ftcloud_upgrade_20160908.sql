ALTER TABLE `operation_log` MODIFY COLUMN `operation` varchar(128) DEFAULT '' COMMENT 'operation:create(创建)/update(修改)/delete(删除)/take(接管)';
