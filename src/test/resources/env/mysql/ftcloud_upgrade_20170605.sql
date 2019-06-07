ALTER TABLE `software_install` ADD CONSTRAINT `software_install_ibfk` FOREIGN KEY (`instance_software_uuid`) REFERENCES `instance_software` (`uuid`);
