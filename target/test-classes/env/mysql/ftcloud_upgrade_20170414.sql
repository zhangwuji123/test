ALTER TABLE `template_info`
ADD COLUMN `has_config_script`  int(4) NULL DEFAULT 0 COMMENT '是否有脚本：0无，1有' AFTER `highly_available`;