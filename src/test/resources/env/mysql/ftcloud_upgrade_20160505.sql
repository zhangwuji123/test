ALTER TABLE `instance_info_detail` ADD COLUMN `config_ip_passwd_enbl`  int(1) ZEROFILL NULL DEFAULT 0 COMMENT '是否开启配置IP和计算机名称：0 否，1 是。' AFTER `disk_usage`;
ALTER TABLE `instance_info_detail` ADD COLUMN `computer_name`  varchar(128) NULL  COMMENT '计算机名称' AFTER `disk_usage`;
ALTER TABLE `instance_info_detail` ADD COLUMN `root_password`  varchar(128) NULL  COMMENT 'Linux系统为root的密码，Windows系统为Administrator的密码' AFTER `disk_usage`;
ALTER TABLE `instance_info_detail` ADD COLUMN `ip_address`  varchar(128) NULL  COMMENT 'ip地址' AFTER `disk_usage`;
ALTER TABLE `instance_info_detail` ADD COLUMN `mask`  varchar(128) NULL  COMMENT '子网掩码' AFTER `disk_usage`;
ALTER TABLE `instance_info_detail` ADD COLUMN `gateway_address`  varchar(128) NULL  COMMENT '网关地址' AFTER `disk_usage`;