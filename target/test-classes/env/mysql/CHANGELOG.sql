--2015-12-9 孙超
create table cs_directory
(
   id                   varchar(32) not null,
   name                 varchar(50),
   path                 varchar(200),
   parent_id            varchar(32),
   create_time          datetime,
   is_shared            int comment '0：未共享，1：已共享',
   primary key (id)
);

create table cs_share
(
   id                   varchar(32) not null,
   name                 varchar(50),
   description          varchar(100) comment 'cs_directory中的path',
   share_path           varchar(100),
   share_type           int comment '1：cifs，2：nfs',
   unit_type            int comment '1：容量（单位GB），2：文件数（单位为千）',
   soft_limit           int,
   hard_limit           int,
   create_time          datetime,
   primary key (id)
);

create table cs_auth_client
(
   id                   varchar(32) not null,
   access_user          varchar(20) comment 'cifs时为用户名，nfs为ip',
   access_credence      varchar(10) comment 'md5值，cifs时为密码，nfs为null',
   permission           int,
   share_id             varchar(32),
   vm_user_id           varchar(32),
   vm_id                varchar(32),
   primary key (id)
);
--2015-12-11 盛义东
INSERT INTO `sys_module` VALUES ('29', NULL, '单实例模板管理', 99, '/singleInstanceTemplate', NULL, 'singleInstanceTemplate');
INSERT INTO `sys_module` VALUES ('30', NULL, '多实例模板管理', 99, '/multiInstanceTemplate', NULL, 'multiInstanceTemplate');

CREATE TABLE `multi_instance_template` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '多实例模板名称',
  `state` int(11) NOT NULL COMMENT '状态，0：删除，1：正常，2：发布',
  `comment` varchar(128) DEFAULT NULL COMMENT '描述，备注信息',
  `image_file` varchar(256) DEFAULT NULL COMMENT '图片文件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`uuid`),
  KEY `cluster_uuid` (`cluster_uuid`),
  KEY `template_uuid` (`template_uuid`),
  CONSTRAINT `cluster_uuid` FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`),
  CONSTRAINT `template_uuid` FOREIGN KEY (`template_uuid`) REFERENCES `template_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

--2015-12-11 王建昌
DROP TABLE  `approve_history`;
DROP TABLE  `order`;
DROP TABLE  `orders`;

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
CREATE TABLE `order_info` (
  `uuid` varchar(128) NOT NULL,
  `price` double DEFAULT '0' COMMENT '价格（暂时不做，默认值为0）',
  `shopping_detail` text COMMENT '订单详情描述',
  `state` int(2) DEFAULT NULL COMMENT '订单状态：1 处理中 2 审核通过 3审核不通过',
  `user_uuid` varchar(128) NOT NULL,
  `approve_rule_uuid` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  KEY `fk_user_1` (`user_uuid`),
  KEY `fk_approve_rule_1` (`approve_rule_uuid`),
  CONSTRAINT `fk_approve_rule_2` FOREIGN KEY (`approve_rule_uuid`) REFERENCES `approve_rule` (`uuid`),
  CONSTRAINT `fk_user_2` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--end
--2015-12-11 王建昌
DROP TABLE  `cs_auth_client`;
DROP TABLE  `cs_directory`;
DROP TABLE  `cs_share`;
CREATE TABLE `cs_auth_client` (
  `uuid` varchar(128) NOT NULL,
  `access_user` varchar(32) DEFAULT NULL COMMENT 'cifs时为用户名，nfs为ip',
  `access_credence` varchar(32) DEFAULT NULL COMMENT 'md5值，cifs时为密码，nfs为空',
  `permission` int(11) DEFAULT NULL COMMENT '权限',
  `cs_share_uuid` varchar(128) DEFAULT NULL COMMENT '外键关联cs_share',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '外键关联sys_user',
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '外键关联instance_info',
  PRIMARY KEY (`uuid`),
  KEY `fk_au_user_1` (`user_uuid`),
  KEY `fk_au_instance_1` (`instance_uuid`),
  KEY `fk_au_share_1` (`cs_share_uuid`),
  CONSTRAINT `fk_au_instance_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`),
  CONSTRAINT `fk_au_share_1` FOREIGN KEY (`cs_share_uuid`) REFERENCES `cs_share` (`uuid`),
  CONSTRAINT `fk_au_user_1` FOREIGN KEY (`user_uuid`) REFERENCES `sys_user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cs_directory` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `path` varchar(256) DEFAULT NULL COMMENT '路径',
  `parent_uuid` varchar(32) DEFAULT NULL COMMENT '父目录',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_shared` int(11) DEFAULT NULL COMMENT '0：未共享，1：已共享',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cs_share` (
  `uuid` varchar(128) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `description` varchar(100) DEFAULT NULL COMMENT 'cs_directory中的path',
  `share_path` varchar(100) DEFAULT NULL COMMENT '共享路径',
  `share_type` int(11) DEFAULT NULL COMMENT '共享类型（1：cifs，2：nfs）',
  `unit_type` int(11) DEFAULT NULL COMMENT '1：容量（单位GB），2：文件数（单位为千）',
  `soft_limit` int(11) DEFAULT NULL COMMENT '软配额（暂时不用）',
  `hard_limit` int(11) DEFAULT NULL COMMENT '配额（容量类型单位是GB）',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- end

--2015-12-16 盛义东
INSERT INTO `columns` VALUES ('24', '平台类型', 'ptype', 1, 24, '100');
INSERT INTO `columns` VALUES ('25', '显示名称', 'displayName', 1, 25, '100');

--2015-12-17 盛健勃
ALTER TABLE `storage`
ADD COLUMN `path`  varchar(128) NULL AFTER `foreign_ref`;
ALTER TABLE `storage`
ADD COLUMN `ptype`  int(11) NULL AFTER `stype`;

--2015-12-28 盛义东
alter table order_info add code varchar(128) comment '订单编号';
--2015-01-04 王建昌
DROP TABLE  `cs_auth_client`;
DROP TABLE  `cs_directory`;
DROP TABLE  `cs_share`;
DROP TABLE  `cloudstore_directory`;
DROP TABLE  `cloudstore_share`;
DROP TABLE  `cloudstore_user`;
DROP TABLE  `cloudstore_user_store`;
DROP TABLE  `cloudstore_auth_client`;
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
-- add by 王建昌 20160106
ALTER TABLE `instance_info`
ADD COLUMN `cluster_uuid`  varchar(128) NULL AFTER `host_uuid`,
ADD INDEX `cluster_uuid` (`cluster_uuid`) USING BTREE ;
ALTER TABLE `instance_info` ADD FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
-- add by 王建昌 20160106 end
--2016-1-6 秦利滨
ALTER TABLE `networks` ADD COLUMN `cluster_uuid`  varchar(128) COMMENT '集群外键';
ALTER TABLE `networks` ADD FOREIGN KEY (`cluster_uuid`) REFERENCES `cluster` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
--2016-1-6 盛义东
alter table single_instance_template add business_uuid varchar(128) comment '业务类型编号';
alter table single_instance_template add CONSTRAINT business_uuid FOREIGN KEY (business_uuid) REFERENCES business (uuid);
--2016-1-18 盛义东
alter table business add code varchar(128) comment '业务编码';
alter table business add flag int(2) comment 'BTV台内用户，台外用户是否可见。0：不可见；1：可见';

