CREATE TABLE `lpar_io_resource` (
`uuid`  varchar(128) NOT NULL ,
`lpar_uuid`  varchar(128) NULL ,
`io_uuid`  varchar(128) NULL ,
`is_required`  int(1) NULL  COMMENT '是否为必须(0:不必须，1:必须)' ,
PRIMARY KEY (`uuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `lpar_io_resource` ADD CONSTRAINT `fk_io_uuid` FOREIGN KEY (`io_uuid`) REFERENCES `mm_io_resource` (`uuid`);
ALTER TABLE `lpar_io_resource` ADD CONSTRAINT `fk_lpar_uuid` FOREIGN KEY (`lpar_uuid`) REFERENCES `lpar_info` (`uuid`);

ALTER TABLE `mm_io_resource`
DROP COLUMN `is_required`,
DROP COLUMN `lpar_uuid`;