ALTER TABLE `load_balance`
ADD COLUMN `charging_rules_uuid`  varchar(128) NULL COMMENT '关联计费规则' AFTER `order_uuid`;
ALTER TABLE `load_balance` ADD CONSTRAINT `load_balance_ibfk_2` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `charge_detail`
MODIFY COLUMN `instance_name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `instance_uuid`;
INSERT INTO `columns` VALUES ('31', '锁定状态', 'protectStatus', 1, 31, 100);
INSERT INTO `columns` VALUES ('32', '创建时间', 'createDt', 1, 32, 100);
