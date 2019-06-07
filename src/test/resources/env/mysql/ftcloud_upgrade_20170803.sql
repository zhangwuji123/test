ALTER TABLE `sysconfig` ADD COLUMN `visible` int(11) DEFAULT '1' COMMENT '是否显示(0:隐藏;1显示)' AFTER uuid;
ALTER TABLE `sys_module` ADD COLUMN `visible` int(11) DEFAULT '1' COMMENT '是否显示(0:隐藏;1显示)' AFTER uuid;
INSERT INTO `sysconfig` VALUES ('43', 0, '计费开关', 'charging_switch', '0');
