ALTER TABLE `elastic_scaling`
CHANGE COLUMN `vm_max` `vm_max` varchar(128) DEFAULT NULL COMMENT '最多实例数' AFTER `vm_min`;

ALTER TABLE `vlanid_pool`
ADD COLUMN `name`  varchar(64) NULL AFTER `uuid`;