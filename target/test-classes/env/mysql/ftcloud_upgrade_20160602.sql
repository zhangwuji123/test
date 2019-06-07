ALTER TABLE `single_instance_template` ADD COLUMN `quota` int(4) NULL COMMENT '配额' AFTER `storage_max_size`;
ALTER TABLE `networks` DROP FOREIGN KEY `networks_ibfk_1`;
ALTER TABLE `networks`
DROP COLUMN `cluster_uuid`,
DROP INDEX `cluster_uuid`;
ALTER TABLE `networks` DROP FOREIGN KEY `networks_ibfk_1`;
ALTER TABLE `networks`
DROP COLUMN `cluster_uuid`,
ADD COLUMN `hypervisor_server_container_uuid`  varchar(128) NULL COMMENT '关联虚拟化配置' AFTER `delete_time`,
ADD COLUMN `datacenter_uuid`  varchar(128) NULL COMMENT '关联数据中心' AFTER `hypervisor_server_container_uuid`,
DROP INDEX `cluster_uuid`;
ALTER TABLE `networks` ADD FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `hypervisor_server_container` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `networks` ADD FOREIGN KEY (`datacenter_uuid`) REFERENCES `datacenter` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
