ALTER TABLE `pxe_server` ADD COLUMN `state`  int NULL DEFAULT 1 COMMENT '状态：0已删除，1正常' AFTER `domain_password`;
ALTER TABLE `operating_system` ADD COLUMN `state`  int NULL DEFAULT 1 COMMENT '状态:0已删除,1可用' AFTER `answer_file`;

