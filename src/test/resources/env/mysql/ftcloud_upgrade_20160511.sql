ALTER TABLE `template_info` DROP FOREIGN KEY `fk_cluster_uuid`;
ALTER TABLE `template_info`
DROP COLUMN `cluster_uuid`,
ADD COLUMN `hypervisor_server_container_uuid`  varchar(128) NULL COMMENT '外键关联虚拟化配置表' AFTER `foreign_ref`,
ADD COLUMN `datacenter_uuid`  varchar(128) NULL COMMENT '外键关联数据中心' AFTER `hypervisor_server_container_uuid`;
ALTER TABLE `template_info` ADD CONSTRAINT `template_info_ibfk_1` FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `ftcloud`.`hypervisor_server_container` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `template_info` ADD CONSTRAINT `template_info_ibfk_2` FOREIGN KEY (`datacenter_uuid`) REFERENCES `ftcloud`.`datacenter` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;