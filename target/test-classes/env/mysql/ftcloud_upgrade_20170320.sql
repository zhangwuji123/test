ALTER TABLE `vnet`
ADD COLUMN `sync_network_uuid`  varchar(128) NULL AFTER `network_scop_uuid`;
ALTER TABLE `vnet` ADD CONSTRAINT `fk_reference_22` FOREIGN KEY (`network_uuid`) REFERENCES `network` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `vnet` ADD CONSTRAINT `fk_reference_23` FOREIGN KEY (`network_scop_uuid`) REFERENCES `network_scop` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `vnet` ADD CONSTRAINT `fk_reference_24` FOREIGN KEY (`sync_network_uuid`) REFERENCES `networks` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

