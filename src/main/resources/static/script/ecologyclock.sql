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
  `ningMengCode` varchar(50) DEFAULT NULL COMMENT '柠檬',
  `ningMengPasswd` varchar(50) DEFAULT NULL COMMENT '柠檬',
  `vpnCode` varchar(50) DEFAULT NULL COMMENT 'vpn',
  `vpnPasswd` varchar(50) DEFAULT NULL COMMENT 'vpn',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

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
