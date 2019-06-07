ALTER TABLE `charge_detail`
ADD COLUMN `instance_description`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '资源描述信息' AFTER `instance_type`;