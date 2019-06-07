ALTER TABLE `load_balance` ADD COLUMN `state` int(2) NULL COMMENT '状态（0：异常，1：高可用配置中，2：正常，3：高可用取消中）' AFTER `hastate`;
ALTER TABLE `load_balance_backend` ADD COLUMN `forward_rule3` varchar(256) NULL COMMENT '转发规则3' AFTER `state`;
ALTER TABLE `load_balance_backend` ADD COLUMN `forward_rule2` varchar(256) NULL COMMENT '转发规则2' AFTER `state`;
ALTER TABLE `load_balance_backend` ADD COLUMN `forward_rule1` varchar(256) NULL COMMENT '转发规则1' AFTER `state`;
