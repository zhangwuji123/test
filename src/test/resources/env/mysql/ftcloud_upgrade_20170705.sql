ALTER TABLE `instance_info` ADD COLUMN `power_state` int(2) unsigned DEFAULT NULL COMMENT '电源状态；1：通电；2：断电' AFTER `state`;
UPDATE instance_info SET power_state = 2;
INSERT INTO `columns` VALUES ('35', '电源状态', 'powerState', 0, 35, 100);
