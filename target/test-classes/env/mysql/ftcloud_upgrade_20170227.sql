ALTER TABLE `network`
MODIFY COLUMN `state`  int(4) NULL DEFAULT NULL COMMENT '0、异常，1、可用，2、操作中，3、已删除' AFTER `dns`;