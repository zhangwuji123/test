ALTER TABLE single_instance_template MODIFY COLUMN state int(2) NOT NULL COMMENT '状态，0：删除，1：正常，3：下架，4：发布';
update single_instance_template set state = 4 where state = 2;
insert into sys_module (uuid,name,priority,url,sn) values ('50','性能监控',99,'/performanceMonitoring','performanceMonitoring');