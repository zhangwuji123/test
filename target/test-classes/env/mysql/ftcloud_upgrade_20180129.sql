CREATE TABLE `specification` (
  `uuid` varchar(128) NOT NULL,
  `type` varchar(4) DEFAULT NULL COMMENT '1CPU,2内存，3磁盘 单位都为G',
  `value` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `organization_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `service_catalog_specification_relation` (
  `uuid` varchar(128) NOT NULL,
  `type` int(4) DEFAULT NULL COMMENT '1CPU ,2 内存，3 磁盘 ，单位都是G',
  `value` int(11) DEFAULT NULL,
  `service_catalog_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `service_catalog` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `img_file` varchar(300) DEFAULT NULL COMMENT '图片路径',
  `type` int(4) DEFAULT NULL COMMENT '类型：1云主机',
  `state` int(4) DEFAULT NULL COMMENT '状态 0：删除，1：正常，3：下架，4：发布''',
  `description` varchar(300) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `organization_uuid` varchar(128) DEFAULT NULL,
  `approve_levels` int(4) DEFAULT NULL COMMENT '几级审批',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `service_catalog_template_relation` (
  `uuid` varchar(128) NOT NULL,
  `template_uuid` varchar(128) DEFAULT NULL,
  `service_catalog_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `service_catalog_organization_visible_relation` (
  `uuid` varchar(128) NOT NULL,
  `service_catalog_uuid` varchar(128) DEFAULT NULL,
  `organization_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `sys_role`
ADD COLUMN `type`  int(4) NULL COMMENT '1管理员，2审批' AFTER `description`,
ADD COLUMN `level`  int(4) NULL COMMENT '审批级别，当为审批类型时具有审批级别' AFTER `type`;

ALTER TABLE `hypervisor_server`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;
ALTER TABLE `hypervisor_server_container`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;

ALTER TABLE `datacenter`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `mem_alloc`;

ALTER TABLE `zone`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `mem_alloc`;

ALTER TABLE `pool`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `mem_alloc`;

ALTER TABLE `cluster`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `performance_data_sync`;

ALTER TABLE `host_info`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `foreign_ref`;

ALTER TABLE `mini_machine_info`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;

ALTER TABLE `storage`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `cluster_uuid`;


ALTER TABLE `template_info`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `user_name`;

ALTER TABLE `iso`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `hypervisor_server_container_uuid`;

ALTER TABLE `network`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;

ALTER TABLE `network_device`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `state`;

ALTER TABLE `network_scop`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `state`;

ALTER TABLE `networks`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `datacenter_uuid`;

ALTER TABLE `vlanid_pool`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;


ALTER TABLE `business`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `state`;

ALTER TABLE `order_info`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `submit_time`;

ALTER TABLE `elastic_scaling`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_date`;

ALTER TABLE `operating_system`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `state`;

ALTER TABLE `pxe_server`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `state`;

ALTER TABLE `alert`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `unionmon_state`;

ALTER TABLE `operation_log`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `action`;

ALTER TABLE `task_info`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `user_uuid`;

ALTER TABLE `message`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;

ALTER TABLE `cm_data`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `type_uuid`;

ALTER TABLE `order_info`
MODIFY COLUMN `approve_rule_uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `user_uuid`;

ALTER TABLE `approve_history`
MODIFY COLUMN `order_uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `uuid`,
MODIFY COLUMN `user_uuid`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '审批人' AFTER `order_uuid`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`uuid`);

ALTER TABLE `software`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `create_time`;

ALTER TABLE `software_install`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `end_time`;

ALTER TABLE `software_parameter`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `state`;

ALTER TABLE `software_resource`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `charging_rules_uuid`;

ALTER TABLE `vdc`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `charging_rules_uuid`;

ALTER TABLE `vpc`
ADD COLUMN `organization_uuid`  varchar(128) NULL AFTER `foreign_ref`;

ALTER TABLE `service_catalog_specification_relation`
MODIFY COLUMN `value`  varchar(300) NULL DEFAULT NULL AFTER `type`;


