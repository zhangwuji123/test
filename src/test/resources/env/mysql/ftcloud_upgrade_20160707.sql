ALTER TABLE `physical_machine`
ADD COLUMN `due_time`  datetime NULL COMMENT '到期时间' AFTER `order_uuid`;
ALTER TABLE `data_disk`
ADD COLUMN `due_time`  datetime NULL COMMENT '到期时间' AFTER `state`;
ALTER TABLE `cloudstore_user_store`
ADD COLUMN `due_time`  datetime NULL COMMENT '到期时间' AFTER `size`;


