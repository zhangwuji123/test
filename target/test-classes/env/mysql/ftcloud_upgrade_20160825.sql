ALTER TABLE cluster ADD COLUMN type int(2) DEFAULT NULL COMMENT '集群类型，1：云主机，2：物理机，3：云存储' after state;
update cluster set type = 1 ;
update cluster set type = 2 where htype = 11;
update cluster set type = 3 where htype = 10;