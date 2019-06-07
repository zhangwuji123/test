ALTER TABLE `lpar_info`
MODIFY COLUMN `state`  int(4) NULL DEFAULT NULL COMMENT '0:异常；1：正在创建中；2：运行；3：关机中；4：已关机；5：删除中；6：已删除；7:开机中；8：开放式固件；9：硬件发现；10：迁移中;' AFTER `cur_cpu`;

