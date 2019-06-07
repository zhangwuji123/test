UPDATE instance_info SET type = 1 where type is null;
ALTER TABLE instance_info MODIFY type int(2) DEFAULT 1 COMMENT '云主机类型（1：云主机；2：负载均衡使用的宿主机）';