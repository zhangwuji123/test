ALTER TABLE `hypervisor_server_container`
ADD COLUMN `state`  int(4) NULL  DEFAULT 1  COMMENT '0已删除，1可用' AFTER `vm_templatefolder`;
ALTER TABLE `hypervisor_server`
ADD COLUMN `state`  int(4) NULL  DEFAULT 1  COMMENT '0已删除，1可用' AFTER `token`;
ALTER TABLE `iso`
ADD COLUMN `state`  int(4) NULL  DEFAULT 1  COMMENT '0已删除，1可用' AFTER `cluster_uuid`;

update hypervisor_server_container set state = 1;
update hypervisor_server  set state = 1;
update iso  set state = 1;