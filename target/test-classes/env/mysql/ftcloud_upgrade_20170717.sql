ALTER TABLE `elastic_scaling` ADD COLUMN `type` int(1) DEFAULT NULL COMMENT '自动伸缩组类型1云主机，2云主机配置' AFTER `step`;

ALTER TABLE `elastic_scaling`
CHANGE COLUMN `step` `step` varchar(128) DEFAULT NULL COMMENT '步长' AFTER `strategy`;

ALTER TABLE `elastic_scaling` ADD COLUMN `on_off` int(1) DEFAULT 1 COMMENT '自动伸缩组开关 1开启，0关闭' AFTER `vm_max`;