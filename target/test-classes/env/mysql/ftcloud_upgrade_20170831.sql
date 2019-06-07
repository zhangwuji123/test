ALTER TABLE `disk_types`
ADD COLUMN `cluster_uuid`  varchar(128) NULL COMMENT '关联集群UUID' AFTER `deleted`;
ALTER TABLE `vpc_resource`
MODIFY COLUMN `resource_type`  int(2) NULL DEFAULT NULL COMMENT '1主机，2物理机，3小机，4路由器，5外部网络，6私有网络, 7云主机，8块存储，9安全组，10防火墙，11公网IP, 12云主机快照' AFTER `isolation_mode`;
