CREATE TABLE `software_flow_parameter_relation` (
  `uuid` varchar(128) NOT NULL,
  `software_flow_uuid` varchar(128) DEFAULT NULL,
  `software_parameter_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `software_flow_uuid` (`software_flow_uuid`),
  KEY `software_parameter_uuid` (`software_parameter_uuid`),
  CONSTRAINT `software_flow_parameter_relation_ibfk_1` FOREIGN KEY (`software_flow_uuid`) REFERENCES `software_flow` (`uuid`),
  CONSTRAINT `software_flow_parameter_relation_ibfk_2` FOREIGN KEY (`software_parameter_uuid`) REFERENCES `software_parameter` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

