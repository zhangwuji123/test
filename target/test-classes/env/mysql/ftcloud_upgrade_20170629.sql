ALTER TABLE `cluster`
ADD COLUMN `auto_sync`  int(4) NULL DEFAULT 1 COMMENT '0:手动，1自动' AFTER `mem_alloc`;