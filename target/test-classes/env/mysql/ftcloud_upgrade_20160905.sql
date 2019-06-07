ALTER TABLE `elastic_gen_config`
ADD COLUMN `disk_size` double NULL COMMENT '系统磁盘大小，单位GB',
ADD COLUMN `start_ip`  varchar(15) NULL COMMENT '起始IP' AFTER `network_uuid`,
ADD COLUMN `end_ip`  varchar(15) NULL COMMENT '结束IP' AFTER `start_ip`,
ADD COLUMN `mask`  varchar(15) NULL COMMENT '掩码' AFTER `end_ip`,
ADD COLUMN `gateway`  varchar(15) NULL COMMENT '网关' AFTER `mask`;