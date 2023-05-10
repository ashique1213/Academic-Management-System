/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.5.20-log : Database - ams
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ams` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ams`;

/*Table structure for table `actualplan` */

DROP TABLE IF EXISTS `actualplan`;

CREATE TABLE `actualplan` (
  `a_id` int(50) NOT NULL AUTO_INCREMENT,
  `p_id` int(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hour` bigint(50) DEFAULT NULL,
  `module` bigint(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `actualplan` */

insert  into `actualplan`(`a_id`,`p_id`,`date`,`hour`,`module`,`status`,`description`) values 
(1,1,'2023-05-07',2,2,'FULLY CONVERED','Bhbj'),
(2,1,'2023-05-07',2,2,'FULLY CONVERED','Bhbj'),
(3,1,'2023-05-07',2,2,'FULLY CONVERED','Bhbj'),
(4,1,'2023-05-07',2,2,'FULLY CONVERED','Bhbj'),
(5,1,'2023-05-07',4,2,'PARTIALLY CONVERED','Jhh');

/*Table structure for table `anoucement` */

DROP TABLE IF EXISTS `anoucement`;

CREATE TABLE `anoucement` (
  `an_id` int(5) NOT NULL AUTO_INCREMENT,
  `anouncement` varchar(100) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`an_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `anoucement` */

insert  into `anoucement`(`an_id`,`anouncement`,`date`) values 
(3,'PLEASE SUBMIT SEMINAR REPORT','2023-04-01');

/*Table structure for table `answer` */

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `an_id` int(5) NOT NULL AUTO_INCREMENT,
  `stud_id` int(5) DEFAULT NULL,
  `q_id` int(5) DEFAULT NULL,
  `ans` varchar(25) DEFAULT NULL,
  `mark` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`an_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `answer` */

/*Table structure for table `assignment` */

DROP TABLE IF EXISTS `assignment`;

CREATE TABLE `assignment` (
  `ass_id` int(5) NOT NULL AUTO_INCREMENT,
  `staf_id` int(11) DEFAULT NULL,
  `topic` varchar(25) DEFAULT NULL,
  `description` varchar(25) DEFAULT NULL,
  `last date` varchar(10) DEFAULT NULL,
  `curdate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ass_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `assignment` */

insert  into `assignment`(`ass_id`,`staf_id`,`topic`,`description`,`last date`,`curdate`) values 
(1,3,'ZZZ','xxx','05/11/23','2023-05-09');

/*Table structure for table `assignsub` */

DROP TABLE IF EXISTS `assignsub`;

CREATE TABLE `assignsub` (
  `assign_id` int(5) NOT NULL AUTO_INCREMENT,
  `t_id` int(5) DEFAULT NULL,
  `sub_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`assign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `assignsub` */

insert  into `assignsub`(`assign_id`,`t_id`,`sub_id`) values 
(1,6,6),
(6,3,1),
(9,3,5),
(10,3,2),
(11,2,10),
(12,2,7);

/*Table structure for table `attendence` */

DROP TABLE IF EXISTS `attendence`;

CREATE TABLE `attendence` (
  `att_id` int(5) NOT NULL AUTO_INCREMENT,
  `stud_id` int(5) DEFAULT NULL,
  `sub_id` int(5) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hour` bigint(15) DEFAULT NULL,
  `attendence` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `attendence` */

insert  into `attendence`(`att_id`,`stud_id`,`sub_id`,`date`,`hour`,`attendence`) values 
(1,4,2,'2023-01-01',1,1),
(2,0,2,'2005-07-23',1,0),
(3,4,2,'2005-08-23',1,1),
(4,9,2,'2005-08-23',1,0),
(5,4,2,'2005-08-23',1,1),
(6,9,2,'2005-08-23',1,0),
(7,4,5,'2005-09-23',2,0),
(8,9,5,'2005-09-23',2,1),
(9,4,5,'2005-09-23',5,1),
(10,9,5,'2005-09-23',5,1),
(11,4,5,'2005-11-23',1,1),
(12,4,5,'2005-11-23',1,1);

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(5) NOT NULL AUTO_INCREMENT,
  `from_id` int(5) DEFAULT NULL,
  `to_id` int(5) DEFAULT NULL,
  `message` varchar(25) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`from_id`,`to_id`,`message`,`date`) values 
(3,4,2,'hlo','2023-04-30'),
(4,4,2,'hh','2023-04-30'),
(5,4,3,'hi','2023-04-30'),
(6,3,4,'hi','2023-05-02'),
(7,4,2,'hi','2023-05-06'),
(8,4,3,'hi i','2023-05-06'),
(9,4,3,'','2023-05-06'),
(10,3,4,'ok','2023-05-06'),
(11,3,9,'hi','2023-05-09'),
(12,3,9,'hi','2023-05-09'),
(13,3,9,'hi','2023-05-09'),
(14,3,9,'hi','2023-05-09');

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `exam_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `topic` varchar(25) DEFAULT NULL,
  `time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `exam` */

insert  into `exam`(`exam_id`,`sub_id`,`date`,`topic`,`time`) values 
(6,3,'05/11/23','SERIES 1',NULL),
(7,2,'05/12/23','SERIES 2','15');

/*Table structure for table `fee` */

DROP TABLE IF EXISTS `fee`;

CREATE TABLE `fee` (
  `f_id` int(5) NOT NULL AUTO_INCREMENT,
  `semester` varchar(15) DEFAULT NULL,
  `amount` varchar(15) DEFAULT NULL,
  `lastdate` date DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `title` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `fee` */

insert  into `fee`(`f_id`,`semester`,`amount`,`lastdate`,`description`,`title`) values 
(3,'1','200','2023-03-30','Urgent','Tution fee'),
(4,'1','2000','2023-05-10','Urgent','EXAM FEE');

/*Table structure for table `fee details` */

DROP TABLE IF EXISTS `fee details`;

CREATE TABLE `fee details` (
  `fd_id` int(5) NOT NULL AUTO_INCREMENT,
  `f_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `amount paid` bigint(15) DEFAULT NULL,
  `amount due` bigint(15) DEFAULT NULL,
  `date paid` date DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`fd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `fee details` */

insert  into `fee details`(`fd_id`,`f_id`,`stud_id`,`amount paid`,`amount due`,`date paid`,`status`) values 
(1,1,4,100,900,'2023-03-16','not completed'),
(2,2,4,10000,100,'2023-03-29','complted'),
(3,2,5,10000,200,'2023-03-29','ok'),
(4,0,4,100,0,'2023-04-30','paid'),
(5,0,4,100,0,'2023-04-30','paid'),
(6,2,4,100,0,'2023-04-30','paid'),
(7,3,4,200,0,'2023-05-06','paid'),
(8,4,9,0,2000,'2023-05-08','NO PAID'),
(9,4,4,0,1000,'2023-05-11','Not paid'),
(10,4,4,2000,0,'2023-05-09','paid');

/*Table structure for table `feed_response` */

DROP TABLE IF EXISTS `feed_response`;

CREATE TABLE `feed_response` (
  `res_id` int(5) NOT NULL AUTO_INCREMENT,
  `feed_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `response` varchar(25) DEFAULT NULL,
  `mark` int(11) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `feed_response` */

insert  into `feed_response`(`res_id`,`feed_id`,`stud_id`,`response`,`mark`) values 
(1,1,4,'f',0),
(2,5,4,'v',0),
(3,6,4,'r',0),
(4,3,4,'f',NULL),
(5,7,4,'B',NULL),
(6,8,4,'B',NULL),
(7,9,4,'B',NULL);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feed_id` int(5) NOT NULL AUTO_INCREMENT,
  `feed_questions` varchar(50) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `op1` varchar(100) DEFAULT NULL,
  `op2` varchar(100) DEFAULT NULL,
  `op3` varchar(100) DEFAULT NULL,
  `op4` varchar(100) DEFAULT NULL,
  `ans` varchar(100) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  `ldate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`feed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feed_id`,`feed_questions`,`staff_id`,`op1`,`op2`,`op3`,`op4`,`ans`,`date`,`ldate`) values 
(1,'bnbvnv',2,'q','r','qe','qu','r','2023-04-30',NULL),
(7,'xxxxxxxx',3,'A','B','C','D','hhhh','2023-05-09','05-10-2023'),
(8,'YYYYYY',3,'A','B','C','D','hhhh','2023-05-09','05-10-2023'),
(9,'ZZZZZ',3,'A','B','C','D','hhhh','2023-05-09','05-10-2023');

/*Table structure for table `internal marks` */

DROP TABLE IF EXISTS `internal marks`;

CREATE TABLE `internal marks` (
  `in_id` int(5) NOT NULL AUTO_INCREMENT,
  `stud_id` int(5) DEFAULT NULL,
  `examid` int(25) DEFAULT NULL,
  `mark` bigint(15) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `internal marks` */

insert  into `internal marks`(`in_id`,`stud_id`,`examid`,`mark`,`date`) values 
(1,4,1,55,'2023-04-30'),
(2,4,2,22,'2023-05-05'),
(4,4,3,67,'2023-05-07'),
(8,9,2,30,'2023-05-09'),
(9,9,5,0,'2023-05-09');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`login id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login id`,`username`,`password`,`type`) values 
(1,'admin','admin','admin'),
(2,'vdt','vdt','teacher'),
(3,'gcz','gcz','teacher'),
(4,'ash','ash','student'),
(5,'rbh','rbh','student'),
(6,'qwerty','qwerty','teacher'),
(7,'123456','123456','student'),
(8,'qwert','qqqqq','teacher'),
(9,'123456','123456','student'),
(10,'frz','123456','teacher'),
(11,'fbn','123456','teacher'),
(12,'rbh','123456','student'),
(13,'nih','123456','student');

/*Table structure for table `materials` */

DROP TABLE IF EXISTS `materials`;

CREATE TABLE `materials` (
  `mat_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `topic` varchar(25) DEFAULT NULL,
  `materials` text,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`mat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `materials` */

insert  into `materials`(`mat_id`,`sub_id`,`topic`,`materials`,`date`) values 
(1,NULL,'ssss',NULL,NULL);

/*Table structure for table `proposedplan` */

DROP TABLE IF EXISTS `proposedplan`;

CREATE TABLE `proposedplan` (
  `p_id` int(5) NOT NULL AUTO_INCREMENT,
  `topic` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hour` bigint(50) DEFAULT NULL,
  `module` bigint(50) DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `proposedplan` */

insert  into `proposedplan`(`p_id`,`topic`,`description`,`date`,`hour`,`module`,`subid`) values 
(3,'INDRODUCTION','INDRODUCTION','2023-05-07',1,2,5),
(4,'XXXX','Xxxxxx','2023-05-09',3,2,5),
(5,'Xx','Ccxc','2023-05-09',1,1,2);

/*Table structure for table `questions` */

DROP TABLE IF EXISTS `questions`;

CREATE TABLE `questions` (
  `q_id` int(5) NOT NULL AUTO_INCREMENT,
  `exam_id` int(5) DEFAULT NULL,
  `question` varchar(50) DEFAULT NULL,
  `op1` varchar(25) DEFAULT NULL,
  `op2` varchar(25) DEFAULT NULL,
  `op3` varchar(25) DEFAULT NULL,
  `op4` varchar(25) DEFAULT NULL,
  `ans` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `questions` */

insert  into `questions`(`q_id`,`exam_id`,`question`,`op1`,`op2`,`op3`,`op4`,`ans`) values 
(1,1,'bvbv','a','b','c','d','a'),
(2,1,'efef','b','t','h','k','k'),
(4,5,'dd','de','fd','dd','dd','ff'),
(5,6,'ZZZZZ','A','B','C','D','D'),
(6,6,'Xxxxx','A','B','C','D','A'),
(7,6,'YYYYY','A','B','C','D','B');

/*Table structure for table `result` */

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  `ans` varchar(30) DEFAULT NULL,
  `res` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `result` */

insert  into `result`(`id`,`qid`,`lid`,`ans`,`res`,`date`) values 
(1,1,1,'a',4,'2023-04-30'),
(2,2,1,'k',4,'2023-04-30'),
(3,1,4,'q',0,'2023-05-02'),
(4,1,4,'c',0,'2023-05-06'),
(5,2,4,'b',0,'2023-05-06');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `stud_id` int(5) NOT NULL AUTO_INCREMENT,
  `login_id` int(5) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `addmission no` bigint(25) DEFAULT NULL,
  `smester` bigint(25) DEFAULT NULL,
  `gender` varchar(25) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(25) DEFAULT NULL,
  `phone` bigint(25) DEFAULT NULL,
  `jointdate` date DEFAULT NULL,
  `photo` text,
  PRIMARY KEY (`stud_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`stud_id`,`login_id`,`name`,`addmission no`,`smester`,`gender`,`dob`,`address`,`phone`,`jointdate`,`photo`) values 
(1,4,'Ashique',16645,1,'male','2023-04-07','Wayanad',9876678822,'2023-04-26','download.jfif'),
(3,9,'Nihad',16665,2,'male','2023-04-07','korome',9874563211,'2023-04-26','download.jfif'),
(4,12,'Rabeeh',16714,3,'male','1999-07-10','vengara',7418529633,'2024-07-09','download.jfif'),
(5,13,'Nihala',16666,3,'female','1999-05-10','kannur',8945632171,'2024-07-09','download.jfif');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subj_id` int(5) NOT NULL AUTO_INCREMENT,
  `subject name` varchar(25) DEFAULT NULL,
  `semester` bigint(25) DEFAULT NULL,
  `credit` bigint(25) DEFAULT NULL,
  PRIMARY KEY (`subj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `subject` */

insert  into `subject`(`subj_id`,`subject name`,`semester`,`credit`) values 
(2,'DBMS',2,4),
(3,'NETWORK',2,4),
(4,'MATHS',1,4),
(5,'DATA STRUCTURE',1,4),
(6,'DFCA',1,4),
(7,'SOFTWARE',1,4),
(8,'OPERATING SYSTEM',2,4),
(9,'A I',2,4),
(10,'DS ML',3,4),
(11,'DEEP LEARNING',3,3),
(12,'OPERATION RESEARCH',3,4),
(13,'DAA',3,4),
(14,'SEMINAR',4,10),
(15,'PROJECT',4,20);

/*Table structure for table `survey` */

DROP TABLE IF EXISTS `survey`;

CREATE TABLE `survey` (
  `sur_id` int(5) NOT NULL AUTO_INCREMENT,
  `survey questions` varchar(100) DEFAULT NULL,
  `op1` varchar(100) DEFAULT NULL,
  `op2` varchar(100) DEFAULT NULL,
  `op3` varchar(100) DEFAULT NULL,
  `op4` varchar(100) DEFAULT NULL,
  `ans` varchar(100) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `ldate` date DEFAULT NULL,
  PRIMARY KEY (`sur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `survey` */

insert  into `survey`(`sur_id`,`survey questions`,`op1`,`op2`,`op3`,`op4`,`ans`,`date`,`subid`,`ldate`) values 
(6,'Xxxx','A','B','C','D','kkk','2023-05-09',5,'2005-10-23'),
(7,'Yyy','A','B','C','D','kkk','2023-05-09',5,'2005-10-23'),
(9,'Aaaa','A','B','C','D','kkk','2023-05-09',2,'2005-10-23');

/*Table structure for table `survey_response` */

DROP TABLE IF EXISTS `survey_response`;

CREATE TABLE `survey_response` (
  `surres_id` int(5) NOT NULL AUTO_INCREMENT,
  `sur_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `mark` int(5) DEFAULT NULL,
  `response` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`surres_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `survey_response` */

insert  into `survey_response`(`surres_id`,`sur_id`,`stud_id`,`mark`,`response`) values 
(1,1,4,0,'1'),
(2,1,4,1,'1'),
(3,2,4,5,'1'),
(4,1,4,1,'1'),
(5,2,4,1,'5'),
(6,1,4,0,'2'),
(7,2,4,0,'3'),
(8,3,4,1,'kkk'),
(9,1,4,1,'1'),
(10,2,4,0,'2'),
(11,3,4,0,'hh'),
(12,4,4,0,'hh'),
(13,1,4,1,'1'),
(14,2,4,0,'3'),
(15,5,4,NULL,'ff');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `t_id` int(5) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(15) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `qualification` varchar(25) DEFAULT NULL,
  `designation` varchar(25) DEFAULT NULL,
  `gender` varchar(25) DEFAULT NULL,
  `age` bigint(25) DEFAULT NULL,
  `phone` bigint(25) DEFAULT NULL,
  `photo` text,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `teacher` */

insert  into `teacher`(`t_id`,`login_id`,`name`,`qualification`,`designation`,`gender`,`age`,`phone`,`photo`) values 
(1,'2','Vasudevan','MCA','Assistant professor','male',45,7894561233,'download.jfif'),
(2,'3','Geevar','MSC','Assistant Professor','male',46,9874563211,'download.jfif'),
(3,'10','Firoz','MCA','Assistant professor','male',45,9874563210,'download.jfif'),
(4,'11','Febin','MCA','Assistant professor','female',40,7894561323,'download.jfif');

/*Table structure for table `time_table` */

DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `time_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `semester` bigint(10) DEFAULT NULL,
  `day` varchar(20) DEFAULT NULL,
  `hours` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=latin1;

/*Data for the table `time_table` */

insert  into `time_table`(`time_id`,`sub_id`,`semester`,`day`,`hours`) values 
(1,3,2,'Monday',1),
(2,3,2,'Monday',2),
(3,2,2,'Monday',3),
(4,2,2,'Monday',4),
(5,9,2,'Monday',5),
(6,2,2,'Monday',6),
(7,8,2,'Tuesday',1),
(8,3,2,'Tuesday',2),
(9,2,2,'Tuesday',3),
(10,9,2,'Tuesday',4),
(11,2,2,'Tuesday',5),
(12,9,2,'Tuesday',6),
(13,2,2,'Wednesday',1),
(14,8,2,'Wednesday',2),
(15,3,2,'Wednesday',3),
(16,2,2,'Wednesday',4),
(17,2,2,'Wednesday',5),
(18,8,2,'Wednesday',6),
(19,9,2,'Thursday',1),
(20,2,2,'Thursday',2),
(21,8,2,'Thursday',3),
(22,2,2,'Thursday',4),
(23,8,2,'Thursday',5),
(24,3,2,'Thursday',6),
(25,2,2,'Friday',1),
(26,9,2,'Friday',2),
(27,2,2,'Friday',3),
(28,8,2,'Friday',4),
(29,3,2,'Friday',5),
(30,2,2,'Friday',6),
(31,5,1,'Monday',1),
(32,5,1,'Monday',2),
(33,4,1,'Monday',3),
(34,4,1,'Monday',4),
(35,4,1,'Monday',5),
(36,5,1,'Monday',6),
(37,4,1,'Tuesday',1),
(38,6,1,'Tuesday',2),
(39,5,1,'Tuesday',3),
(40,6,1,'Tuesday',4),
(41,5,1,'Tuesday',5),
(42,6,1,'Tuesday',6),
(43,6,1,'Wednesday',1),
(44,6,1,'Wednesday',2),
(45,5,1,'Wednesday',3),
(46,5,1,'Wednesday',4),
(47,4,1,'Wednesday',5),
(48,7,1,'Wednesday',6),
(49,7,1,'Thursday',1),
(50,6,1,'Thursday',2),
(51,7,1,'Thursday',3),
(52,7,1,'Thursday',4),
(53,5,1,'Thursday',5),
(54,4,1,'Thursday',6),
(55,4,1,'Friday',1),
(56,7,1,'Friday',2),
(57,6,1,'Friday',3),
(58,4,1,'Friday',4),
(59,4,1,'Friday',5),
(60,6,1,'Friday',6),
(61,10,3,'Monday',1),
(62,10,3,'Monday',2),
(63,13,3,'Monday',3),
(64,10,3,'Monday',4),
(65,13,3,'Monday',5),
(66,10,3,'Monday',6),
(67,12,3,'Tuesday',1),
(68,11,3,'Tuesday',2),
(69,10,3,'Tuesday',3),
(70,13,3,'Tuesday',4),
(71,10,3,'Tuesday',5),
(72,12,3,'Tuesday',6),
(73,10,3,'Wednesday',1),
(74,12,3,'Wednesday',2),
(75,11,3,'Wednesday',3),
(76,10,3,'Wednesday',4),
(77,12,3,'Wednesday',5),
(78,10,3,'Wednesday',6),
(79,13,3,'Thursday',1),
(80,10,3,'Thursday',2),
(81,12,3,'Thursday',3),
(82,11,3,'Thursday',4),
(83,10,3,'Thursday',5),
(84,11,3,'Thursday',6),
(85,10,3,'Friday',1),
(86,13,3,'Friday',2),
(87,10,3,'Friday',3),
(88,12,3,'Friday',4),
(89,11,3,'Friday',5),
(90,10,3,'Friday',6),
(91,15,4,'Monday',1),
(92,15,4,'Monday',2),
(93,15,4,'Monday',3),
(94,14,4,'Monday',4),
(95,14,4,'Monday',5),
(96,14,4,'Monday',6),
(97,15,4,'Tuesday',1),
(98,15,4,'Tuesday',2),
(99,15,4,'Tuesday',3),
(100,14,4,'Tuesday',4),
(101,14,4,'Tuesday',5),
(102,14,4,'Tuesday',6),
(103,15,4,'Wednesday',1),
(104,15,4,'Wednesday',2),
(105,15,4,'Wednesday',3),
(106,14,4,'Wednesday',4),
(107,14,4,'Wednesday',5),
(108,14,4,'Wednesday',6),
(109,15,4,'Thursday',1),
(110,15,4,'Thursday',2),
(111,15,4,'Thursday',3),
(112,14,4,'Thursday',4),
(113,14,4,'Thursday',5),
(114,14,4,'Thursday',6),
(115,15,4,'Friday',1),
(116,15,4,'Friday',2),
(117,15,4,'Friday',3),
(118,14,4,'Friday',4),
(119,14,4,'Friday',5),
(120,14,4,'Friday',6),
(121,15,4,'Monday',1),
(122,15,4,'Monday',2),
(123,15,4,'Monday',3),
(124,14,4,'Monday',4),
(125,14,4,'Monday',5),
(126,14,4,'Monday',6),
(127,15,4,'Tuesday',1),
(128,15,4,'Tuesday',2),
(129,15,4,'Tuesday',3),
(130,14,4,'Tuesday',4),
(131,14,4,'Tuesday',5),
(132,14,4,'Tuesday',6),
(133,15,4,'Wednesday',1),
(134,15,4,'Wednesday',2),
(135,15,4,'Wednesday',3),
(136,14,4,'Wednesday',4),
(137,14,4,'Wednesday',5),
(138,14,4,'Wednesday',6),
(139,15,4,'Thursday',1),
(140,15,4,'Thursday',2),
(141,15,4,'Thursday',3),
(142,14,4,'Thursday',4),
(143,14,4,'Thursday',5),
(144,14,4,'Thursday',6),
(145,15,4,'Friday',1),
(146,15,4,'Friday',2),
(147,15,4,'Friday',3),
(148,14,4,'Friday',4),
(149,14,4,'Friday',5),
(150,14,4,'Friday',6);

/*Table structure for table `up_assignment` */

DROP TABLE IF EXISTS `up_assignment`;

CREATE TABLE `up_assignment` (
  `up_id` int(5) NOT NULL AUTO_INCREMENT,
  `ass_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `file` varchar(100) DEFAULT NULL,
  `data` varchar(10) DEFAULT NULL,
  `marks` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`up_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `up_assignment` */

insert  into `up_assignment`(`up_id`,`ass_id`,`stud_id`,`file`,`data`,`marks`) values 
(1,2,4,'storage_emulated_0_DCIM_Camera_IMG_20230504_162522.jpg','2023-05-06',0),
(2,5,4,'storage_emulated_0_Download_bb353826c9aa19c73359cef6b48d5da8.jpg','2023-05-08',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
