ALTER TABLE `sys_user`
ADD COLUMN `oa_name`  varchar(50) NULL COMMENT 'OA名' AFTER `foreign_ref`,
ADD COLUMN `company`  varchar(100) NULL COMMENT '所属公司ID' AFTER `oa_name`,
ADD COLUMN `project`  varchar(100) NULL COMMENT '所属项目名称' AFTER `company`;