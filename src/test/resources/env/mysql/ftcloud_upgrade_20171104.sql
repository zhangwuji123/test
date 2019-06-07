ALTER TABLE `hypervisor_server_container`
ADD COLUMN `domain`  varchar(128) NULL COMMENT '域' AFTER `vm_templatefolder`,
ADD COLUMN `az`  varchar(128) NULL COMMENT '可用区' AFTER `domain`,
ADD COLUMN `project`  varchar(128) NULL COMMENT '项目' AFTER `az`,
ADD COLUMN `project_admin`  varchar(128) NULL COMMENT '项目管理员' AFTER `project`,
ADD COLUMN `project_password`  varchar(128) NULL COMMENT '项目密码' AFTER `project_admin`;
