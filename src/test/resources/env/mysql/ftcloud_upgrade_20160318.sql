ALTER TABLE task_info MODIFY COLUMN resource_type  int(2) DEFAULT NULL COMMENT '资源类型 1：云主机，2：网络，3：存储，4：安全，5：子网，6：集群，7：云主机快照，8：云存储';
ALTER TABLE task_info ADD COLUMN resource_name  varchar(128) DEFAULT NULL COMMENT '资源名称';
