CREATE TABLE `load_balance` (
  `uuid` varchar(128) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '负载均衡名称',
  `hastate` int(2) DEFAULT NULL COMMENT '高可用状态（1：master；2：backup）',
  `ptype` int(2) NOT NULL COMMENT '资源平台类型（1：OpenStack；2：CloudStack；3：FDStack）',
  `htype` int(2) NOT NULL COMMENT '虚拟化类型（1：vmware；2：xenserver；3：kvm；4：hyper-v；5：华为fusion）',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '宿主云主机uuid',
  `cluster_uuid` varchar(128) DEFAULT NULL COMMENT '负载均衡集群uuid',
  `user_uuid` varchar(128) DEFAULT NULL COMMENT '所属用户uuid',
  `order_uuid` varchar(128) DEFAULT NULL COMMENT '订单uuid',
  PRIMARY KEY (`uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `load_balance_ibfk_1` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `load_balance_listener` (
  `uuid` varchar(128) NOT NULL COMMENT '键主',
  `name` varchar(128) NOT NULL COMMENT '监听器名称',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `protocol` int(2) DEFAULT NULL COMMENT '监听协议（1：http；2：tcp）',
  `ip` varchar(128) DEFAULT NULL COMMENT '监听ip',
  `port` int(11) DEFAULT NULL COMMENT '监听端口',
  `vip` varchar(128) DEFAULT NULL COMMENT '拟虚ip',
  `balance` int(2) DEFAULT NULL COMMENT '负载方式（1：轮询；2：最小链接）',
  `health_check` int(2) DEFAULT NULL COMMENT '健康检查（0：不可用；1：可用）',
  `session_stricky` int(2) DEFAULT NULL COMMENT '会话保持（0：无会话；1：植入cookie；2：改写cookie）',
  `cookie_timeout` int(11) DEFAULT NULL COMMENT 'cookie超时时间（对应会话的植入，单位秒）',
  `cookie_name` varchar(128) DEFAULT NULL COMMENT 'cookie名称（对应会话的改写名称）',
  `lb_uuid` varchar(128) DEFAULT NULL COMMENT '负载均衡uuid',
  PRIMARY KEY (`uuid`),
  KEY `lb_uuid` (`lb_uuid`),
  CONSTRAINT `load_balance_listener_ibfk_1` FOREIGN KEY (`lb_uuid`) REFERENCES `load_balance` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `load_balance_backend` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `ip` varchar(128) DEFAULT NULL COMMENT '绑定的ip',
  `port` int(11) DEFAULT NULL COMMENT '绑定的端口',
  `weight` int(4) DEFAULT '1' COMMENT '权重默认为1，最大值为256，0表示不参与负载均衡',
  `state` int(2) DEFAULT NULL COMMENT '是否参与负载的状态（0：不参与；1：参与）',
  `forward_rule_type` int(2) DEFAULT NULL COMMENT '转发策略类型（1：domain；2：url）',
  `forward_rule_name` varchar(128) DEFAULT NULL COMMENT '转发策略名称',
  `forward_rule_content` varchar(256) DEFAULT NULL COMMENT '转发策略内容',
  `lbl_uuid` varchar(128) DEFAULT NULL COMMENT '监听uuid',
  `instance_uuid` varchar(128) DEFAULT NULL COMMENT '云主机uuid',
  PRIMARY KEY (`uuid`),
  KEY `listener_uuid` (`lbl_uuid`),
  KEY `instance_uuid` (`instance_uuid`),
  CONSTRAINT `load_balance_backend_ibfk_1` FOREIGN KEY (`lbl_uuid`) REFERENCES `load_balance_listener` (`uuid`),
  CONSTRAINT `load_balance_backend_ibfk_2` FOREIGN KEY (`instance_uuid`) REFERENCES `instance_info` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE instance_info ADD COLUMN type int(2) DEFAULT NULL COMMENT '云主机类型（1：云主机；2：负载均衡使用的宿主机）';
ALTER TABLE business ADD COLUMN colorpicker varchar(128) NULL COMMENT '颜色';
INSERT INTO `sys_module` VALUES ('36', null, '负载均衡', '99', '/loadBalance', null, 'loadBalance');
