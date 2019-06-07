ALTER TABLE `instance_info`
ADD COLUMN `dyna_mem_enbl`  int(2) ZEROFILL NULL DEFAULT 0 COMMENT '是否开启动态内存：0 否，1 是。' AFTER `resource_protect_uuid`;
ALTER TABLE `instance_info`
ADD COLUMN `dyna_mem_min`  double(11,2) NULL DEFAULT NULL COMMENT '最小动态内存，单位：MB' AFTER `dyna_mem_enbl`;
ALTER TABLE `instance_info`
ADD COLUMN `dyna_mem_max`  double(11,2) NULL DEFAULT NULL COMMENT '最大动态内存，单位：MB' AFTER `dyna_mem_min`;
INSERT INTO `columns` VALUES ('28', '动态内存', 'dynaMemEnbl', '1', '1', '100');
INSERT INTO `columns` VALUES ('29', '动态内存最小值', 'dynaMemMin', '1', '29', '100');
INSERT INTO `columns` VALUES ('30', '动态内存最大值', 'dynaMemMax', '1', '30', '100');