ALTER TABLE load_balance MODIFY COLUMN `hastate` int(2) DEFAULT NULL COMMENT '高可用状态（1：master；2：backup）';
INSERT INTO `sys_module` VALUES ('36', null, '负载均衡', '99', '/loadBalance', null, 'loadBalance');
