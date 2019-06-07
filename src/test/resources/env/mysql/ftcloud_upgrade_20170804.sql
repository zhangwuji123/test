delete from sys_role_permission where permission like '%iso:%' and role_uuid  in ('ff8080815c86f19f015c9a29dd4b0415','ff8080815c86f19f015c9a29dd4b0415');
delete from sys_module where uuid = '95';
ALTER TABLE `approve_rule`
ADD PRIMARY KEY (`uuid`);
ALTER TABLE `sys_role_permission`
MODIFY COLUMN `permission`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限（user:save）' AFTER `role_uuid`,
ADD PRIMARY KEY (`role_uuid`, `permission`);
