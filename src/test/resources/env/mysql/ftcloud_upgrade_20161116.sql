DROP TABLE IF EXISTS `resource_display`;
CREATE TABLE `resource_display` (
  `uuid` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(128) DEFAULT NULL COMMENT '操作系统名称',
  `version_number` varchar(128) DEFAULT NULL COMMENT '操作系统版本号',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `image_name` varchar(128) DEFAULT NULL COMMENT '操作系统对应的图片',
  `resource_allocation` text COMMENT '资源配置',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `resource_display` VALUES ('1', 'Windows 2008 R2 企业版 64位', '2008', 'Iaas', 'windows.png', NULL);
INSERT INTO `resource_display` VALUES ('10', 'CentOS 7.2 64Bit', '7.2', 'Iaas', 'centos.png', NULL);
INSERT INTO `resource_display` VALUES ('11', 'openSUSE 11 64Bit', '11', 'Iaas', 'os_suse.png', NULL);
INSERT INTO `resource_display` VALUES ('12', 'openSUSE 11 64Bit', '12.3', 'Iaas', 'os_suse.png', NULL);
INSERT INTO `resource_display` VALUES ('13', 'Tomcat 7', 'java', 'Paas', 'tomcat.png', NULL);
INSERT INTO `resource_display` VALUES ('14', 'MySql 5', 'other', 'Paas', 'mysql.png', NULL);
INSERT INTO `resource_display` VALUES ('15', '快编', 'kb', 'Saas', 'icon_kb.png', 'VM0109880900,CPU:4核  内存8G  硬盘:100G,40000;VM0109880901,CPU:2核  内存4G  硬盘:100G,20000;');
INSERT INTO `resource_display` VALUES ('16', '负载均衡', 'lb', 'Saas', 'loadblance.png', 'VM0109880900,CPU:4核  内存8G  硬盘:100G,40000;VM0109880901,CPU:2核  内存4G  硬盘:100G,20000;');
INSERT INTO `resource_display` VALUES ('17', '转码', 'zb', 'Saas', 'zhuanma.png', NULL);
INSERT INTO `resource_display` VALUES ('18', '闪编', 'sb', 'Saas', 'icon_sb.png', 'VM0109880900,CPU:4核  内存8G  硬盘:100G,40000;VM0109880901,CPU:2核  内存4G  硬盘:100G,20000;');
INSERT INTO `resource_display` VALUES ('19', 'nginx 1.8.0', 'other', 'Paas', 'nginx.png', NULL);
INSERT INTO `resource_display` VALUES ('2', 'Windows 2012 R2 简体中文 64位', '2012', 'Iaas', 'windows.png', NULL);
INSERT INTO `resource_display` VALUES ('20', 'redis ', 'other', 'Paas', 'redis.png', NULL);
INSERT INTO `resource_display` VALUES ('21', 'RabbitMQ 3.5.0', 'other', 'Paas', 'rabbitmq.png', NULL);
INSERT INTO `resource_display` VALUES ('22', 'JAVA运行环境（Ubuntu 64位 | JDK1.7） ', 'java', 'Paas', 'java_jdk.png', NULL);
INSERT INTO `resource_display` VALUES ('23', 'JAVA运行环境（Windows2008 64位 | JDK1.7 | Tomcat7.0）', 'java', 'Paas', 'java_tomcat.png', NULL);
INSERT INTO `resource_display` VALUES ('24', 'JAVA运行环境（CentOS7.2 64位 | Nginx | Tomcat7 | JDK1.7）', 'java', 'Paas', 'java_tomcat.png', NULL);
INSERT INTO `resource_display` VALUES ('25', 'JAVA运行环境 (Tomcat8,MySQL5.5,Windows)', 'java', 'Paas', 'java_tomcat.png', NULL);
INSERT INTO `resource_display` VALUES ('26', 'JAVA运行环境（Windows2012 64位 Tomcat8 | JDK1.8 | MySQL 5.6 )', 'java', 'Paas', 'java_tomcat.png', NULL);
INSERT INTO `resource_display` VALUES ('27', 'JAVA运行环境（CentOS7.2 64位 Tomcat7 | JDK1.7 | MySQL5.5 )', 'java', 'Paas', 'java_tomcat.png', NULL);
INSERT INTO `resource_display` VALUES ('28', 'JAVA运行环境（Centos 64位 | JDK1.7 | Jboss7.1）', 'java', 'Paas', 'java_jboss.png', NULL);
INSERT INTO `resource_display` VALUES ('29', 'JAVA运行环境 (Apache Nginx Weblogic8/9 CentOS 6.5）', 'java', 'Paas', 'java_weblogic.png', NULL);
INSERT INTO `resource_display` VALUES ('3', 'Windows 7', '7', 'Iaas', 'windows.png', NULL);
INSERT INTO `resource_display` VALUES ('30', 'ASP/.NET运行环境（Windows2008 64位 | IIS7.0）', 'asp', 'Paas', 'asp.png', NULL);
INSERT INTO `resource_display` VALUES ('31', '多语言环境（CentOS6.5 64位 | Nginx | PHP | JAVA）', 'other', 'Paas', 'php.png', NULL);
INSERT INTO `resource_display` VALUES ('32', '多语言环境（Win2008 64位 | Python | Perl | Ruby）', 'other', 'Paas', 'ruby.png', NULL);
INSERT INTO `resource_display` VALUES ('33', '多语言环境（Centos 64位 | Python | Perl | Ruby | Erlang ）', 'other', 'Paas', 'erlang.png', NULL);
INSERT INTO `resource_display` VALUES ('34', 'Nodejs集成环境 ( CentOS7.0 64位 Apache | Nodejs | Python | Redis )', 'other', 'Paas', 'nodejs.png', NULL);
INSERT INTO `resource_display` VALUES ('35', 'Nodejs集成环境 ( Win2012 64位 Apache | Nodejs | Python | Redis )', 'other', 'Paas', 'nodejs.png', NULL);
INSERT INTO `resource_display` VALUES ('36', 'Django框架（Linux15.1 64位）', 'other', 'Paas', 'django.png', NULL);
INSERT INTO `resource_display` VALUES ('37', 'Django框架（Windows2012 64位）', 'other', 'Paas', 'django.png', NULL);
INSERT INTO `resource_display` VALUES ('38', '数据库 Oracle 11G', 'other', 'Paas', 'oracle.png', NULL);
INSERT INTO `resource_display` VALUES ('39', '数据库 MySql 5.5', 'other', 'Paas', 'mysql.png', NULL);
INSERT INTO `resource_display` VALUES ('4', 'Windows 8.1', '8.1', 'Iaas', 'windows.png', NULL);
INSERT INTO `resource_display` VALUES ('40', '数据库 Oracle 10G', 'other', 'Paas', 'oracle.png', NULL);
INSERT INTO `resource_display` VALUES ('41', '数据库 SQL Server 2008', 'other', 'Paas', 'sqlserver.png', NULL);
INSERT INTO `resource_display` VALUES ('42', '数据库 MongoDB 3.0.2', 'other', 'Paas', 'mongodb.png', NULL);
INSERT INTO `resource_display` VALUES ('43', 'PHP运行环境（CentOS7.2 64位 | Nginx | PHP5.3）', 'php', 'Paas', 'php_nginx.png', NULL);
INSERT INTO `resource_display` VALUES ('44', 'PHP运行环境(Centos6.5 64位|nginx|php多版本|mysql)', 'php', 'Paas', 'php_nginx.png', NULL);
INSERT INTO `resource_display` VALUES ('45', 'PHP运行环境（Windows2012 64位 Apache | MySQL | PHP ）', 'php', 'Paas', 'php_apache.png', NULL);
INSERT INTO `resource_display` VALUES ('46', 'PHP运行环境（CentOS7.2 64位 | Apache | PHP多版本）', 'php', 'Paas', 'php_apache.png', NULL);
INSERT INTO `resource_display` VALUES ('47', 'PHP运行环境（Windows2008 64位 Nginx | MySQL | PHP )', 'php', 'Paas', 'php_nginx.png', NULL);
INSERT INTO `resource_display` VALUES ('5', 'CentOS 6.3 64Bit', '6.3', 'Iaas', 'centos.png', NULL);
INSERT INTO `resource_display` VALUES ('50', 'FOR CDN（Windows2008 64位 | IIS7.0）', 'asp', 'Pass', 'asp.png', NULL);
INSERT INTO `resource_display` VALUES ('51', 'Windows2008 64位 | IIS7.0', 'asp', 'Pass', 'asp.png', NULL);
INSERT INTO `resource_display` VALUES ('52', 'Windows2012_R2_64位ASP|PHP|ASP.NET', 'asp', 'Pass', 'asp.png', NULL);
INSERT INTO `resource_display` VALUES ('6', 'CentOS 6.4 64Bit', '6.4', 'Iaas', 'centos.png', NULL);
INSERT INTO `resource_display` VALUES ('7', 'CentOS 6.5 64Bit', '6.5', 'Iaas', 'centos.png', NULL);
INSERT INTO `resource_display` VALUES ('8', 'CentOS 6.6 64Bit', '6.6', 'Iaas', 'centos.png', NULL);
INSERT INTO `resource_display` VALUES ('9', 'CentOS 7 64Bit', '7', 'Iaas', 'centos.png', NULL);
