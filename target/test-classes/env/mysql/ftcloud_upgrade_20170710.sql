ALTER TABLE `elastic_scaling` ADD COLUMN `type` int(1) DEFAULT NULL COMMENT '自动伸缩组类型1云主机，2云主机配置' AFTER `step`;

ALTER TABLE `elastic_scaling`
CHANGE COLUMN `step` `step` varchar(128) DEFAULT NULL COMMENT '步长' AFTER `strategy`;