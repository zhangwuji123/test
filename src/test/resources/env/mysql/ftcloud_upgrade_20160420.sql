ALTER TABLE `vnet`
ADD COLUMN `state`  int(1) NULL COMMENT '状态(0:异常；1.创建中 2.创建完成 3.删除中 4.已删除)' AFTER `network_uuid`;

ALTER TABLE `instance_volume_info`
MODIFY COLUMN `state`  int(1) NULL COMMENT '状态(0:异常；1.创建中 2.创建完成 3.删除中 4.已删除)' AFTER `provision`;