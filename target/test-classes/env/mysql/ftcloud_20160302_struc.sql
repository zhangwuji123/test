alter table template_info modify type int(2) default 1 comment '类型，1：云主机；2负载均衡';
update template_info set type = 1;
alter table single_instance_template add type int(2) default 1 comment '类型，1：云主机；2负载均衡';
update single_instance_template set type = 1;
