/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-06-19 10:08:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', 'admin@abc.com', '20', '', '2');
INSERT INTO `employee` VALUES ('2', '赵总', 'c4ca4238a0b923820dcc509a6f75849b', 'zhaoz@wolfcode.cn', '35', '\0', '1');
INSERT INTO `employee` VALUES ('3', '赵一明', 'c4ca4238a0b923820dcc509a6f75849b', 'zhaoym@wolfcode.cn', '25', '\0', '1');
INSERT INTO `employee` VALUES ('4', '钱总', 'c4ca4238a0b923820dcc509a6f75849b', 'qianz@wolfcode.cn', '35', '\0', '2');
INSERT INTO `employee` VALUES ('5', '钱二明', 'c4ca4238a0b923820dcc509a6f75849b', 'qianem@wolfcode.cn', '25', '\0', '2');
INSERT INTO `employee` VALUES ('6', '孙总', 'c4ca4238a0b923820dcc509a6f75849b', 'sunz@wolfcode.cn', '35', '\0', '3');
INSERT INTO `employee` VALUES ('7', '孙三明', 'c4ca4238a0b923820dcc509a6f75849b', 'sunsm@wolfcode.cn', '25', '\0', '3');
INSERT INTO `employee` VALUES ('9', '李四明', 'c4ca4238a0b923820dcc509a6f75849b', 'lism@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('10', '周总', 'c4ca4238a0b923820dcc509a6f75849b', 'zhouz@wolfcode.cn', '35', '\0', '5');
INSERT INTO `employee` VALUES ('11', '周五明', 'c4ca4238a0b923820dcc509a6f75849b', 'zhouwm@wolfcode.cn', '25', '\0', '5');
INSERT INTO `employee` VALUES ('12', '吴总', 'c4ca4238a0b923820dcc509a6f75849b', 'wuz@wolfcode.cn', '35', '\0', '6');
INSERT INTO `employee` VALUES ('13', '吴六明', 'c4ca4238a0b923820dcc509a6f75849b', 'wulm@wolfcode.cn', '25', '\0', '6');
INSERT INTO `employee` VALUES ('14', '郑总', 'c4ca4238a0b923820dcc509a6f75849b', 'zhengz@wolfcode.cn', '35', '\0', '6');
INSERT INTO `employee` VALUES ('15', '郑七明', 'c4ca4238a0b923820dcc509a6f75849b', 'zhengqm@wolfcode.cn', '25', '\0', '6');
INSERT INTO `employee` VALUES ('16', '孙四明', 'c4ca4238a0b923820dcc509a6f75849b', 'sunsim@wolfcode.cn', '25', '\0', '3');
INSERT INTO `employee` VALUES ('17', '孙五明2', 'c4ca4238a0b923820dcc509a6f75849b', 'sunwm@wolfcode.cn', '25', '\0', '1');
INSERT INTO `employee` VALUES ('18', '李五明', 'c4ca4238a0b923820dcc509a6f75849b', 'liwm@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('19', '李六明', 'c4ca4238a0b923820dcc509a6f75849b', 'lilm@wolfcode.cn', '25', '\0', '4');
