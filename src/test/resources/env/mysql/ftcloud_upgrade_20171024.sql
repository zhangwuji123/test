CREATE TABLE `software_parameter_relation` (
  `uuid` varchar(128) NOT NULL,
  `software_uuid` varchar(128) DEFAULT NULL,
  `software_parameter_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `software_uuid` (`software_uuid`),
  KEY `software_parameter_uuid` (`software_parameter_uuid`),
  CONSTRAINT `software_parameter_relation_ibfk_1` FOREIGN KEY (`software_uuid`) REFERENCES `software` (`uuid`),
  CONSTRAINT `software_parameter_relation_ibfk_2` FOREIGN KEY (`software_parameter_uuid`) REFERENCES `software_parameter` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
