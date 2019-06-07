ALTER TABLE `physical_machine`
MODIFY COLUMN `state`  int(4) NULL DEFAULT NULL COMMENT '状态：0：异常，1：创建中，2：运行，3:  修改中，4：开机中，5：关机中，6：已关机，7：重启中，8：已删除，9部署中, 10同步中' AFTER `ipmi_password`;