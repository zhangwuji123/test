ALTER TABLE `instance_info` ADD COLUMN due_time datetime DEFAULT NULL COMMENT '到期时间' after update_time;
INSERT INTO `columns` VALUES ('33', '到期时间', 'dueTime', 1, 1, 100);
