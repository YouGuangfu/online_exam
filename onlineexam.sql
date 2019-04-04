/*
Navicat MySQL Data Transfer

Source Server         : you
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : onlineexam

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-04 11:04:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exercise
-- ----------------------------
DROP TABLE IF EXISTS `exercise`;
CREATE TABLE `exercise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chooses` text,
  `content` text NOT NULL,
  `paper_id` bigint(20) NOT NULL DEFAULT '0',
  `position` int(11) NOT NULL,
  `remark` text NOT NULL,
  `score` tinyint(4) NOT NULL DEFAULT '1',
  `subject_id` bigint(20) NOT NULL,
  `title` text NOT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exercise
-- ----------------------------
INSERT INTO `exercise` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('2', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('3', '1', '1', '1', '1', '1', '11', '1', '1', '1');
INSERT INTO `exercise` VALUES ('4', '1', '1', '0', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('5', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('6', '1', '1', '1', '1', '1', '11', '1', '1', '1');
INSERT INTO `exercise` VALUES ('7', '1', '1', '0', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('8', '1', '1', '0', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('9', '1', '1', '0', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('10', '1', '1', '0', '1', '1', '1', '1', '1', '1');
INSERT INTO `exercise` VALUES ('11', '{\"A\":\"q\",\"B\":\"w\",\"C\":\"e\",\"D\":\"r\"}', 't', '8', '1', 'q', '1', '1', 'java', 'single_choose');
INSERT INTO `exercise` VALUES ('12', '{\"A\":\"aa\",\"B\":\"aaa\",\"C\":\"aaaa\",\"D\":\"aaaaa\"}', 'a', '8', '2', 'aaaaaa', '2', '1', 'java', 'single_choose');
INSERT INTO `exercise` VALUES ('13', '{\"A\":\"1\",\"B\":\"2\"}', '', '8', '3', '', '2', '1', '1', 'single_choose');
INSERT INTO `exercise` VALUES ('14', null, '', '8', '4', '', '1', '1', '1', 'short_answer');
INSERT INTO `exercise` VALUES ('15', '{\"A\":\"q\",\"B\":\"b\"}', '', '9', '1', '', '1', '5', 'test', 'single_choose');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_time` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('1', '90', '1', '5', '1');
INSERT INTO `paper` VALUES ('9', '90', 'python', '12', '5');
INSERT INTO `paper` VALUES ('8', '90', 'java', '12', '1');
INSERT INTO `paper` VALUES ('5', '90', 'java', '12', '1');
INSERT INTO `paper` VALUES ('6', '90', 'java', '12', '1');
INSERT INTO `paper` VALUES ('7', '90', 'java', '12', '1');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p1jgir6qcpmqnxt4a8105wsot` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '语文');
INSERT INTO `subject` VALUES ('2', '数学');
INSERT INTO `subject` VALUES ('3', '英语');
INSERT INTO `subject` VALUES ('4', 'java');
INSERT INTO `subject` VALUES ('5', 'python');

-- ----------------------------
-- Table structure for teacher_student
-- ----------------------------
DROP TABLE IF EXISTS `teacher_student`;
CREATE TABLE `teacher_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_student
-- ----------------------------
INSERT INTO `teacher_student` VALUES ('1', '1', '12');
INSERT INTO `teacher_student` VALUES ('2', '6', '12');
INSERT INTO `teacher_student` VALUES ('3', '4', '12');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(40) NOT NULL DEFAULT '/img/default.png',
  `name` varchar(40) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '/img/default.png', 'you', 'c9b34aceb51e698c96802d6fe0836a25', '1');
INSERT INTO `user` VALUES ('2', '/img/default.png', 'tea', '7239ea2b5dc943f61f3c0a0276c20974', '2');
INSERT INTO `user` VALUES ('3', '/img/default.png', 'y', 'e10adc3949ba59abbe56e057f20f883e', '2');
INSERT INTO `user` VALUES ('4', '/img/default.png', 'yyy', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `user` VALUES ('5', '/img/default.png', 'ttt', 'e10adc3949ba59abbe56e057f20f883e', '2');
INSERT INTO `user` VALUES ('6', '/img/default.png', '尤', '7d9a22c8598378bd6bb67584611867f7', '1');
INSERT INTO `user` VALUES ('12', '/img/default.png', 'teacher', 'e10adc3949ba59abbe56e057f20f883e', '2');

-- ----------------------------
-- Table structure for user_exercise
-- ----------------------------
DROP TABLE IF EXISTS `user_exercise`;
CREATE TABLE `user_exercise` (
  `User_id` bigint(20) NOT NULL,
  `exerciseCollection_id` bigint(20) NOT NULL,
  PRIMARY KEY (`User_id`,`exerciseCollection_id`),
  KEY `FK64fh25r5s837fui8vo72ds332` (`exerciseCollection_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_exercise
-- ----------------------------
INSERT INTO `user_exercise` VALUES ('4', '8');

-- ----------------------------
-- Table structure for user_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `user_paper_answer`;
CREATE TABLE `user_paper_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` text,
  `exercise_id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `score` int(11) DEFAULT '0',
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_paper_answer
-- ----------------------------
INSERT INTO `user_paper_answer` VALUES ('1', 'A', '15', '9', '0', '4');
INSERT INTO `user_paper_answer` VALUES ('2', 'A', '15', '9', '1', '4');
INSERT INTO `user_paper_answer` VALUES ('3', null, '11', '8', '0', '4');
INSERT INTO `user_paper_answer` VALUES ('4', null, '12', '8', '0', '4');
INSERT INTO `user_paper_answer` VALUES ('5', 'B', '13', '8', '0', '4');
INSERT INTO `user_paper_answer` VALUES ('6', '2', '14', '8', '0', '4');
INSERT INTO `user_paper_answer` VALUES ('7', null, '11', '8', '1', '4');
INSERT INTO `user_paper_answer` VALUES ('8', null, '12', '8', '2', '4');
INSERT INTO `user_paper_answer` VALUES ('9', 'B', '13', '8', '2', '4');
INSERT INTO `user_paper_answer` VALUES ('10', '2', '14', '8', '1', '4');

-- ----------------------------
-- Table structure for user_paper_score
-- ----------------------------
DROP TABLE IF EXISTS `user_paper_score`;
CREATE TABLE `user_paper_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paper_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_paper_score
-- ----------------------------
INSERT INTO `user_paper_score` VALUES ('1', '9', '1', '4');
INSERT INTO `user_paper_score` VALUES ('2', '8', '6', '4');
