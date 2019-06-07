ALTER TABLE `alert`	ADD COLUMN `user_uuid`  varchar(128) NULL AFTER `foreign_ref`;
ALTER TABLE `alert`	ADD COLUMN `resource_foreign_ref`  varchar(128) NULL AFTER `foreign_ref`;
ALTER TABLE `alert`	ADD COLUMN `resource_uuid`  varchar(128) NULL AFTER `foreign_ref`;