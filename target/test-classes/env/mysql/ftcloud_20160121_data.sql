/*
Navicat MySQL Data Transfer

Source Server         : 160.17.5.154
Source Server Version : 50620
Source Host           : 160.17.5.154:20031
Source Database       : ftcloud

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-01-21 13:52:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES ('01', '名称', 'name', '1', '2', '150');
INSERT INTO `columns` VALUES ('02', '业务类型', 'business', '1', '25', '100');
INSERT INTO `columns` VALUES ('03', '虚拟化类型', 'htype', '1', '5', '100');
INSERT INTO `columns` VALUES ('04', '主机', 'host', '1', '8', '100');
INSERT INTO `columns` VALUES ('05', '状态', 'state', '1', '3', '100');
INSERT INTO `columns` VALUES ('06', 'IP', 'ip', '1', '13', '100');
INSERT INTO `columns` VALUES ('07', '存储', 'storage', '1', '23', '100');
INSERT INTO `columns` VALUES ('08', 'CPU使用率', 'cpu', '0', '10', '100');
INSERT INTO `columns` VALUES ('09', '内存使用率', 'mem', '1', '12', '100');
INSERT INTO `columns` VALUES ('10', '硬盘使用率', 'disk', '0', '14', '100');
INSERT INTO `columns` VALUES ('11', '平台负责人', 'platformChief', '1', '6', '100');
INSERT INTO `columns` VALUES ('12', '业务负责人', 'businessChief', '1', '7', '100');
INSERT INTO `columns` VALUES ('13', 'CPU核数', 'cpuNum', '1', '15', '100');
INSERT INTO `columns` VALUES ('14', '内存大小(G)', 'memSize', '1', '16', '100');
INSERT INTO `columns` VALUES ('15', '系统盘容量(G)', 'sysDiskSize', '1', '17', '100');
INSERT INTO `columns` VALUES ('16', '用户磁盘容量(G)', 'userDiskSize', '0', '18', '100');
INSERT INTO `columns` VALUES ('17', '操作系统', 'osInfo', '1', '9', '300');
INSERT INTO `columns` VALUES ('18', '集群', 'cluster', '1', '19', '100');
INSERT INTO `columns` VALUES ('19', '网卡数', 'ethernetCardsNum', '1', '20', '100');
INSERT INTO `columns` VALUES ('20', '虚拟磁盘数', 'virtualDisksNum', '1', '21', '100');
INSERT INTO `columns` VALUES ('21', '置备的磁盘空间(G)', 'diskTotal', '1', '22', '100');
INSERT INTO `columns` VALUES ('22', '已用的磁盘空间(G)', 'diskUsed', '0', '11', '100');
INSERT INTO `columns` VALUES ('23', '模板', 'template', '1', '24', '100');
INSERT INTO `columns` VALUES ('24', '平台类型', 'ptype', '1', '4', '100');
INSERT INTO `columns` VALUES ('25', '显示名称', 'displayName', '1', '1', '100');

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('01', null, '数据中心管理', '99', '/datacenter', null, 'datacenter');
INSERT INTO `sys_module` VALUES ('02', null, '区域管理', '99', '/zone', null, 'zone');
INSERT INTO `sys_module` VALUES ('03', null, '资源管理', '99', '/pool', null, 'pool');
INSERT INTO `sys_module` VALUES ('04', null, '集群管理', '99', '/cluster', null, 'cluster');
INSERT INTO `sys_module` VALUES ('05', null, '主机管理', '99', '/host', null, 'host');
INSERT INTO `sys_module` VALUES ('06', null, '实例管理', '99', '/instance', null, 'instance');
INSERT INTO `sys_module` VALUES ('07', null, '用户管理', '99', '/user', null, 'user');
INSERT INTO `sys_module` VALUES ('08', null, '角色管理', '99', '/role', null, 'role');
INSERT INTO `sys_module` VALUES ('09', null, '系统参数管理', '99', '/sysconfig', null, 'sysconfig');
INSERT INTO `sys_module` VALUES ('10', null, '模板管理', '99', '/image', null, 'image');
INSERT INTO `sys_module` VALUES ('11', null, 'ISO管理', '99', '/iso', null, 'iso');
INSERT INTO `sys_module` VALUES ('12', null, '日志管理', '99', '/log', null, 'log');
INSERT INTO `sys_module` VALUES ('13', null, '告警管理', '99', '/alert', null, 'alert');
INSERT INTO `sys_module` VALUES ('14', null, '监控管理', '99', '/monitor', null, 'monitor');
INSERT INTO `sys_module` VALUES ('15', null, '报表管理', '99', '/report', null, 'report');
INSERT INTO `sys_module` VALUES ('16', null, '业务管理', '99', '/business', null, 'business');
INSERT INTO `sys_module` VALUES ('17', null, '存储管理', '99', '/storage', null, 'storage');
INSERT INTO `sys_module` VALUES ('18', null, '服务器管理', '99', '/hypervisor', null, 'hypervisor');
INSERT INTO `sys_module` VALUES ('19', null, '虚拟化配置管理', '99', '/container', null, 'container');
INSERT INTO `sys_module` VALUES ('20', null, '云主机类型管理', '99', '/flavor', null, 'flavor');
INSERT INTO `sys_module` VALUES ('21', null, '网络管理', '99', '/network', null, 'network');
INSERT INTO `sys_module` VALUES ('22', null, '快照管理', '99', '/snapshot', null, 'snapshot');
INSERT INTO `sys_module` VALUES ('23', null, '子网管理', '99', '/subnet', null, 'subnet');
INSERT INTO `sys_module` VALUES ('24', null, '云盘管理', '99', '/disk', null, 'disk');
INSERT INTO `sys_module` VALUES ('25', null, '云盘快照管理', '99', '/diskSnapshot', null, 'diskSnapshot');
INSERT INTO `sys_module` VALUES ('26', null, '审批规则', '99', '/approveRule', null, 'approveRule');
INSERT INTO `sys_module` VALUES ('27', null, '订单审批', '99', '/approve', null, 'approve');
INSERT INTO `sys_module` VALUES ('28', null, '订单管理', '99', '/order', null, 'order');
INSERT INTO `sys_module` VALUES ('29', null, '单实例模板管理', '99', '/singleInstanceTemplate', null, 'singleInstanceTemplate');
INSERT INTO `sys_module` VALUES ('30', null, '多实例模板管理', '99', '/multiInstanceTemplate', null, 'multiInstanceTemplate');
INSERT INTO `sys_module` VALUES ('31', null, '云存储目录', '99', '/cloudStoreDirectory', null, 'cloudStoreDirectory');
INSERT INTO `sys_module` VALUES ('32', null, '云存储管理', '99', '/cloudStoreUserStore', null, 'cloudStoreUserStore');
INSERT INTO `sys_module` VALUES ('33', null, '云存储用户管理', '99', '/cloudStoreUser', null, 'cloudStoreUser');


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '5b512e877063d1de0b658ab72ae19b3669db733f', '1', '3', '5b2c991824798b3f', 'enabled', 'admin', 'admin@futong.com.cn', '13000000000', '', '2015-04-26 09:25:19');


-- ----------------------------
-- Records of sysconfig
-- ----------------------------
INSERT INTO `sysconfig` VALUES ('1', '报表统计开始时间', 'report_statistics_start_time', '0');
INSERT INTO `sysconfig` VALUES ('2', '报表统计结束时间', 'report_statistics_end_time', '23');
INSERT INTO `sysconfig` VALUES ('3', 'CPU最大值', 'cpu_max', '128');
INSERT INTO `sysconfig` VALUES ('4', '内存最大值', 'mem_max', '32');
INSERT INTO `sysconfig` VALUES ('5', '硬盘最大值', 'disk_max', '1024');
INSERT INTO `sysconfig` VALUES ('6', '表格刷新时间', 'table_refresh_time', '15');
INSERT INTO `sysconfig` VALUES ('7', 'vmwareVncPort', 'vmware_vnc_port', '29876');
INSERT INTO `sysconfig` VALUES ('8', 'vmwareVncUrl', 'vmware_vnc_url', '160.17.5.154');
INSERT INTO `sysconfig` VALUES ('9', '最大审批级别', 'max_approval_level', '10');