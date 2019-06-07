ALTER TABLE multi_instance_template ADD COLUMN relation_image_file varchar(256) DEFAULT NULL COMMENT '关系图片文件' AFTER image_file;

ALTER TABLE `vnet`
ADD COLUMN `network_uuid`  varchar(128) NULL COMMENT '网络uuid' AFTER `foreign_ref`;