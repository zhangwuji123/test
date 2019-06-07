CREATE TABLE `licence_info` (
`uuid`  varchar(128) PRIMARY key ,
`import_date`  datetime NULL COMMENT '导入日期',
`state`  int(1) NULL COMMENT '0:无效;1:有效',
`licence`  text not NULL COMMENT 'base64编码的licence'
);