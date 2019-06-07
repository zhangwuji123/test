INSERT INTO `sysconfig` VALUES ('15', '老台区域名称', 'btv_zone_old', '老台');
INSERT INTO `sysconfig` VALUES ('16', '新台区域名称', 'btv_zone_new', 'ftcloud-zone');
INSERT INTO `sysconfig` VALUES ('17', '公有云集群名称', 'public_cloud_cluster_name', 'cloudCluster');
INSERT INTO `sysconfig` VALUES ('18', '蓝汛公有云集群名称', 'chinacache_cluster_name', '蓝讯公有云集群');
ALTER TABLE `physical_machine`
CHANGE COLUMN `decription` `description`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述' AFTER `disk_size`;
ALTER TABLE `physical_machine`
ADD COLUMN `order_uuid`  varchar(128) NULL COMMENT '关联订单表' AFTER `purpose`;
ALTER TABLE `physical_machine` ADD CONSTRAINT `physical_machine_ibfk_6` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

