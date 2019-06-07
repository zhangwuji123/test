ALTER TABLE `firewall_router_relation`
ADD INDEX `router_id` (`router_id`) USING BTREE ;

ALTER TABLE `firewall_router_relation` ADD CONSTRAINT `FK_ROUTER_INFO_1` FOREIGN KEY (`router_id`) REFERENCES `router_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `firewalls`
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL AFTER `vpc_id`;

ALTER TABLE `firewall_rules`
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `vpc_id`;

ALTER TABLE `security_groups`
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL AFTER `foreign_ref`;

ALTER TABLE `security_group_rules`
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL AFTER `security_group_id`;


