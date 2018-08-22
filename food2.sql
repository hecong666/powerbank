/*
SQLyog Ultimate v8.32 
MySQL - 5.5.36 : Database - bicycle_sharing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bicycle_sharing` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bicycle_sharing`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(64) NOT NULL COMMENT '管理员账户',
  `admin_password` varchar(32) NOT NULL COMMENT '管理员密码',
  `admin_email` varchar(100) NOT NULL COMMENT '管理员邮箱',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

/*Data for the table `admin` */

insert  into `admin`(`admin_id`,`admin_name`,`admin_password`,`admin_email`) values (1,'root','e10adc3949ba59abbe56e057f20f883e','1150555483@qq.com');

/*Table structure for table `admin_message` */

DROP TABLE IF EXISTS `admin_message`;

CREATE TABLE `admin_message` (
  `admin_message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员通知id',
  `admin_message_title` varchar(500) NOT NULL COMMENT '管理员通知标题',
  `admin_message_content` text NOT NULL COMMENT '管理员通知内容',
  `admin_message_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `admin_id` int(11) NOT NULL COMMENT '发布管理员id',
  PRIMARY KEY (`admin_message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员通知表';

/*Data for the table `admin_message` */

insert  into `admin_message`(`admin_message_id`,`admin_message_title`,`admin_message_content`,`admin_message_time`,`admin_id`) values (1,'开会啦','一起来做广播体操吧','2018-08-13 19:38:14',1);

/*Table structure for table `bicycle` */

DROP TABLE IF EXISTS `bicycle`;

CREATE TABLE `bicycle` (
  `bicycle_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单车id',
  `bicycle_current_x` double NOT NULL COMMENT '单车经度',
  `bicycle_current_y` double NOT NULL COMMENT '单车纬度',
  `bicycle_last_time` datetime NOT NULL COMMENT '最后一次归还时间',
  `bicycle_statement` int(11) NOT NULL DEFAULT '1' COMMENT '单车状况',
  PRIMARY KEY (`bicycle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='单车表';

/*Data for the table `bicycle` */

insert  into `bicycle`(`bicycle_id`,`bicycle_current_x`,`bicycle_current_y`,`bicycle_last_time`,`bicycle_statement`) values (1,118.642371,32.036997,'2018-08-13 21:47:00',-1);

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员通知id',
  `pid` int(11) NOT NULL COMMENT '单车id',
  `user_id` int(11) NOT NULL COMMENT '借车人id',
  `borrow_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '借车开始时间',
  `borrow_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '借车结束时间',
  `cost` decimal(9,2) NOT NULL COMMENT '消费金额',
  `remaining` decimal(9,2) NOT NULL COMMENT '消费余额',
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='借车记录表';

/*Data for the table `borrow` */

insert  into `borrow`(`borrow_id`,`pid`,`user_id`,`borrow_start_time`,`borrow_end_time`,`cost`,`remaining`) values (1,1,5,'2018-08-21 22:37:54','2018-08-21 22:43:40','0.00','200.00'),(2,1,11,'2018-08-22 19:08:23','2018-08-22 19:09:45','0.00','0.00'),(3,1,11,'2018-08-22 20:07:17','2018-08-22 20:07:26','1.00','399.00');

/*Table structure for table `power_bank` */

DROP TABLE IF EXISTS `power_bank`;

CREATE TABLE `power_bank` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `last_time` datetime NOT NULL,
  `statement` int(10) NOT NULL,
  `dump_energy` int(100) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `power_bank` */

insert  into `power_bank`(`pid`,`last_time`,`statement`,`dump_energy`) values (1,'1000-01-01 00:00:00',-1,100);

/*Table structure for table `recharge` */

DROP TABLE IF EXISTS `recharge`;

CREATE TABLE `recharge` (
  `recharge_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员通知id',
  `user_id` int(11) NOT NULL COMMENT '充值人id',
  `recharge_amount` decimal(9,2) NOT NULL COMMENT '充值金额',
  `remaining` decimal(9,2) NOT NULL COMMENT '余额',
  `recharge_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '充值时间',
  PRIMARY KEY (`recharge_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='充值记录表';

/*Data for the table `recharge` */

insert  into `recharge`(`recharge_id`,`user_id`,`recharge_amount`,`remaining`,`recharge_time`) values (1,11,'50.00','1148.00','2018-08-22 20:13:00');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(64) NOT NULL COMMENT '用户手机号',
  `user_account` decimal(9,2) DEFAULT '0.00' COMMENT '余额',
  `user_credit` int(11) DEFAULT '80' COMMENT '信用度',
  `user_cash` int(11) DEFAULT '0' COMMENT '押金',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_account`,`user_credit`,`user_cash`) values (1,'11','0.00',80,0),(5,'18875143459','200.00',0,0),(7,'18225392075','0.00',0,0),(8,'18225392075','0.00',0,0),(10,'15320292473','0.00',0,0),(11,'18225392074','1148.00',0,0);

/*Table structure for table `user_feedback` */

DROP TABLE IF EXISTS `user_feedback`;

CREATE TABLE `user_feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '反馈id',
  `feedback_title` varchar(64) NOT NULL COMMENT '反馈标题',
  `feedback_content` varchar(64) NOT NULL COMMENT '反馈内容',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `bicycle_id` int(11) NOT NULL COMMENT '车辆id',
  `feedback_time` datetime NOT NULL COMMENT '反馈时间',
  `feedback_statement` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈表';

/*Data for the table `user_feedback` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
