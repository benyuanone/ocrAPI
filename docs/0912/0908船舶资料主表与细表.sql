/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.0.27-community-nt : Database - owboss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`owboss` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `owboss`;

/*Table structure for table `erp_ship` */

DROP TABLE IF EXISTS `erp_ship`;

CREATE TABLE `erp_ship` (
  `OWID` varchar(64) NOT NULL,
  `SHIP_ID` varchar(32) NOT NULL,
  `CHN_SHIP_NAME` varchar(64) NOT NULL,
  `ENG_SHIP_NAME` varchar(64) default NULL,
  `SHIP_TYPE` int(11) NOT NULL,
  `NATIONALITY` varchar(64) default NULL,
  `YEARS` varchar(32) default NULL,
  `CALL_SIGN` varchar(128) default NULL,
  `MMSI` varchar(128) default NULL COMMENT '关联多语言表',
  `IMO` varchar(128) default NULL,
  `LONGNESS` decimal(20,6) default NULL,
  `WIDTH` decimal(20,6) default NULL,
  `DRAFT` decimal(20,6) default NULL,
  `TOTAL_WEIGHT` decimal(20,6) default NULL,
  `NET_WEIGHT` decimal(20,6) default NULL COMMENT '关联多语言表',
  `LOAD_WEIGTH` decimal(20,6) default NULL,
  `CABIN_NUM` int(11) default NULL COMMENT '关联多语言表',
  `SHIP_CAPACITY` decimal(20,6) default NULL COMMENT '关联多语言表,可多选',
  `COMPANY` varchar(128) default NULL COMMENT '关联多语言表',
  `COMPANY_MAN` varchar(64) default NULL,
  `COMPANY_PHONE` varchar(32) default NULL,
  `COMPANY_ADDRESS` varchar(256) default NULL,
  `TAXPAYER_NUMBER` varchar(64) default NULL,
  `OPEN_BANK` varchar(128) default NULL,
  `BANK_ACCOUNT` varchar(128) default NULL,
  `SHIP_CHARGE` varchar(32) default NULL,
  `CHARGE_PHONE` varchar(32) default NULL,
  `SHIP_PROPERTY` int(11) default NULL,
  `SHIP_REQUIRE` varchar(256) default NULL,
  `LIMITS` tinyint(4) default NULL,
  `STATE` int(11) NOT NULL,
  `REMARK` varchar(800) default NULL,
  `CREATETIME` datetime default NULL,
  `CREATOR` varchar(64) default NULL,
  `CREATOR_NAME` varchar(16) default NULL,
  `LASTUPDATE` datetime default NULL,
  `UPDATOR` varchar(64) default NULL,
  `UPDATOR_NAME` varchar(16) default NULL,
  `VER` int(11) default NULL,
  `VERTIME` datetime default NULL,
  `DEPT_ID` int(11) default NULL,
  `DEPT_PATH` varchar(240) default NULL,
  `DELFLG` int(11) NOT NULL,
  PRIMARY KEY  (`OWID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `erp_ship` */

insert  into `erp_ship`(`OWID`,`SHIP_ID`,`CHN_SHIP_NAME`,`ENG_SHIP_NAME`,`SHIP_TYPE`,`NATIONALITY`,`YEARS`,`CALL_SIGN`,`MMSI`,`IMO`,`LONGNESS`,`WIDTH`,`DRAFT`,`TOTAL_WEIGHT`,`NET_WEIGHT`,`LOAD_WEIGTH`,`CABIN_NUM`,`SHIP_CAPACITY`,`COMPANY`,`COMPANY_MAN`,`COMPANY_PHONE`,`COMPANY_ADDRESS`,`TAXPAYER_NUMBER`,`OPEN_BANK`,`BANK_ACCOUNT`,`SHIP_CHARGE`,`CHARGE_PHONE`,`SHIP_PROPERTY`,`SHIP_REQUIRE`,`LIMITS`,`STATE`,`REMARK`,`CREATETIME`,`CREATOR`,`CREATOR_NAME`,`LASTUPDATE`,`UPDATOR`,`UPDATOR_NAME`,`VER`,`VERTIME`,`DEPT_ID`,`DEPT_PATH`,`DELFLG`) values ('1','1','船','ship',20017,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','','',20018,'',NULL,1,'',NULL,NULL,NULL,'2017-09-12 08:57:35','1','超级管理员',2,NULL,NULL,NULL,1),('2','2','船2','ship2',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),('4028b8815e6fd4da015e6fdb952300a3','Ship001','泰坦','',1,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','','',NULL,'',NULL,0,'','2017-09-11 15:33:35','1','超级管理员','2017-09-11 15:36:20','1','超级管理员',2,'2017-09-11 15:33:35',NULL,NULL,0),('4028b8815e6fd4da015e6fe7e0b200a4','Ship0535','阿卡西亚','YC ACACIA',1,'韩国','1992','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','','',NULL,'',NULL,0,'','2017-09-11 15:47:00','1','超级管理员','2017-09-11 16:42:04','1','超级管理员',3,'2017-09-11 15:47:00',NULL,NULL,0),('4028b8815e6fd4da015e70040fa60204','111','111','',1,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','','',NULL,'',NULL,0,'','2017-09-11 16:17:47','1','超级管理员',NULL,NULL,NULL,1,'2017-09-11 16:17:47',NULL,NULL,0),('4028b8815e701db6015e7036c16602a1','Ship0616','爱丽丝','STO IRIS',20017,'韩国','1993','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','','',20018,'',NULL,0,'','2017-09-11 17:13:10','1','超级管理员','2017-09-12 08:57:17','1','超级管理员',2,'2017-09-11 17:13:10',NULL,NULL,0),('4028b8815e738d06015e7395883c0008','SHIP0054','爱丽丝','',20017,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'疯帽子','','','','','','','','',20018,'',NULL,0,'','2017-09-12 08:55:33','1','超级管理员',NULL,NULL,NULL,1,'2017-09-12 08:55:33',NULL,NULL,0),('4028b8815e738d06015e739dbdfb00af','SHIP0056','复兴号','',20017,'','','112','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','','',NULL,'',NULL,0,'','2017-09-12 09:04:31','1','超级管理员',NULL,NULL,NULL,1,'2017-09-12 09:04:31',NULL,NULL,0);

/*Table structure for table `erp_ship_list` */

DROP TABLE IF EXISTS `erp_ship_list`;

CREATE TABLE `erp_ship_list` (
  `OWID` varchar(64) NOT NULL,
  `INDEXNO` int(11) default NULL,
  `ERPSHIP_REF_OWID` varchar(64) default NULL,
  `ERPGOODS_REF_OWID` varchar(64) default NULL,
  `REMARK` varchar(800) default NULL,
  `STATE` int(11) NOT NULL,
  `CREATETIME` datetime default NULL,
  `CREATOR` varchar(64) default NULL,
  `CREATOR_NAME` varchar(16) default NULL,
  `LASTUPDATE` datetime default NULL,
  `UPDATOR` varchar(64) default NULL,
  `UPDATOR_NAME` varchar(16) default NULL,
  `VER` int(11) default NULL,
  `VERTIME` datetime default NULL,
  `DEPT_ID` int(11) default NULL,
  `DEPT_PATH` varchar(240) default NULL,
  `DELFLG` int(11) NOT NULL,
  PRIMARY KEY  (`OWID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `erp_ship_list` */

insert  into `erp_ship_list`(`OWID`,`INDEXNO`,`ERPSHIP_REF_OWID`,`ERPGOODS_REF_OWID`,`REMARK`,`STATE`,`CREATETIME`,`CREATOR`,`CREATOR_NAME`,`LASTUPDATE`,`UPDATOR`,`UPDATOR_NAME`,`VER`,`VERTIME`,`DEPT_ID`,`DEPT_PATH`,`DELFLG`) values ('1',NULL,'1','1',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
