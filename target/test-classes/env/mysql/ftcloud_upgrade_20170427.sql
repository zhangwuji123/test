ALTER TABLE `instance_volume_info`
ADD COLUMN `cluster_uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '关联到集群和平台' AFTER `storage_uuid`;