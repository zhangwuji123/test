ALTER TABLE `load_balance` ADD COLUMN due_time datetime DEFAULT NULL COMMENT '到期时间' after create_time;
