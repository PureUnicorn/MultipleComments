/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : commentsystem

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2019-07-26 12:12:26
*/
create database commentSystem;

use commentSystem;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commentfirstlevel
-- ----------------------------
DROP TABLE IF EXISTS `commentfirstlevel`;
CREATE TABLE `commentfirstlevel` (
  `flc_id` int(11) NOT NULL AUTO_INCREMENT,
  `sayingId` int(11) NOT NULL,
  `commenter` varchar(50) NOT NULL,
  `commenterAvatar` varchar(50) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTime` datetime NOT NULL,
  PRIMARY KEY (`flc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentfirstlevel
-- ----------------------------
INSERT INTO `commentfirstlevel` VALUES ('5', '11', 'timelessmemory', '/images/smile.jpg', '一级评论', '2019-07-24 08:32:12');
INSERT INTO `commentfirstlevel` VALUES ('40', '28', 'timelessmemory123', 'images/timelessmemory.jpg', '评论自己', '2019-07-25 09:32:29');
INSERT INTO `commentfirstlevel` VALUES ('44', '30', 'timelessmemory123', 'images/timelessmemory.jpg', 'reply test.', '2019-07-26 09:05:37');
INSERT INTO `commentfirstlevel` VALUES ('45', '11', 'timelessmemory123', 'images/timelessmemory.jpg', 'my comment', '2019-07-26 10:20:33');

-- ----------------------------
-- Table structure for commentsaying
-- ----------------------------
DROP TABLE IF EXISTS `commentsaying`;
CREATE TABLE `commentsaying` (
  `saying_id` int(11) NOT NULL AUTO_INCREMENT,
  `sayingContent` varchar(500) NOT NULL,
  `section_id` int(20) NOT NULL,
  `author` varchar(50) NOT NULL,
  `sayingAvatar` varchar(50) NOT NULL,
  `likes` varchar(1000) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`saying_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentsaying
-- ----------------------------
INSERT INTO `commentsaying` VALUES ('11', 'saying_sayingContent2', '1', 'saying_author2', '/images/mario.jpg', '21', '2019-07-23 10:40:51');
INSERT INTO `commentsaying` VALUES ('30', 'This is a test comment.', '1', 'timelessmemory123', 'images/timelessmemory.jpg', '3', '2019-07-26 09:05:22');

-- ----------------------------
-- Table structure for commentsecondlevel
-- ----------------------------
DROP TABLE IF EXISTS `commentsecondlevel`;
CREATE TABLE `commentsecondlevel` (
  `slc_id` int(11) NOT NULL AUTO_INCREMENT,
  `sayingId` int(11) NOT NULL,
  `flcId` int(11) NOT NULL,
  `replier` varchar(50) NOT NULL,
  `toCommenter` varchar(50) NOT NULL,
  `replyContent` varchar(50) NOT NULL,
  `replyTime` datetime NOT NULL,
  PRIMARY KEY (`slc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentsecondlevel
-- ----------------------------
INSERT INTO `commentsecondlevel` VALUES ('35', '28', '40', 'timelessmemory123', 'timelessmemory123', ' 回复自己', '2019-07-25 09:32:35');
INSERT INTO `commentsecondlevel` VALUES ('36', '28', '40', 'timelessmemory123', 'timelessmemory123', ' 回复自己', '2019-07-25 09:32:46');
INSERT INTO `commentsecondlevel` VALUES ('45', '30', '44', 'timelessmemory123', 'timelessmemory123', ' reply myself.', '2019-07-26 09:05:44');
