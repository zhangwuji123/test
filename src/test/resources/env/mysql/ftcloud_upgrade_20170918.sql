ALTER 
VIEW `storage_instance_relation` AS 
select distinct uuid() AS `uuid`,`instance_volume_info`.`instance_uuid` AS `instance_uuid`,`instance_volume_info`.`storage_uuid` AS `storage_uuid` from `instance_volume_info` where ((`instance_volume_info`.`storage_uuid` is not null and `instance_volume_info`.instance_uuid is not null) and (`instance_volume_info`.`state` <> 4)) ;
