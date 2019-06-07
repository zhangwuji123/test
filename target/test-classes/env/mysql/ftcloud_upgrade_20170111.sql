ALTER TABLE `networks`
MODIFY COLUMN `status`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值 creating,deleting,modifying,normal,exception,upleve' AFTER `name`;
ALTER TABLE `vnet`
ADD COLUMN `network_scop_uuid`  varchar(128) NULL AFTER `network_uuid`;
ALTER TABLE `instance_network_relation` DROP FOREIGN KEY `instance_network_relation_ibfk_211`;
ALTER TABLE `instance_network_relation` ADD CONSTRAINT `instance_network_relation_ibfk_211` FOREIGN KEY (`network_uuid`) REFERENCES `network` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;


