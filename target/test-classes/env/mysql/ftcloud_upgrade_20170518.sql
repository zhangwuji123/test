alter table template_info ADD password VARCHAR(50) COMMENT '密码';
alter table template_info ADD user_name VARCHAR(50) COMMENT '用户名';
ALTER TABLE `load_balance` ADD COLUMN `vip` varchar(128) DEFAULT NULL COMMENT '虚IP' AFTER `state`;

ALTER TABLE `instance_info`
ADD COLUMN `highly_available`  int(1) NULL DEFAULT NULL COMMENT '高可用 0：否，1：是' AFTER `charging_rules_uuid`;
