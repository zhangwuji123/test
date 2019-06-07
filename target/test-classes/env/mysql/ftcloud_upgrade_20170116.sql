ALTER TABLE `task_info`
MODIFY COLUMN `job_id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `error_message`;