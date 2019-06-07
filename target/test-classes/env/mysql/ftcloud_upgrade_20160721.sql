ALTER TABLE `cloudstore_directory`
MODIFY COLUMN `state`  int(11) NULL DEFAULT NULL COMMENT '状态（0异常，1创建中，2正常，3修改中，4删除中，5已删除,6同步中）' AFTER `path`;