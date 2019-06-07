DROP table storage_instance_relation;
create or replace view storage_instance_relation as select distinct `instance_volume_info`.`instance_uuid` AS `uuid`,`instance_volume_info`.`instance_uuid` AS `instance_uuid`,`instance_volume_info`.`storage_uuid` AS `storage_uuid` from `instance_volume_info` where (`instance_volume_info`.`storage_uuid` is not null);
