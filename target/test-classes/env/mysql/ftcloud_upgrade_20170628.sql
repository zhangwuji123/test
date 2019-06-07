ALTER TABLE `business` ADD COLUMN `parent_uuid` varchar(128) DEFAULT NULL COMMENT '关联编号' AFTER `uuid`;
ALTER TABLE `business` ADD CONSTRAINT `business_ibfk` FOREIGN KEY (`parent_uuid`) REFERENCES `business` (`uuid`);
ALTER TABLE `business` ADD COLUMN `state` int(2) DEFAULT NULL COMMENT '状态，1：正常，2：操作中' AFTER `colorpicker`;
ALTER TABLE `instance_info_detail`
ADD COLUMN `cpu_hot_plug`  int(4) NULL DEFAULT 0 COMMENT '是否支持热插拔0不支持、1支持' AFTER `gpu_enbl`,
ADD COLUMN `mem_hot_plug`  int(4) NULL DEFAULT 0 COMMENT '是否支持热插拔0不支持、1支持' AFTER `cpu_hot_plug`,
ADD COLUMN `cpu_reset_min_limit`  int(11) NULL DEFAULT 1 COMMENT '热插拔时cpu修改大小最小值限制单位核' AFTER `mem_hot_plug`,
ADD COLUMN `cpu_reset_max_limit`  int(11) NULL DEFAULT 9999 COMMENT '热插拔cpu修改大小最大值限制单位核' AFTER `cpu_reset_min_limit`,
ADD COLUMN `mem_reset_min_limit`  double(11,2) NULL DEFAULT 1024 COMMENT '热插拔内存最小值限制 默认1024M，单位MB' AFTER `cpu_reset_max_limit`,
ADD COLUMN `mem_reset_max_limit`  double(11,2) NULL DEFAULT 102400000 COMMENT '热插拔内存最小值限制 默认102400000M，单位MB' AFTER `mem_reset_min_limit`;
