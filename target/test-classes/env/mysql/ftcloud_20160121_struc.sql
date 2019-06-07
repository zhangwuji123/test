/*
Navicat MySQL Data Transfer

Source Server         : 160.17.5.154
Source Server Version : 50620
Source Host           : 160.17.5.154:20031
Source Database       : ftcloud

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-01-21 13:52:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alert
-- ----------------------------
DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `uuid` varchar(128) NOT NULL,
  `rule_uuid` varchar(12) NOT NULL,
  `type` varchar(32) DEFAULT NULL COMMENT '告警对象类型 cluster：集群;datastore：数据存储;host：主机;vm：虚拟机;vrm：VRM节点;container：容器;',
  `ptype` int(2) DEFAULT NULL,
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `state` int(2) DEFAULT NULL COMMENT '告警对象状态；0：未处理；1：已处理',
  `subject` varchar(128) DEFAULT NULL COMMENT '告警描述',
  `alert_count` int(8) DEFAULT NULL COMMENT '告警次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建告警时间',
  `last_sent_time` datetime DEFAULT NULL COMMENT '最后发送告警时间',
  `resolved_time` datetime DEFAULT NULL COMMENT '处理告警时间',
  `alert_level` varchar(128) DEFAULT NULL COMMENT '告警级别：（CRITICAL, 严重告警/MAJOR, 重大告警/MINOR, 次要告警/WARNING, 警告告警/UNKOWN, 待定告警）',
  `alert_type` int(2) DEFAULT NULL COMMENT '告警来源 0：阀值告警 1：系统告警',
  `device_ip` char(16) DEFAULT NULL COMMENT '告警设备ip',
  `device_name` varchar(128) DEFAULT NULL COMMENT '告警设备名字',
  `description` varchar(2048) DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '系统同步报警时可能需要的唯一键',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警';

-- ----------------------------
-- Table structure for alert_rule
-- ----------------------------
DROP TABLE IF EXISTS `alert_rule`;
CREATE TABLE `alert_rule` (
  `uuid` varchar(128) NOT NULL,
  `dt_created` datetime DEFAULT NULL COMMENT '告警规则创建日期',
  `dt_updated` datetime DEFAULT NULL COMMENT '告警规则更新日期',
  `rule_name` varchar(128) DEFAULT NULL COMMENT '告警规则名称',
  `device_type` varchar(32) DEFAULT NULL COMMENT '告警设备类型：host、vm',
  `metric` varchar(128) DEFAULT NULL COMMENT '告警指标（CPU_USAGE_PERCENT, cpu使用率/MEM_USAGE_PERCENT, 内存使用率/NETWORK_IN_SPEED, 网络流入速度/STORAGE_WRITE_SPEED, 存储读出速度/STORAGE_READ_SPEED, 存储读入速度/NETWORK_OUT_SPEED, 网络流出速度）',
  `threshold` decimal(10,5) DEFAULT NULL COMMENT '第一个告警阈值',
  `threshold_two` decimal(10,5) DEFAULT NULL COMMENT '第二个告警阈值',
  `comparator` varchar(128) DEFAULT NULL COMMENT '阀值比较符，GT、EQ、LT、NEQ',
  `device_uuid` varchar(128) DEFAULT NULL COMMENT '设备uuid',
  `device_name` varchar(128) DEFAULT '' COMMENT '设备名称',
  `alert_level` varchar(128) DEFAULT NULL COMMENT '告警级别：（CRITICAL, 严重告警/MAJOR, 重大告警/MINOR, 次要告警/WARNING, 警告告警/UNKOWN, 待定告警）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_send` int(2) DEFAULT NULL COMMENT '是否发送',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警规则';

-- ----------------------------
-- Table structure for approve_history
-- ----------------------------
DROP TABLE IF EXISTS `approve_history`;
CREATE TABLE `approve_history` (
  `uuid` varchar(128) NOT NULL,
  `order_uuid` varchar(128) NOT NULL,
  `user_uuid` varchar(128) NOT NULL COMMENT '审批人',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `approve_level` int(4) DEFAULT NULL COMMENT '当前审批级别',
  `approve_result` int(4) DEFAULT '0' COMMENT '审批结果（0不同意，1同意）',
  `approve_opinion` varchar(300) DEFAULT NULL COMMENT '审批意见',
  `shopping_detail` text,
  PRIMARY KEY (`uuid`,`user_uuid`,`order_uuid`),
  KEY `fk_user_3` (`user_uuid`),
  KEY `fk_order_5` (`order_uuid`),
  KEY `uuid` (`uuid`),
  CONSTRAINT `fk_order_5` FOREIGN KEY (`order_uuid`) REFERENCES `order_info` (`uuid`),
  CONSTRAINT `fk_user_3` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for approve_rule
-- ----------------------------
DROP TABLE IF EXISTS `approve_rule`;
CREATE TABLE `approve_rule` (
  `uuid` varchar(128) NOT NULL,
  `user_uuid` varchar(128) NOT NULL COMMENT '创建人',
  `name` varchar(32) DEFAULT NULL COMMENT '规则名称',
  `state` int(4) DEFAULT '0' COMMENT '状态（0、未启用，1、已启用，2、废弃）',
  `is_default` int(4) DEFAULT '0' COMMENT '是否为默认审批规则（0、否，1、是）',
  `auto` int(4) DEFAULT '0' COMMENT '手动自动(0手动,1自动)',
  `type` int(4) DEFAULT '1' COMMENT '审核规则类型（1、订单审核默认，2、模板审核）',
  `approval_level` int(4) DEFAULT NULL COMMENT '审批级别最小为1最大为系统参数配置的最大审批级别',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `disable_time` datetime DEFAULT NULL COMMENT '废弃时间',
  KEY `fk_user_5` (`user_uuid`),
  KEY `uuid` (`uuid`),
  CONSTRAINT `fk_user_5` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '业务名称',
  `description` varchar(256) DEFAULT NULL COMMENT '业务描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `platform_chief` varchar(256) DEFAULT NULL COMMENT '平台负责人',
  `business_chief` varchar(256) DEFAULT NULL COMMENT '业务负责人',
  `code` varchar(128) DEFAULT NULL COMMENT '业务编码',
  `flag` int(2) DEFAULT NULL COMMENT 'BTV台内用户，台外用户是否可见。0：不可见；1：可见',
  PRIMARY KEY (`uuid`),
  KEY `uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务表';

-- ----------------------------
-- Table structure for cloud_disk
-- ----------------------------
DROP TABLE IF EXISTS `cloud_disk`;
CREATE TABLE `cloud_disk` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '所属集群uuid',
  `state` int(2) DEFAULT NULL COMMENT '状态：0:异常；1:创建中；2：正常；3：删除中；4：已删除；5：快照中；6：挂载中；7:解挂中;',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `attach_at` datetime DEFAULT NULL COMMENT '挂载时间',
  `size_gb` int(11) DEFAULT NULL COMMENT '大小',
  `volume_type` varchar(36) DEFAULT NULL COMMENT '卷类型：SAS ;SATA ;SSD;',
  `vm_uuid` varchar(128) DEFAULT NULL COMMENT '虚机id,挂载的虚机',
  `deleted` int(2) DEFAULT NULL COMMENT '是否删除:0:有效；1:已删除；',
  `foreign_ref` varchar(100) DEFAULT NULL COMMENT '外部系统资源标示',
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `dev_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fkey_disk_vm01` (`vm_uuid`),
  KEY `fkey_disk_cluster01` (`cluster_uuid`),
  CONSTRAINT `fkey_disk_cluster01` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `fkey_disk_vm01` FOREIGN KEY (`vm_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cloudstore_auth_client
-- ----------------------------
DROP TABLE IF EXISTS `cloudstore_auth_client`;
CREATE TABLE `cloudstore_auth_client` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `access_user` varchar(32) DEFAULT NULL COMMENT '访问对象',
  `access_credence` varchar(32) DEFAULT NULL COMMENT '访问凭证',
  `permission` int(11) DEFAULT NULL COMMENT '访问权限',
  `state` int(11) DEFAULT NULL COMMENT '状态（0异常，1正在授权，2正常，3取消授权，4修改中，5删除中，6已删除）',
  `cloudstore_share_uuid` varchar(128) DEFAULT NULL COMMENT '共享id',
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '虚拟机id',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '云存储系统id',
  PRIMARY KEY (`uuid`),
  KEY `cloudstore_share_uuid` (`cloudstore_share_uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `cloudstore_auth_client_ibfk_1` FOREIGN KEY (`cloudstore_share_uuid`) REFERENCES `cloudstore_share` (`uuid`),
  CONSTRAINT `cloudstore_auth_client_ibfk_2` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授权表';

-- ----------------------------
-- Table structure for cloudstore_directory
-- ----------------------------
DROP TABLE IF EXISTS `cloudstore_directory`;
CREATE TABLE `cloudstore_directory` (
  `uuid` varchar(32) NOT NULL COMMENT 'uuid',
  `name` varchar(50) DEFAULT NULL COMMENT '目录名',
  `path` varchar(200) DEFAULT NULL COMMENT '绝对路径',
  `state` int(11) DEFAULT NULL COMMENT '状态（0异常，1创建中，2正常，3修改中，4删除中，5已删除）',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父目录id',
  `cloudstore_user_uuid` varchar(128) DEFAULT NULL COMMENT '所属用户id',
  `cluster_uuid` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_shared` int(11) DEFAULT NULL COMMENT '是否已共享',
  `foreign_ref` varchar(32) DEFAULT NULL COMMENT '云存储系统id',
  PRIMARY KEY (`uuid`),
  KEY `cloudstore_user_uuid` (`cloudstore_user_uuid`),
  KEY `clusterUuid` (`cluster_uuid`),
  CONSTRAINT `cloudstore_directory_ibfk_1` FOREIGN KEY (`cloudstore_user_uuid`) REFERENCES `cloudstore_user` (`uuid`),
  CONSTRAINT `cloudstore_directory_ibfk_2` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录表';

-- ----------------------------
-- Table structure for cloudstore_share
-- ----------------------------
DROP TABLE IF EXISTS `cloudstore_share`;
CREATE TABLE `cloudstore_share` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(50) DEFAULT NULL COMMENT '共享名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `share_type` int(11) DEFAULT NULL COMMENT '共享类型',
  `state` int(11) DEFAULT NULL COMMENT '状态（0异常，1创建中，2正常，3修改中，4删除中，5已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '云存储系统id',
  `cloudstore_user_store_uuid` varchar(128) DEFAULT NULL COMMENT '用户云存储id',
  PRIMARY KEY (`uuid`),
  KEY `FK_R2` (`cloudstore_user_store_uuid`),
  CONSTRAINT `FK_R2` FOREIGN KEY (`cloudstore_user_store_uuid`) REFERENCES `cloudstore_user_store` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='共享';

-- ----------------------------
-- Table structure for cloudstore_user
-- ----------------------------
DROP TABLE IF EXISTS `cloudstore_user`;
CREATE TABLE `cloudstore_user` (
  `uuid` varchar(32) NOT NULL COMMENT 'uuid',
  `cloudstore_user` varchar(32) DEFAULT NULL COMMENT '云存储系统用户名',
  `cloudstore_pwd` varchar(32) DEFAULT NULL COMMENT '云存储系统用户密码',
  `state` int(11) NOT NULL COMMENT '状态（0异常，1创建中，2正常，3修改中，4删除中，5已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '申请人id',
  `cluster_uuid` varchar(128) DEFAULT NULL,
  `is_main_user` int(11) DEFAULT NULL COMMENT '是否是属主用户，即与云平台对应的用户',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '云存储系统id',
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `cloudstore_user_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `cloudstore_user_ibfk_2` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='云存储用户表';

-- ----------------------------
-- Table structure for cloudstore_user_store
-- ----------------------------
DROP TABLE IF EXISTS `cloudstore_user_store`;
CREATE TABLE `cloudstore_user_store` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `size` int(11) DEFAULT NULL COMMENT '容量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int(11) DEFAULT NULL COMMENT '状态(0异常，1创建中，2正常，3修改中，4删除中，5已删除)',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '关联sys_user中用户的uuid',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '所属集群',
  `cloudstore_directory_uuid` varchar(128) DEFAULT NULL COMMENT '云存储目录uuid',
  `quota_ref_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `user_uuid` (`user_uuid`),
  KEY `cloudstore_directory_uuid` (`cloudstore_directory_uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `cloudstore_user_store_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`),
  CONSTRAINT `cloudstore_user_store_ibfk_2` FOREIGN KEY (`cloudstore_directory_uuid`) REFERENCES `cloudstore_directory` (`uuid`),
  CONSTRAINT `cloudstore_user_store_ibfk_3` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户云存储';

-- ----------------------------
-- Table structure for cluster
-- ----------------------------
DROP TABLE IF EXISTS `cluster`;
CREATE TABLE `cluster` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '集群名字',
  `location` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` int(2) DEFAULT NULL COMMENT '0.临时 1：正在创建中；2：正常；3 : 修改中 ; 4：删除中；5：已删除；',
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `cpu_usage` double(11,0) DEFAULT NULL,
  `cpu_total` double(11,0) DEFAULT NULL,
  `mem_usage` double(11,0) DEFAULT NULL COMMENT '单位为M',
  `mem_total` double(11,0) DEFAULT NULL,
  `disk_usage` double(11,0) DEFAULT NULL,
  `disk_total` double(11,0) DEFAULT NULL,
  `pool_uuid` varchar(128) DEFAULT NULL,
  `zone_uuid` varchar(128) DEFAULT NULL,
  `datacenter_uuid` varchar(128) DEFAULT NULL,
  `hypervisor_server_container_uuid` varchar(128) DEFAULT NULL,
  `alias` varchar(128) DEFAULT NULL COMMENT '别名',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_7` (`datacenter_uuid`),
  KEY `fk_reference_8` (`zone_uuid`),
  KEY `fk_reference_9` (`pool_uuid`),
  KEY `hypervisor_server_container_uuid` (`hypervisor_server_container_uuid`),
  CONSTRAINT `cluster_ibfk_1` FOREIGN KEY (`hypervisor_server_container_uuid`) REFERENCES `hypervisor_server_container` (`uuid`),
  CONSTRAINT `fk_reference_7` FOREIGN KEY (`datacenter_uuid`) REFERENCES `datacenter` (`uuid`),
  CONSTRAINT `fk_reference_8` FOREIGN KEY (`zone_uuid`) REFERENCES `zone` (`uuid`),
  CONSTRAINT `fk_reference_9` FOREIGN KEY (`pool_uuid`) REFERENCES `pool` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for columns
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL COMMENT '列名称',
  `name_key` varchar(32) DEFAULT NULL,
  `visible` int(11) DEFAULT '1' COMMENT '是否显示(0:隐藏;1显示)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `width` int(11) DEFAULT '100' COMMENT '宽度',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for datacenter
-- ----------------------------
DROP TABLE IF EXISTS `datacenter`;
CREATE TABLE `datacenter` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `location` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cpu_usage` double(11,0) DEFAULT NULL,
  `cpu_total` double(11,0) DEFAULT NULL,
  `mem_usage` double(11,0) DEFAULT NULL,
  `mem_total` double(11,0) DEFAULT NULL,
  `disk_usage` double(11,0) DEFAULT NULL,
  `disk_total` double(11,0) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for disk_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `disk_snapshot`;
CREATE TABLE `disk_snapshot` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '所属集群uuid',
  `state` int(2) DEFAULT NULL COMMENT '状态：0:异常；1:创建中；2：正常；3：删除中；4：已删除；5：挂载中；6：已挂载；',
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `disk_uuid` varchar(128) DEFAULT NULL,
  `size_gb` int(11) DEFAULT NULL,
  `display_name` varchar(20) DEFAULT NULL,
  `volume_type_uuid` varchar(36) DEFAULT NULL,
  `deleted` int(2) DEFAULT NULL COMMENT '是否删除:0:有效；1:已删除；',
  `foreign_ref` varchar(100) DEFAULT NULL,
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  PRIMARY KEY (`uuid`),
  KEY `fkey_snap_disk01` (`disk_uuid`),
  KEY `fkey_snap_cluster01` (`cluster_uuid`),
  CONSTRAINT `fkey_snap_cluster01` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `fkey_snap_disk01` FOREIGN KEY (`disk_uuid`) REFERENCES `cloud_disk` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for disk_types
-- ----------------------------
DROP TABLE IF EXISTS `disk_types`;
CREATE TABLE `disk_types` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `foreign_ref` varchar(100) DEFAULT NULL COMMENT '外部系统资源标示',
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `state` int(2) DEFAULT NULL COMMENT '状态：1:创建中；2：正常；3：删除中；4：已删除',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `deleted` int(2) DEFAULT NULL COMMENT '是否删除:0:有效；1:已删除；',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for flavor_info
-- ----------------------------
DROP TABLE IF EXISTS `flavor_info`;
CREATE TABLE `flavor_info` (
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `mem_size` int(11) NOT NULL COMMENT '内存大小Mb',
  `cpu_num` int(11) NOT NULL COMMENT 'cpu个数',
  `swap` int(11) NOT NULL COMMENT '交换盘大小',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外部插入的id',
  `root_gb` int(11) DEFAULT NULL COMMENT '根磁盘',
  `ephemeral_gb` int(11) DEFAULT NULL COMMENT '临时盘',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for host_aggregate
-- ----------------------------
DROP TABLE IF EXISTS `host_aggregate`;
CREATE TABLE `host_aggregate` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `availability_zone` varchar(128) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for host_info
-- ----------------------------
DROP TABLE IF EXISTS `host_info`;
CREATE TABLE `host_info` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(128) DEFAULT NULL COMMENT '主机名称',
  `ip` char(16) DEFAULT NULL COMMENT '管理ip地址',
  `os_user_name` varchar(128) DEFAULT NULL COMMENT '用户登陆名',
  `os_password` varchar(64) DEFAULT NULL COMMENT '用户登陆密码',
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) unsigned DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `state` int(2) unsigned DEFAULT NULL COMMENT '1：运行，2：关机，3：正在维护，4：临时',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `cpu_total` double(11,2) DEFAULT NULL,
  `cpu_alloc` double(11,2) DEFAULT NULL,
  `cpu_usage` double(11,2) DEFAULT NULL,
  `cpu_number` int(11) DEFAULT NULL,
  `mem_total` double(11,2) DEFAULT NULL,
  `mem_alloc` double(11,2) DEFAULT NULL,
  `mem_usage` double(11,2) DEFAULT NULL,
  `disk_total` double(11,2) DEFAULT NULL,
  `disk_alloc` double(11,2) DEFAULT NULL,
  `disk_usage` double(11,2) DEFAULT NULL,
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '所属机架uuid',
  `pool_uuid` varchar(128) DEFAULT NULL,
  `zone_uuid` varchar(128) DEFAULT NULL,
  `datacenter_uuid` varchar(128) DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外部插入的id',
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_10` (`datacenter_uuid`),
  KEY `fk_reference_11` (`zone_uuid`),
  KEY `fk_reference_12` (`pool_uuid`),
  KEY `fk_reference_13` (`cluster_uuid`),
  CONSTRAINT `fk_reference_10` FOREIGN KEY (`datacenter_uuid`) REFERENCES `datacenter` (`uuid`),
  CONSTRAINT `fk_reference_11` FOREIGN KEY (`zone_uuid`) REFERENCES `zone` (`uuid`),
  CONSTRAINT `fk_reference_12` FOREIGN KEY (`pool_uuid`) REFERENCES `pool` (`uuid`),
  CONSTRAINT `fk_reference_13` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物理节点';

-- ----------------------------
-- Table structure for hypervisor_server
-- ----------------------------
DROP TABLE IF EXISTS `hypervisor_server`;
CREATE TABLE `hypervisor_server` (
  `uuid` varchar(128) NOT NULL,
  `ip` char(16) NOT NULL,
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) NOT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion,50：hyper-v九门监控,51：hyper-v SCOM',
  `username` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `port` int(8) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `token` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hypervisor_server_container
-- ----------------------------
DROP TABLE IF EXISTS `hypervisor_server_container`;
CREATE TABLE `hypervisor_server_container` (
  `uuid` varchar(128) NOT NULL,
  `server_uuid` varchar(128) NOT NULL,
  `container_name` varchar(128) DEFAULT NULL,
  `vswitch_name` varchar(128) DEFAULT NULL,
  `vm_folder` varchar(128) DEFAULT NULL,
  `vm_templatefolder` varchar(128) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `server_uuid` (`server_uuid`),
  CONSTRAINT `hypervisor_server_container_ibfk_1` FOREIGN KEY (`server_uuid`) REFERENCES `hypervisor_server` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instance_info
-- ----------------------------
DROP TABLE IF EXISTS `instance_info`;
CREATE TABLE `instance_info` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(128) NOT NULL COMMENT '实例名称',
  `display_name` varchar(128) DEFAULT NULL COMMENT '显示名称，用于在页面显示',
  `cpu_num` int(11) unsigned DEFAULT NULL COMMENT 'cpu 数量',
  `mem_size` double(11,2) unsigned DEFAULT NULL COMMENT '内存容量',
  `sys_disk_size` double(11,2) unsigned DEFAULT NULL COMMENT '系统盘容量',
  `user_disk_size` double(11,2) unsigned DEFAULT NULL COMMENT '用户磁盘容量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) unsigned DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `state` int(2) unsigned DEFAULT NULL COMMENT '实例状态；0:异常；1：正在创建中；2：运行；3：关机中；4：已关机；5：删除中；6：已删除；7:开机中；9：重启中；11：暂停中；12：已暂停；13：恢复中；14：已恢复；15：接管中;16:迁移中;17:变更中;18:快照恢复中',
  `update_time` datetime DEFAULT NULL COMMENT '最近一次修改时间',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  `template_uuid` varchar(128) DEFAULT NULL COMMENT '资源镜像id',
  `host_uuid` varchar(128) DEFAULT NULL COMMENT '所属主机uuid',
  `cluster_uuid` varchar(128) DEFAULT NULL,
  `user_uuid` varchar(128) DEFAULT NULL,
  `business_uuid` varchar(128) DEFAULT NULL COMMENT '业务',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外部插入的id',
  `instance_info_detail_uuid` varchar(128) DEFAULT NULL COMMENT 'detail的id',
  `flavor_uuid` varchar(128) DEFAULT NULL COMMENT 'detail的id',
  `delete_time` datetime DEFAULT NULL,
  `vnc_port` int(11) unsigned DEFAULT NULL COMMENT 'VNC端口',
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_17` (`template_uuid`),
  KEY `fk_reference_19` (`host_uuid`),
  KEY `fk_reference_20` (`user_uuid`),
  KEY `instance_info_ibfk_1` (`business_uuid`),
  KEY `fk_ins_detail_1275` (`instance_info_detail_uuid`),
  KEY `flavor_uuid` (`flavor_uuid`),
  KEY `cluster_uuid` (`cluster_uuid`) USING BTREE,
  CONSTRAINT `fk_ins_detail_1275` FOREIGN KEY (`instance_info_detail_uuid`) REFERENCES `instance_info_detail` (`uuid`),
  CONSTRAINT `fk_reference_17` FOREIGN KEY (`template_uuid`) REFERENCES `template_info` (`uuid`),
  CONSTRAINT `fk_reference_19` FOREIGN KEY (`host_uuid`) REFERENCES `host_info` (`uuid`),
  CONSTRAINT `instance_info_ibfk_1` FOREIGN KEY (`business_uuid`) REFERENCES `business` (`uuid`),
  CONSTRAINT `instance_info_ibfk_2` FOREIGN KEY (`flavor_uuid`) REFERENCES `flavor_info` (`uuid`),
  CONSTRAINT `instance_info_ibfk_3` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instance_info_detail
-- ----------------------------
DROP TABLE IF EXISTS `instance_info_detail`;
CREATE TABLE `instance_info_detail` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `ethernet_cards_num` int(11) DEFAULT NULL COMMENT '网卡数',
  `virtual_disks_num` int(11) DEFAULT NULL COMMENT '虚拟磁盘数',
  `disk_total` decimal(11,2) DEFAULT NULL COMMENT '置备的磁盘空间',
  `disk_used` decimal(11,2) DEFAULT NULL COMMENT '已用的磁盘空间',
  `os_info` varchar(128) DEFAULT NULL COMMENT '操作系统',
  `cpu_usage` double(11,2) DEFAULT NULL,
  `mem_usage` double(11,2) DEFAULT NULL,
  `disk_usage` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instance_info_vnc
-- ----------------------------
DROP TABLE IF EXISTS `instance_info_vnc`;
CREATE TABLE `instance_info_vnc` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `url` varchar(256) DEFAULT NULL COMMENT 'vnc的url',
  `state` int(2) unsigned DEFAULT NULL COMMENT '状态；0:异常；1：创建中；2：创建完成；3：已使用；',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '所属实例uuid',
  PRIMARY KEY (`uuid`),
  KEY `ins_fk_reference_21111` (`instance_uuid`),
  CONSTRAINT `ins_fk_reference_21111` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instance_network_relation
-- ----------------------------
DROP TABLE IF EXISTS `instance_network_relation`;
CREATE TABLE `instance_network_relation` (
  `uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '虚拟机uuid',
  `network_uuid` varchar(128) DEFAULT NULL COMMENT '网络uuid',
  PRIMARY KEY (`uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  KEY `network_uuid` (`network_uuid`),
  CONSTRAINT `instance_network_relation_ibfk_111` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`),
  CONSTRAINT `instance_network_relation_ibfk_211` FOREIGN KEY (`network_uuid`) REFERENCES `networks` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instance_os
-- ----------------------------
DROP TABLE IF EXISTS `instance_os`;
CREATE TABLE `instance_os` (
  `uuid` varchar(128) NOT NULL,
  `display_name` varchar(128) DEFAULT NULL COMMENT '显示名称',
  `os_type` varchar(128) DEFAULT NULL COMMENT 'os类型',
  `os_version` varchar(128) DEFAULT NULL COMMENT 'os版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ip_usages
-- ----------------------------
DROP TABLE IF EXISTS `ip_usages`;
CREATE TABLE `ip_usages` (
  `uuid` varchar(128) NOT NULL,
  `ip_address` varchar(64) NOT NULL,
  `ippool_uuid` varchar(128) DEFAULT NULL,
  `subnet_uuid` varchar(128) NOT NULL,
  `network_uuid` varchar(128) NOT NULL,
  `allocated` int(2) DEFAULT NULL COMMENT '是否已经分配：0:未分配；1：已分配；3：预定（保留）;',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `ip_key_pool` (`ippool_uuid`),
  KEY `ip_key_subnet` (`subnet_uuid`),
  KEY `ip_key_network` (`network_uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `ip_key_network` FOREIGN KEY (`network_uuid`) REFERENCES `networks` (`uuid`),
  CONSTRAINT `ip_key_pool` FOREIGN KEY (`ippool_uuid`) REFERENCES `ipallocationpools` (`uuid`),
  CONSTRAINT `ip_key_subnet` FOREIGN KEY (`subnet_uuid`) REFERENCES `subnets` (`uuid`),
  CONSTRAINT `ip_usages_ibfk_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ipallocationpools
-- ----------------------------
DROP TABLE IF EXISTS `ipallocationpools`;
CREATE TABLE `ipallocationpools` (
  `uuid` varchar(36) NOT NULL,
  `subnet_uuid` varchar(36) DEFAULT NULL,
  `first_ip` varchar(64) NOT NULL COMMENT '起始IP',
  `last_ip` varchar(64) NOT NULL COMMENT '终止IP',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外部系统键',
  PRIMARY KEY (`uuid`),
  KEY `subnet_uuid` (`subnet_uuid`) USING BTREE,
  CONSTRAINT `ipallocationpools_ibfk_1` FOREIGN KEY (`subnet_uuid`) REFERENCES `subnets` (`uuid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ipallocations
-- ----------------------------
DROP TABLE IF EXISTS `ipallocations`;
CREATE TABLE `ipallocations` (
  `uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(36) DEFAULT NULL,
  `ip_address` varchar(64) NOT NULL,
  `subnet_uuid` varchar(128) NOT NULL,
  `network_uuid` varchar(128) NOT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ip_address`,`subnet_uuid`,`network_uuid`,`uuid`),
  KEY `network_uuid` (`network_uuid`) USING BTREE,
  KEY `subnet_uuid` (`subnet_uuid`) USING BTREE,
  KEY `instance_uuid` (`instance_uuid`) USING BTREE,
  CONSTRAINT `ipallocations_ibfk_1` FOREIGN KEY (`network_uuid`) REFERENCES `networks` (`uuid`) ON DELETE CASCADE,
  CONSTRAINT `ipallocations_ibfk_2` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`) ON DELETE CASCADE,
  CONSTRAINT `ipallocations_ibfk_3` FOREIGN KEY (`subnet_uuid`) REFERENCES `subnets` (`uuid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for multi_instance_template
-- ----------------------------
DROP TABLE IF EXISTS `multi_instance_template`;
CREATE TABLE `multi_instance_template` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '多实例模板名称',
  `state` int(11) NOT NULL COMMENT '状态，0：删除，1：正常，2：发布',
  `comment` varchar(128) DEFAULT NULL COMMENT '描述，备注信息',
  `image_file` varchar(256) DEFAULT NULL COMMENT '图片文件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for networks
-- ----------------------------
DROP TABLE IF EXISTS `networks`;
CREATE TABLE `networks` (
  `uuid` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL COMMENT '状态值 creating,deleting,modifying,normal,exception',
  `shared` tinyint(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `physical_network` varchar(255) DEFAULT NULL COMMENT '物理标示（有运维层提供）',
  `segmentation_id` varchar(255) DEFAULT NULL COMMENT 'VLAN标示',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `create_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `cluster_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `networks_ibfk_1` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `uuid` varchar(128) NOT NULL,
  `operation` varchar(32) DEFAULT '' COMMENT 'operation:create(创建)/update(修改)/delete(删除)/take(接管)',
  `object_name` varchar(128) DEFAULT '' COMMENT '对象名称',
  `object_type` varchar(32) DEFAULT '' COMMENT '对象类型：host/vm',
  `object_uuid` varchar(128) DEFAULT '0' COMMENT '对象uuid',
  `object_user_uuid` varchar(128) DEFAULT '0' COMMENT '对象用户uuid',
  `object_user_name` char(128) DEFAULT NULL COMMENT '对象用户名',
  `start_time` datetime DEFAULT NULL COMMENT '操作开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '操作停止时间',
  `operator_name` char(128) DEFAULT '' COMMENT '当前登录用户名',
  `opt_result` int(11) DEFAULT '0' COMMENT '操作结果：1:sucess/2:failed',
  `error_code` varchar(128) DEFAULT '' COMMENT '错误代码',
  `comment` varchar(2000) DEFAULT '' COMMENT '备注',
  `action` int(2) DEFAULT NULL COMMENT '动作：1开始，9结束',
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_24` (`object_user_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `uuid` varchar(128) NOT NULL,
  `price` double DEFAULT '0' COMMENT '价格（暂时不做，默认值为0）',
  `shopping_detail` text COMMENT '订单详情描述',
  `state` int(2) DEFAULT NULL COMMENT '订单状态：0取消 1未处理 2处理中 3 审核通过 4审核不通过 ',
  `user_uuid` varchar(128) NOT NULL,
  `approve_rule_uuid` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `code` varchar(128) DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`uuid`),
  KEY `fk_user_1` (`user_uuid`),
  KEY `fk_approve_rule_1` (`approve_rule_uuid`),
  CONSTRAINT `fk_approve_rule_2` FOREIGN KEY (`approve_rule_uuid`) REFERENCES `approve_rule` (`uuid`),
  CONSTRAINT `fk_user_2` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pool
-- ----------------------------
DROP TABLE IF EXISTS `pool`;
CREATE TABLE `pool` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '资源池名称',
  `location` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cpu_usage` double(11,0) DEFAULT NULL COMMENT 'cpu已分配',
  `cpu_total` double(11,0) DEFAULT NULL COMMENT 'cpu总量',
  `mem_usage` double(11,0) DEFAULT NULL COMMENT '内存已分配',
  `mem_total` double(11,0) DEFAULT NULL COMMENT '内存总量',
  `disk_usage` double(11,0) DEFAULT NULL COMMENT '磁盘已分配',
  `disk_total` double(11,0) DEFAULT NULL COMMENT '磁盘总量',
  `zone_uuid` varchar(128) DEFAULT NULL,
  `datacenter_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_15` (`datacenter_uuid`),
  KEY `fk_reference_16` (`zone_uuid`),
  CONSTRAINT `fk_reference_15` FOREIGN KEY (`datacenter_uuid`) REFERENCES `datacenter` (`uuid`),
  CONSTRAINT `fk_reference_16` FOREIGN KEY (`zone_uuid`) REFERENCES `zone` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `collection_name` varchar(128) DEFAULT NULL COMMENT 'mongodb集合名字',
  `type` int(11) DEFAULT NULL COMMENT '报表类型；1为cpu，2为内存',
  `create_time` datetime DEFAULT NULL,
  `cluster_name` varchar(128) DEFAULT NULL,
  `datacenter_name` varchar(128) DEFAULT NULL,
  `host_name` varchar(128) DEFAULT NULL,
  `pool_name` varchar(128) DEFAULT NULL,
  `user_uuid` varchar(128) DEFAULT NULL,
  `zone_name` varchar(128) DEFAULT NULL,
  `datacenter_uuid` varchar(128) DEFAULT NULL,
  `zone_uuid` varchar(128) DEFAULT NULL,
  `pool_uuid` varchar(128) DEFAULT NULL,
  `cluster_uuid` varchar(128) DEFAULT NULL,
  `host_uuid` varchar(128) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0：未完成，1：完成',
  PRIMARY KEY (`uuid`),
  KEY `FK_7qv4socrj7s1oxohr44f9tc5t` (`user_uuid`),
  CONSTRAINT `FK_7qv4socrj7s1oxohr44f9tc5t` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for single_instance_template
-- ----------------------------
DROP TABLE IF EXISTS `single_instance_template`;
CREATE TABLE `single_instance_template` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `cluster_uuid` varchar(128) NOT NULL COMMENT '集群的主键',
  `template_uuid` varchar(128) NOT NULL COMMENT '镜像模板的主键',
  `ptype` int(11) NOT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(11) NOT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `name` varchar(128) NOT NULL COMMENT '单实例模板名称',
  `cpu_num` int(11) NOT NULL COMMENT 'CPU个数',
  `mem_size` int(11) NOT NULL COMMENT '内存大小',
  `disk_size` int(11) NOT NULL COMMENT '硬盘大小',
  `network_card_num` int(11) DEFAULT NULL COMMENT '网卡数',
  `state` int(11) NOT NULL COMMENT '状态，0：删除，1：正常，2：发布',
  `comment` varchar(128) DEFAULT NULL COMMENT '描述，备注信息',
  `image_file` varchar(256) DEFAULT NULL COMMENT '图片文件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `business_uuid` varchar(128) DEFAULT NULL COMMENT '业务类型编号',
  PRIMARY KEY (`uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  KEY `template_uuid` (`template_uuid`),
  KEY `business_uuid` (`business_uuid`),
  CONSTRAINT `business_uuid` FOREIGN KEY (`business_uuid`) REFERENCES `business` (`uuid`),
  CONSTRAINT `cluster_uuid` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `template_uuid` FOREIGN KEY (`template_uuid`) REFERENCES `template_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for single_multi_relation
-- ----------------------------
DROP TABLE IF EXISTS `single_multi_relation`;
CREATE TABLE `single_multi_relation` (
  `uuid` varchar(128) NOT NULL COMMENT '单实例与多实例关联表主键',
  `single_instance_template_uuid` varchar(128) NOT NULL COMMENT '单实例模板',
  `multi_instance_template_uuid` varchar(128) NOT NULL COMMENT '多实例模板',
  PRIMARY KEY (`uuid`),
  KEY `single_instance_template_uuid` (`single_instance_template_uuid`),
  KEY `multi_instance_template_uuid` (`multi_instance_template_uuid`),
  CONSTRAINT `single_multi_relation_ibfk_1` FOREIGN KEY (`single_instance_template_uuid`) REFERENCES `single_instance_template` (`uuid`),
  CONSTRAINT `single_multi_relation_ibfk_2` FOREIGN KEY (`multi_instance_template_uuid`) REFERENCES `multi_instance_template` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for snapshot
-- ----------------------------
DROP TABLE IF EXISTS `snapshot`;
CREATE TABLE `snapshot` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL COMMENT '快照名称',
  `state` int(2) DEFAULT NULL COMMENT '快照状态，0:异常；1.创建中 2.创建完成 3.删除中 4.已删除 5恢复中 6已恢复 7.临时',
  `errno` int(11) DEFAULT NULL COMMENT '错误代码：1.超时 2.数据库错误 4.创建错误 8.删除错误 16.恢复错误',
  `size` int(11) DEFAULT NULL COMMENT '快照大小',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(128) DEFAULT NULL COMMENT '快照描述',
  `user_uuid` varchar(128) DEFAULT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '磁盘名称',
  `state` int(11) DEFAULT NULL COMMENT '1：可用,0：异常',
  `file_system` varchar(128) DEFAULT NULL COMMENT 'csvfs',
  `iqn` varchar(128) DEFAULT NULL COMMENT 'iSCSI Qualified Name',
  `disk_total` double(11,2) DEFAULT NULL COMMENT '单位G',
  `disk_used` double(11,2) DEFAULT NULL COMMENT '单位G',
  `disk_alloc` double(11,2) DEFAULT NULL COMMENT '单位G',
  `stype` int(11) DEFAULT NULL COMMENT '0：本地，1：共享',
  `ptype` int(11) DEFAULT NULL,
  `htype` int(11) unsigned DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外部插入的id',
  `path` varchar(128) DEFAULT NULL,
  `cluster_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storage_host_relation
-- ----------------------------
DROP TABLE IF EXISTS `storage_host_relation`;
CREATE TABLE `storage_host_relation` (
  `uuid` varchar(128) NOT NULL,
  `host_uuid` varchar(128) DEFAULT NULL COMMENT '主机uuid',
  `storage_uuid` varchar(128) DEFAULT NULL COMMENT '存储uuid',
  PRIMARY KEY (`uuid`),
  KEY `fk6ed3562b6a72d669` (`host_uuid`),
  KEY `fk6ded3562bf9d9a49` (`storage_uuid`),
  CONSTRAINT `fk6ded3562bf9d9a49` FOREIGN KEY (`storage_uuid`) REFERENCES `storage` (`uuid`),
  CONSTRAINT `fk6ed3562b6a72d669` FOREIGN KEY (`host_uuid`) REFERENCES `host_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storage_instance_relation
-- ----------------------------
DROP TABLE IF EXISTS `storage_instance_relation`;
CREATE TABLE `storage_instance_relation` (
  `uuid` varchar(128) NOT NULL,
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '虚拟机uuid',
  `storage_uuid` varchar(128) DEFAULT NULL COMMENT '存储uuid',
  PRIMARY KEY (`uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  KEY `storage_uuid` (`storage_uuid`),
  CONSTRAINT `storage_instance_relation_ibfk_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`),
  CONSTRAINT `storage_instance_relation_ibfk_2` FOREIGN KEY (`storage_uuid`) REFERENCES `storage` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subnets
-- ----------------------------
DROP TABLE IF EXISTS `subnets`;
CREATE TABLE `subnets` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(255) DEFAULT NULL,
  `network_uuid` varchar(36) DEFAULT NULL,
  `ip_version` int(11) NOT NULL,
  `cidr` varchar(64) NOT NULL,
  `gateway_ip` varchar(64) DEFAULT NULL,
  `enable_dhcp` tinyint(1) DEFAULT NULL,
  `shared` tinyint(1) DEFAULT NULL,
  `ipv6_ra_mode` enum('slaac','dhcpv6-stateful','dhcpv6-stateless') DEFAULT NULL,
  `ipv6_address_mode` enum('slaac','dhcpv6-stateful','dhcpv6-stateless') DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '外部插入的id',
  `ip_range` varchar(128) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  PRIMARY KEY (`uuid`),
  KEY `pk_network_uuid` (`network_uuid`),
  CONSTRAINT `pk_network_uuid` FOREIGN KEY (`network_uuid`) REFERENCES `networks` (`uuid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `uuid` varchar(128) NOT NULL,
  `description` varchar(128) DEFAULT NULL COMMENT '功能描述',
  `name` varchar(32) NOT NULL COMMENT '功能名称',
  `priority` int(11) DEFAULT NULL COMMENT '优先级(备用字段)',
  `url` varchar(256) NOT NULL COMMENT '功能uri',
  `parent_uuid` varchar(128) DEFAULT NULL COMMENT '父节点uuid',
  `sn` varchar(32) NOT NULL COMMENT '定义功能模块标识（user）',
  PRIMARY KEY (`uuid`),
  KEY `fk6510844bf7efb7eb` (`parent_uuid`),
  CONSTRAINT `fk6510844bf7efb7eb` FOREIGN KEY (`parent_uuid`) REFERENCES `sys_module` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_uuid` varchar(128) NOT NULL COMMENT '角色uuid',
  `permission` varchar(128) DEFAULT NULL COMMENT '角色权限（user:save）',
  KEY `fk679e22396a72d669` (`role_uuid`),
  CONSTRAINT `fk679e22396a72d669` FOREIGN KEY (`role_uuid`) REFERENCES `sys_role` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uuid` varchar(128) NOT NULL,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT 'hash密码',
  `type` int(2) unsigned DEFAULT NULL COMMENT '用户类别：1、管理员，2、普通用户',
  `approve` int(4) DEFAULT '0' COMMENT '审批资格（无审批权限用户：0，一级审批用户：1，二级审批用户：2，依次类推）',
  `salt` varchar(32) DEFAULT NULL COMMENT 'hash运算盐值',
  `status` varchar(32) NOT NULL COMMENT '状态enabled/disabled/lock',
  `realname` varchar(64) NOT NULL COMMENT '真实姓名',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(16) DEFAULT NULL COMMENT '人员手机',
  `comment` varchar(128) DEFAULT NULL COMMENT '用户备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uuid` varchar(128) NOT NULL,
  `priority` int(11) NOT NULL COMMENT '优先级（备用字段）',
  `role_uuid` varchar(128) DEFAULT NULL COMMENT '角色uuid',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '用户uuid',
  PRIMARY KEY (`uuid`),
  KEY `fk6dd3562b6a72d669` (`role_uuid`),
  KEY `fk6dd3562bf9d9a49` (`user_uuid`),
  CONSTRAINT `fk6dd3562b6a72d669` FOREIGN KEY (`role_uuid`) REFERENCES `sys_role` (`uuid`),
  CONSTRAINT `fk6dd3562bf9d9a49` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysconfig
-- ----------------------------
DROP TABLE IF EXISTS `sysconfig`;
CREATE TABLE `sysconfig` (
  `uuid` varchar(128) NOT NULL COMMENT 'id',
  `module_name` varchar(128) DEFAULT NULL COMMENT '模块名称',
  `param_name` varchar(128) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(128) DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task_info
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info` (
  `uuid` varchar(128) NOT NULL,
  `task_name` varchar(128) DEFAULT NULL COMMENT '任务名称',
  `state` int(2) DEFAULT NULL COMMENT '状态 0：异常, 1:处理中,2:完成;',
  `created_at` datetime DEFAULT NULL COMMENT '创建后更新',
  `updated_at` datetime DEFAULT NULL COMMENT '更新后变更',
  `finish_at` datetime DEFAULT NULL COMMENT '更新后变更',
  `resource_uuid` varchar(128) DEFAULT NULL COMMENT '资源ID',
  `resource_type` int(2) DEFAULT NULL COMMENT '资源类型 1：虚机，2：网络，3：存储，4：安全',
  `error_code` varchar(20) DEFAULT NULL COMMENT '错误码,定义见单独的定义或文档（后期可能会较多）',
  `error_message` varchar(500) DEFAULT NULL COMMENT '错误描述',
  `job_id` varchar(50) DEFAULT NULL,
  `ptype` int(11) DEFAULT NULL,
  `htype` int(11) DEFAULT NULL,
  `task_desc` varchar(500) DEFAULT NULL COMMENT 'state为处理中时，存储任务相关数据，json格式',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for template_info
-- ----------------------------
DROP TABLE IF EXISTS `template_info`;
CREATE TABLE `template_info` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(128) DEFAULT NULL COMMENT '镜像名',
  `version` varchar(128) DEFAULT NULL COMMENT '镜像os版本',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `type` int(2) DEFAULT '0' COMMENT '0.local 1.remote',
  `ptype` int(2) DEFAULT NULL COMMENT '资源平台类型，1：OpenStack，2：CloudStack，3：FDStack',
  `htype` int(2) DEFAULT NULL COMMENT '虚拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `size` double unsigned DEFAULT NULL COMMENT '单位gb',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int(10) unsigned DEFAULT NULL COMMENT '镜像状态，0、异常，1、可用，2、不可用，3、创建中',
  `server_ip` varchar(16) DEFAULT NULL,
  `server_state` int(11) DEFAULT '0' COMMENT '0.enable 1.disable',
  `path` char(128) DEFAULT NULL,
  `disk_format` varchar(20) DEFAULT NULL COMMENT '镜像类型 iso,qcow2,vmdk',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `cluster_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_cluster_uuid` (`cluster_uuid`),
  CONSTRAINT `fk_cluster_uuid` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vm_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `vm_snapshot`;
CREATE TABLE `vm_snapshot` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `state` int(2) DEFAULT NULL COMMENT '状态，0:异常；1：可用，2：创建中，3：删除中；6：已删除;',
  `created_at` datetime DEFAULT NULL COMMENT '创建后更新',
  `updated_at` datetime DEFAULT NULL COMMENT '更新后变更',
  `vm_uuid` varchar(128) DEFAULT NULL COMMENT '虚机ID',
  `size_gb` int(11) DEFAULT NULL,
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '后端系统资源ID标示',
  `ptype` int(2) DEFAULT NULL,
  `htype` int(2) DEFAULT NULL COMMENT '拟化类型，1：vmware，2：xenserver，3：kvm，4：hyper-v，5：华为fusion',
  `delete_at` datetime DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk__snap_vm_ref` (`vm_uuid`),
  CONSTRAINT `fk__snap_vm_ref` FOREIGN KEY (`vm_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vnet
-- ----------------------------
DROP TABLE IF EXISTS `vnet`;
CREATE TABLE `vnet` (
  `uuid` varchar(128) NOT NULL COMMENT 'uuid',
  `name` varchar(128) DEFAULT NULL,
  `interface_index` int(2) DEFAULT NULL,
  `ip` char(16) DEFAULT NULL COMMENT 'ip',
  `gateway` char(16) DEFAULT NULL COMMENT '网关',
  `mac` varchar(32) DEFAULT NULL COMMENT 'mac',
  `mask` char(16) DEFAULT NULL COMMENT 'mask',
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '所属实例uuid',
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_21` (`instance_uuid`),
  CONSTRAINT `fk_reference_21` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zone
-- ----------------------------
DROP TABLE IF EXISTS `zone`;
CREATE TABLE `zone` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '区域名字',
  `location` varchar(128) DEFAULT NULL COMMENT '区域位置',
  `cpu_usage` double(11,0) DEFAULT NULL COMMENT 'cpu已分配',
  `cpu_total` double(11,0) DEFAULT NULL COMMENT 'cpu总量',
  `mem_usage` double(11,0) DEFAULT NULL COMMENT '内存已分配',
  `mem_total` double(11,0) DEFAULT NULL COMMENT '内存总量',
  `disk_usage` double(11,0) DEFAULT NULL COMMENT '磁盘已分配',
  `disk_total` double(11,0) DEFAULT NULL COMMENT '磁盘总量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `datacenter_uuid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_reference_14` (`datacenter_uuid`),
  CONSTRAINT `fk_reference_14` FOREIGN KEY (`datacenter_uuid`) REFERENCES `datacenter` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
