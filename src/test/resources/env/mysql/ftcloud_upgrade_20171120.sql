ALTER TABLE `hypervisor_server`
ADD COLUMN `uri`  varchar(128) NULL COMMENT 'uri' AFTER `alias`;