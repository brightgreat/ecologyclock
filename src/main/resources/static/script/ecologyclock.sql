/*
Navicat MySQL Data Transfer

Source Server         : PSO实施部环境
Source Server Version : 50711
Source Host           : 120.55.37.227:33306
Source Database       : clock

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2020-12-23 20:15:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(15) DEFAULT NULL COMMENT '角色权限',
  `ecologyCode` varchar(50) DEFAULT NULL,
  `ecologyPasswd` varchar(50) DEFAULT NULL,
  `ecologyAddress` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


alter table  `user`
 add column `isNingMengClock` int(11) DEFAULT 0,
 add column `ningMengCode` varchar(50) DEFAULT NULL COMMENT '柠檬',
 add column `ningMengPasswd` varchar(50) DEFAULT NULL COMMENT '柠檬',
 add column `isVpnClock` int(11) DEFAULT 0,
 add column `vpnCode` varchar(50) DEFAULT NULL COMMENT 'vpn',
 add column `vpnPasswd` varchar(50) DEFAULT NULL COMMENT 'vpn',
 add column `userDef1` varchar(50) DEFAULT NULL COMMENT 'userDef1',
 add column `userDef2` varchar(50) DEFAULT NULL COMMENT 'userDef2',
 add column `userDef3` varchar(50) DEFAULT NULL COMMENT 'userDef3',
 add column `userDef4` varchar(50) DEFAULT NULL COMMENT 'userDef4',
 add column `userDef5` varchar(50) DEFAULT NULL COMMENT 'userDef5',
 add column `userDef6` varchar(50) DEFAULT NULL COMMENT 'userDef6',
 add column `userDef7` varchar(50) DEFAULT NULL COMMENT 'userDef7',
 add column `userDef8` varchar(50) DEFAULT NULL COMMENT 'userDef8',
 add column `userDef9` varchar(50) DEFAULT NULL COMMENT 'userDef9',
 add column `userDef10` varchar(50) DEFAULT NULL COMMENT 'userDef10',
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', null, null, null);
INSERT INTO `user` VALUES ('3', '3310579331@qq.com', '1', 'admin', 'hlwang@ittx.com.cn', 'VHR4MTIzNA==', '上海通天晓信息技术有限公司');
INSERT INTO `user` VALUES ('4', '1018765067@qq.com', '1', 'user', 'wjhu@ittx.com.cn', 'SHdqMTIzNDU2', '京东亚洲一号福州长乐物流园');
INSERT INTO `user` VALUES ('22', '1120964530@qq.com', '1', 'user', 'cbzhu@ittx.com.cn', 'R2pzMDcxMDE5', '上海通天晓信息技术有限公司');
INSERT INTO `user` VALUES ('25', '277090648@qq.com', '1', 'user', 'jnwu@ittx.com.cn', 'Rm5zdDEyMzQ=', '上海通天晓信息技术有限公司');
INSERT INTO `user` VALUES ('26', 'a', 'a', 'admin', null, null, null);
INSERT INTO `user` VALUES ('27', 'b', 'a', 'user', null, null, null);
