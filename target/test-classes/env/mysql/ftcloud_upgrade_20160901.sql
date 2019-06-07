alter table datacenter add mem_alloc double(11,0) comment '内存已经分配的核数';
alter table zone add mem_alloc double(11,0) comment '内存已经分配的核数';
alter table pool add mem_alloc double(11,0) comment '内存已经分配的核数';
alter table cluster add mem_alloc double(11,0) comment '内存已经分配的核数';

update datacenter set mem_alloc = 0;
update zone set mem_alloc = 0;
update pool set mem_alloc = 0;
update cluster set mem_alloc = 0;