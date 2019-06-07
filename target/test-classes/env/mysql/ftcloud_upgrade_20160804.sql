ALTER TABLE `template_info`
ADD COLUMN `highly_available`  int(255) NULL DEFAULT NULL COMMENT '高可用 0：否，1：是' AFTER `disk_format`;


ALTER TABLE `instance_volume_info` ADD CONSTRAINT `fk_reference_22_volume_key` FOREIGN KEY (`storage_uuid`) REFERENCES `storage` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;