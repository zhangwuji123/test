DROP TABLE IF EXISTS `charging_rules`;
DROP TABLE IF EXISTS `unit_price`;
DROP TABLE IF EXISTS `promotion_rules`;
CREATE TABLE `charging_rules` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `product_type` int(2) NOT NULL COMMENT '产品类型：（1：云主机；2：负载均衡；3：系统存储；4：云存储；5：物理机；6：弹性调度）',
  `charge_mode` int(2) NOT NULL COMMENT '计费模式：（1：按秒计费；2：按分计费；3：按时计费；4：按天计费；5：按次计费）',
  `price` double(11,2) NOT NULL COMMENT '单价',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `comment` varchar(256) DEFAULT NULL COMMENT '内容，备注',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计费规则';
CREATE TABLE `unit_price` (
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '主键',
  `charging_rules_uuid` varchar(128) NOT NULL COMMENT '费计规则编号',
  `type` int(2) NOT NULL COMMENT '类型（1：cpu；2：mem；3：disk）',
  `price` double(11,2) NOT NULL COMMENT '单价',
  PRIMARY KEY (`uuid`),
  KEY `charging_rules_uuid` (`charging_rules_uuid`),
  CONSTRAINT `unit_price_ibfk_1` FOREIGN KEY (`charging_rules_uuid`) REFERENCES `charging_rules` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单价设置';
CREATE TABLE `promotion_rules` (
  `uuid` varchar(128) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `purchase_type` int(2) NOT NULL COMMENT '购买的产品类型（1：云主机；2：负载均衡；3：系统存储；4：云存储；5：物理机；6：弹性调度）',
  `purchase_quantity` int(11) NOT NULL COMMENT '购买的数量',
  `present_type` int(2) NOT NULL COMMENT '赠送的类型（1：云主机；2：负载均衡；3：系统存储；4：云存储；5：物理机；6：弹性调度）',
  `present_quantity` int(11) NOT NULL COMMENT '赠送的数量',
  `present_days` int(11) NOT NULL COMMENT '赠送的天数',
  `event_start_time` datetime NOT NULL COMMENT '动活开始时间',
  `event_end_time` datetime NOT NULL COMMENT '活动结束时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `comment` varchar(4096) DEFAULT NULL COMMENT '描述，备注',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='促销规则';