CREATE TABLE `notice` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '名称标题',
  `description` varchar(300) DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告表';
CREATE TABLE `banner` (
  `uuid` varchar(128) NOT NULL COMMENT '唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `title_image_path` varchar(300) DEFAULT NULL COMMENT '标题图片路径',
  `content_image_path` varchar(300) DEFAULT NULL COMMENT '内容图片路径',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '穿件时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自服务首页轮播图片的小图和点进去的大图，默认显示前三个';
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('53', NULL, '公告', '99', '/notice', NULL, 'notice');
INSERT INTO `sys_module` (`uuid`, `description`, `name`, `priority`, `url`, `parent_uuid`, `sn`) VALUES ('54', NULL, '轮播广告', '99', '/banner', NULL, 'banner');