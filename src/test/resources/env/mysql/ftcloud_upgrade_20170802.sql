INSERT INTO `sysconfig` (`uuid`, `module_name`, `param_name`, `value`) VALUES ('10', '文件上传绝对路径', 'file_upload_absolute_path', '/usr/local/ftcloud/upload/images/');
ALTER TABLE `task_info`
MODIFY COLUMN `task_desc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'state为处理中时，存储任务相关数据，json格式' AFTER `htype`;
