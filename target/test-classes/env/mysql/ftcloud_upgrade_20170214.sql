ALTER TABLE `ci_rack` MODIFY COLUMN `model` varchar(64) DEFAULT NULL COMMENT '设备型号';
ALTER TABLE `ci_rack` ADD COLUMN `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号' AFTER `create_time`;
ALTER TABLE `ci_gdhk` ADD COLUMN `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号' AFTER `create_time`;
ALTER TABLE `ci_physical_server` ADD COLUMN `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机柜编号' AFTER `create_time`;
ALTER TABLE `ci_physical_server` ADD COLUMN `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号' AFTER `create_time`;
ALTER TABLE `ci_network_device` ADD COLUMN `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机柜编号' AFTER `create_time`;
ALTER TABLE `ci_network_device` ADD COLUMN `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号' AFTER `create_time`;
ALTER TABLE `ci_storage` ADD COLUMN `rack_uuid` varchar(128) DEFAULT NULL COMMENT '机柜编号' AFTER `create_time`;
ALTER TABLE `ci_storage` ADD COLUMN `jf_uuid` varchar(128) DEFAULT NULL COMMENT '机房编号' AFTER `create_time`;
ALTER TABLE `ci_cluster` DROP COLUMN `ip`;
ALTER TABLE `ci_logic_server` DROP COLUMN `device_sn`;
ALTER TABLE `ci_logic_server` DROP COLUMN `storage_sn`;
ALTER TABLE `ci_logic_server` ADD COLUMN `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '集群编号' AFTER `create_time`;
ALTER TABLE `ci_logic_server` ADD COLUMN `storage_uuid` varchar(128) DEFAULT NULL COMMENT '存储编号' AFTER `create_time`;
ALTER TABLE `ci_logic_server` ADD COLUMN `physical_server_uuid` varchar(128) DEFAULT NULL COMMENT '物理服务器编号' AFTER `create_time`;
ALTER TABLE `ci_middleware` DROP COLUMN `hostname`;
ALTER TABLE `ci_middleware` ADD COLUMN `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '集群编号' AFTER `create_time`;
ALTER TABLE `ci_middleware` ADD COLUMN `host_uuid` varchar(128) DEFAULT NULL COMMENT '主机编号，逻辑服务器编号' AFTER `create_time`;
ALTER TABLE `ci_database` DROP COLUMN `hostname`;
ALTER TABLE `ci_database` ADD COLUMN `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '集群编号' AFTER `create_time`;
ALTER TABLE `ci_database` ADD COLUMN `host_uuid` varchar(128) DEFAULT NULL COMMENT '主机编号，逻辑服务器编号' AFTER `create_time`;



ALTER TABLE `ip_pool` 
ADD COLUMN `pre_allocation_state` int(4) DEFAULT '0' COMMENT '预分配状态' AFTER `state`;