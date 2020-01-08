/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.28 : Database - booksys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`booksys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `booksys`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `bianhao` varchar(20) DEFAULT NULL COMMENT '编号',
  `create_date_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `danjia` varchar(20) NOT NULL COMMENT '图书单价',
  `image_url` varchar(200) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '图书名称',
  `num` varchar(50) NOT NULL COMMENT '库存',
  `order_no` varchar(50) NOT NULL COMMENT '排序号',
  `press` varchar(20) DEFAULT NULL COMMENT '出版社',
  `update_date_time` varchar(50) DEFAULT NULL,
  `book_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKik6nixtd03m92hoqfr3qw2ogg` (`book_type_id`) USING BTREE,
  CONSTRAINT `FKik6nixtd03m92hoqfr3qw2ogg` FOREIGN KEY (`book_type_id`) REFERENCES `book_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `book` */

insert  into `book`(`id`,`author`,`bianhao`,`create_date_time`,`danjia`,`image_url`,`name`,`num`,`order_no`,`press`,`update_date_time`,`book_type_id`) values (4,'斯巴达','001','2019-09-18 11:31:49','99.9',NULL,'Java入门到精通','100','1','新华出版社','2019-09-18 11:31:49',1),(5,'阿斯达','002','2019-09-18 11:31:49','35',NULL,'童话故事','97','2','新华出版社','2019-09-18 11:31:49',4),(6,'阿斯达','003','2019-09-18 11:31:49','66',NULL,'菜谱','96','3','新华出版社','2019-09-18 11:31:49',6),(7,'吴承恩','004','2019-09-19 15:50:41','89.9',NULL,'西游记','99','4','长江文艺出版社','2019-09-19 15:51:05',4),(8,'施耐庵','005','2019-09-19 15:52:06','89.9',NULL,'水浒传','98','5','人民文学出版社','2019-09-19 15:52:06',4),(9,'曹雪芹','006','2019-09-19 15:53:04','89.9',NULL,'红楼梦','99','6','人民文学出版社','2019-09-19 15:53:04',4),(10,'罗贯中','007','2019-09-19 15:53:49','89.9',NULL,'三国演义','98','7','上海古籍出版社','2019-09-19 15:53:49',4);

/*Table structure for table `book_type` */

DROP TABLE IF EXISTS `book_type`;

CREATE TABLE `book_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `order_no` varchar(40) NOT NULL,
  `update_date_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `book_type` */

insert  into `book_type`(`id`,`create_date_time`,`name`,`order_no`,`update_date_time`) values (1,'2019-09-17 18:07:29','计算机类','1','2019-09-17 18:17:50'),(4,'2019-09-18 08:39:22','文学类','2','2019-09-18 08:39:22'),(5,'2019-09-18 08:39:38','科学类','3','2019-09-18 08:39:38'),(6,'2019-09-18 08:39:59','养生类','4','2019-09-18 08:39:59'),(7,'2019-09-19 15:42:04','哲学类','5','2019-09-19 15:42:04'),(8,'2019-09-19 15:42:40','政治类','6','2019-09-19 15:42:40'),(10,'2019-09-19 15:43:34','军事类','8','2019-09-19 15:43:34'),(11,'2019-09-19 15:43:55','经济学','9','2019-09-19 15:43:55'),(12,'2019-09-19 15:45:24','综合性类','7','2019-09-19 15:45:24');

/*Table structure for table `borrowed_record` */

DROP TABLE IF EXISTS `borrowed_record`;

CREATE TABLE `borrowed_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '读者号',
  `user_trueName` varchar(30) DEFAULT NULL COMMENT '读者真实姓名',
  `bianhao` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `book_name` varchar(20) DEFAULT NULL COMMENT '书名',
  `state` int(1) DEFAULT NULL COMMENT '0未还1已还2已催还',
  `start_time` varchar(30) DEFAULT NULL COMMENT '借阅时间',
  `end_time` varchar(30) DEFAULT NULL COMMENT '归还时间',
  `book_id` int(11) DEFAULT NULL COMMENT '图书的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `borrowed_record` */

insert  into `borrowed_record`(`id`,`username`,`user_trueName`,`bianhao`,`book_name`,`state`,`start_time`,`end_time`,`book_id`) values (1,'swk','孙悟空','001','Java入门到精通',1,'2019-09-17 18:07:29','2019-09-20 11:05:02',4),(3,'admin','常紫阳','002','童话故事',1,'2019-09-19 15:12:27','2019-09-20 11:01:52',5),(6,'admin','常紫阳','003','菜谱',1,'2019-09-19 15:37:46','2019-09-19 17:27:08',6),(7,'swk','孙悟空','007','三国演义',1,'2019-09-19 15:55:24','2019-09-20 11:05:03',10),(8,'swk','孙悟空','006','红楼梦',1,'2019-09-19 15:55:29','2019-09-20 11:05:12',9),(9,'swk','孙悟空','004','西游记',1,'2019-09-19 15:55:31','2019-09-20 11:05:13',7),(10,'admin','常紫阳','007','三国演义',1,'2019-09-19 15:56:38','2019-09-19 17:27:49',10),(11,'admin','常紫阳','001','Java入门到精通',0,'2019-09-19 17:26:51',NULL,4),(12,'admin','常紫阳','007','三国演义',0,'2019-09-19 17:27:53',NULL,10),(13,'admin','常紫阳','002','童话故事',1,'2019-09-20 11:02:32','2019-09-20 11:03:14',5),(14,'swk','孙悟空','002','童话故事',0,'2019-09-20 11:04:44',NULL,5),(15,'swk','孙悟空','003','菜谱',0,'2019-09-20 11:04:47',NULL,6),(16,'swk','孙悟空','005','水浒传',0,'2019-09-20 11:04:50',NULL,8);

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `webName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `config` */

insert  into `config`(`id`,`webName`) values (1,'图书管理系统');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `div_id` varchar(50) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `order_no` varchar(11) NOT NULL,
  `p_id` int(11) DEFAULT NULL,
  `permissions` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '0根节点1叶子节点',
  `type` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `menu` */

insert  into `menu`(`id`,`div_id`,`icon`,`name`,`order_no`,`p_id`,`permissions`,`state`,`type`,`url`) values (1,'basic','','基本管理','1',-1,'基本管理',0,0,''),(2,'book','','信息管理','2',-1,'图书',0,0,''),(4,'user','&#xe770;','用户管理','1',1,'用户管理',1,0,'/houtai/user/manage'),(6,'role','&#xe612;','角色管理','2',1,'角色管理',1,0,'/houtai/role/manage'),(9,'menu','&#xe674;','菜单管理','3',1,'菜单管理',1,0,'/houtai/menu/manage?pId=-1'),(10,'book','&#xe705;','图书管理','1',2,'图书管理',1,0,'/houtai/book/manage'),(11,'type','&#xe705;','图书类型','2',2,'图书类型',1,0,'/houtai/book/type/manage'),(15,'borrow','&#xe60a;','借阅记录','3',2,'借阅记录',1,0,'/houtai/borrowedRecord/manage'),(21,'jieyue','&#xe705;','借阅','3',-1,'借阅',0,0,''),(22,'wyjy','&#xe770;','我要借阅','1',21,'我要借阅',1,0,'/houtai/IWantBorrow/manage'),(23,'jyjl','&#xe770;','我的借阅记录','2',21,'我的借阅记录',1,0,'/houtai/MyBorrowed/manage');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` varchar(20) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `order_no` varchar(30) DEFAULT NULL,
  `update_date_time` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `role` */

insert  into `role`(`id`,`create_date_time`,`name`,`order_no`,`update_date_time`,`remark`) values (1,'2019-09-10 17:05:00','超级管理员','1','2019-11-29 15:57:53','最顶级的管理员'),(6,'2019-09-11 17:05:00','普通管理员','2','2019-09-18 15:50:46','只能操作信息管理'),(7,'2019-09-11 17:36:29','最帅管理员','3','2019-09-18 15:50:52','就是最帅的'),(60,'2019-09-18 15:40:59','读者','4','2019-09-20 11:04:03','可以借书看');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKhayg4ib6v7h1wyeyxhq6xlddq` (`menu_id`) USING BTREE,
  KEY `FK4n8tfik5mix35p0najxw5o8qi` (`role_id`) USING BTREE,
  CONSTRAINT `FK4n8tfik5mix35p0najxw5o8qi` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKhayg4ib6v7h1wyeyxhq6xlddq` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`menu_id`,`role_id`) values (110,2,6),(111,10,6),(112,11,6),(212,21,60),(213,22,60),(214,23,60),(215,1,1),(216,4,1),(217,6,1),(218,9,1),(219,2,1),(220,10,1),(221,11,1),(222,15,1),(223,21,1),(224,22,1),(225,23,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` varchar(50) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `order_no` varchar(11) NOT NULL,
  `pwd` varchar(200) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `true_name` varchar(20) NOT NULL,
  `update_date_time` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKj7usi932axhbnhfmbtf3j6toh` (`role_id`) USING BTREE,
  CONSTRAINT `FKj7usi932axhbnhfmbtf3j6toh` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `user` */

insert  into `user`(`id`,`create_date_time`,`name`,`order_no`,`pwd`,`remark`,`true_name`,`update_date_time`,`role_id`) values (1,'2019-09-10 20:35:30','admin','1','ba61ce8fa1e3725876e6363c76043c8d','666','常紫阳','2019-09-12 17:19:18',1),(58,'2019-09-12 17:40:21','czy','2','ba61ce8fa1e3725876e6363c76043c8d','上山打老虎','czy','2019-09-16 13:17:45',7),(64,'2019-09-12 17:57:17','ws','3','ba61ce8fa1e3725876e6363c76043c8d','打老虎啊','武松','2019-09-12 17:57:46',6),(67,'2019-09-17 11:33:11','swk','4','ba61ce8fa1e3725876e6363c76043c8d','三打白骨精','孙悟空','2019-09-18 16:47:43',60);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
