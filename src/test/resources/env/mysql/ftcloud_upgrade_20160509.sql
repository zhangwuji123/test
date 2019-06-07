ALTER TABLE `instance_volume_info`
ADD COLUMN `storage_uuid` varchar(128) NULL AFTER `instance_uuid`;
