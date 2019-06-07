ALTER TABLE `lpar_info`
ADD COLUMN `cur_mem`  int(11) NULL COMMENT '当前内存' AFTER `desired_mem`,
ADD COLUMN `cur_cpu`  int(11) NULL COMMENT '当前cpu' AFTER `desired_cpu`,
ADD COLUMN `os_type`  int(1) NULL COMMENT '操作系统类型：1:aixliunx; 2:vioserver;3:os400' AFTER `os_name`;