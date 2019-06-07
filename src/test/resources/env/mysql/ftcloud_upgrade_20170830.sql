CREATE TABLE `firewall_policies` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `vpc_id` varchar(128) DEFAULT NULL COMMENT '项目ID',
  `shared` tinyint(1) DEFAULT NULL COMMENT '是否共享',
  `audited` tinyint(1) DEFAULT NULL COMMENT '是否审核',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `state` int(8) DEFAULT '1' COMMENT '0:异常 1：操作中 2：正常 3：已删除',
  PRIMARY KEY (`uuid`),
  KEY `FK_POLICY_VPC_1` (`vpc_id`),
  CONSTRAINT `FK_POLICY_VPC_1` FOREIGN KEY (`vpc_id`) REFERENCES `vpc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `firewall_policy_rule_relation` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `policy_id` varchar(128) DEFAULT NULL COMMENT '防火墙策略ID',
  `rule_id` varchar(128) DEFAULT NULL COMMENT '防火墙规则ID',
  PRIMARY KEY (`uuid`),
  KEY `policy_id` (`policy_id`) USING BTREE,
  KEY `rule_id` (`rule_id`) USING BTREE,
  CONSTRAINT `FK_POLICY_1` FOREIGN KEY (`policy_id`) REFERENCES `firewall_policies` (`uuid`),
  CONSTRAINT `FK_RULE_1` FOREIGN KEY (`rule_id`) REFERENCES `firewall_rules` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `firewalls` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `foreign_ref` varchar(128) DEFAULT NULL COMMENT '名称',
  `firewall_policy_id` varchar(128) DEFAULT NULL,
  `vpc_id` varchar(128) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `state` int(8) DEFAULT '1' COMMENT '0:异常 1:操作中 2:正常 3:已删除 4:关联路由中 5:移除路由中',
  PRIMARY KEY (`uuid`),
  KEY `FK_FIREWALL_POLICIES_1` (`firewall_policy_id`),
  CONSTRAINT `FK_FIREWALL_POLICIES_1` FOREIGN KEY (`firewall_policy_id`) REFERENCES `firewall_policies` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `firewall_rules` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `protocol` varchar(32) DEFAULT NULL COMMENT '协议（icmp,tcp,udp,or null）',
  `action` varchar(32) DEFAULT NULL COMMENT '动作(allow or deny)允许或拒绝  默认deny拒绝 ',
  `source_port` varchar(10) DEFAULT NULL COMMENT '源端口',
  `source_ip_address` varchar(128) DEFAULT NULL COMMENT '源IP地址/子网',
  `destination_port` varchar(10) DEFAULT NULL COMMENT '目的端口(80:90)',
  `destination_ip_address` varchar(128) DEFAULT NULL COMMENT '目的IP地址/子网',
  `shared` tinyint(1) DEFAULT NULL COMMENT '是不共享',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `foreign_ref` varchar(128) DEFAULT NULL,
  `vpc_id` varchar(128) DEFAULT NULL,
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `state` int(8) DEFAULT '1' COMMENT '0:异常 1:操作中 2:正常 3:已删除',
  `ip_version` varchar(8) DEFAULT '4' COMMENT 'ip版本4 或 6',
  PRIMARY KEY (`uuid`),
  KEY `FK_VPC_1` (`vpc_id`),
  CONSTRAINT `FK_VPC_1` FOREIGN KEY (`vpc_id`) REFERENCES `vpc` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `firewall_router_relation` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `firewall_id` varchar(128) DEFAULT NULL COMMENT '防火墙ID',
  `router_id` varchar(128) DEFAULT NULL COMMENT '路由器ID',
  PRIMARY KEY (`uuid`),
  KEY `firewall_id` (`firewall_id`) USING BTREE,
  CONSTRAINT `FK_FIREWALL_1` FOREIGN KEY (`firewall_id`) REFERENCES `firewalls` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `vdc_quota`
MODIFY COLUMN `quota_type`  int(2) NULL DEFAULT NULL COMMENT '1 云主机，2 CPU核数，3 内存GB，4 硬盘（GB），5公网IP（个），6快照（个），7网络（个），8安全组，9负载均衡，10物理机，11小型机，12路由器, 13防火墙' AFTER `uuid`;
ALTER TABLE `vpc_quota`
MODIFY COLUMN `quota_type`  int(2) NULL DEFAULT NULL COMMENT '1 云主机，2 CPU核数，3 内存GB，4 硬盘（GB），5公网IP（个），6快照（个），7网络（个），8安全组，9负载均衡，10物理机，11小型机，12路由器, 13安全组' AFTER `uuid`;
