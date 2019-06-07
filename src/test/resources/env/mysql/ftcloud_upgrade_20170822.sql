CREATE TABLE `lpar_virtual_io` (
  `uuid` varchar(128) NOT NULL,
  `lpar_uuid` varchar(128) NOT NULL,
  `type` int(1) NOT NULL COMMENT '设备类型：1-serial(服务器串行); 2-scsi(服务器SCSI); 3-eth(网卡); 4:fc(光纤通道)',
  `belong_to` int(1) DEFAULT NULL COMMENT 'vio属于谁：1-vios；2-client',
  `slot_number` int(11) NOT NULL COMMENT '适配器标识',
  `remote_lpar_uuid` varchar(128) DEFAULT NULL COMMENT '对于vios该值为适用lpar的uuid，如果为空则适用任意lpar；对于lpar来说是vios的uuid',
  `remote_slot_number` int(5) DEFAULT NULL COMMENT '对于vios该值为适用lpar的slot_number,如果为0，则适用lpar的任意slot; 对于lpar来说是vios的slot_number',
  `is_required` int(1) NOT NULL COMMENT '必需: 0 - no  1 - yes',
  `foreign_ref` varchar(50) DEFAULT NULL COMMENT 'slot_number@lpar@小机',
  `vlan_id` int(5) DEFAULT NULL COMMENT '网卡信息type为eth时不能为空',
  `trunk_priority` int(2) DEFAULT NULL COMMENT '网卡信息 优先级:值为1-15的数字',
  `is_ieee` int(1) DEFAULT NULL COMMENT '是否是IEEE 802.1q 兼容适配器0 - no  1 - yes',
  `additional_vlan_ids` varchar(255) DEFAULT NULL COMMENT '网卡信息 is_ieee为1时，可选的vlanids，多个用逗号分隔',
  `wwpns` varchar(255) DEFAULT NULL COMMENT '光纤通道信息，多个用逗号分隔',
  PRIMARY KEY (`uuid`),
  KEY `fk_lpar_info` (`lpar_uuid`),
  CONSTRAINT `fk_lpar_info` FOREIGN KEY (`lpar_uuid`) REFERENCES `lpar_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `lpar_info`
ADD COLUMN `is_powervm`  int(1) NULL COMMENT '是否是powervm' AFTER `user_uuid`;


ALTER TABLE `vpc_quota`
MODIFY COLUMN `quota_type`  int(2) NULL DEFAULT NULL COMMENT '1 云主机，2 CPU核数，3 内存GB，4 硬盘（GB），5公网IP（个），6快照（个），7网络（个），8安全组，9负载均衡，10物理机，11小型机，12路由器' AFTER `uuid`;
ALTER TABLE `vdc_quota`
MODIFY COLUMN `quota_type`  int(2) NULL DEFAULT NULL COMMENT '1 云主机，2 CPU核数，3 内存GB，4 硬盘（GB），5公网IP（个），6快照（个），7网络（个），8安全组，9负载均衡，10物理机，11小型机，12路由器' AFTER `uuid`;



CREATE TABLE `vdc_user_pwd` (
  `uuid` varchar(128) NOT NULL,
  `password` varchar(128) DEFAULT NULL COMMENT '密码，可解密',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '关联用户的UUID',
  PRIMARY KEY (`uuid`),
  KEY `vdc_user_pass_fk_001` (`user_uuid`),
  CONSTRAINT `vdc_user_pass_fk_001` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `router_info`
ADD COLUMN `gateway`  varchar(128) NULL COMMENT '路由网关' AFTER `foreign_ref`;


