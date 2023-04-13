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
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `actualplan` */

/*Table structure for table `anoucement` */

DROP TABLE IF EXISTS `anoucement`;

CREATE TABLE `anoucement` (
  `an_id` int(5) NOT NULL AUTO_INCREMENT,
  `anouncement` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`an_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `anoucement` */

insert  into `anoucement`(`an_id`,`anouncement`,`date`) values 
(3,'Eid mubarak','2023-04-01');

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
  `last date` date DEFAULT NULL,
  PRIMARY KEY (`ass_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `assignment` */

/*Table structure for table `assignsub` */

DROP TABLE IF EXISTS `assignsub`;

CREATE TABLE `assignsub` (
  `assign_id` int(5) NOT NULL AUTO_INCREMENT,
  `t_id` int(5) DEFAULT NULL,
  `sub_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`assign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `assignsub` */

insert  into `assignsub`(`assign_id`,`t_id`,`sub_id`) values 
(1,6,6),
(2,2,6),
(4,6,2),
(5,2,1),
(6,3,1),
(7,3,3),
(8,2,2);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `attendence` */

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(5) NOT NULL AUTO_INCREMENT,
  `from_id` int(5) DEFAULT NULL,
  `to_id` int(5) DEFAULT NULL,
  `message` varchar(25) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `exam_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `topic` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `exam` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `fee` */

insert  into `fee`(`f_id`,`semester`,`amount`,`lastdate`,`description`,`title`) values 
(2,'1','100','2023-03-22','urgent','tution '),
(3,'2','200','2023-03-30','hi','fee');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `fee details` */

insert  into `fee details`(`fd_id`,`f_id`,`stud_id`,`amount paid`,`amount due`,`date paid`,`status`) values 
(1,1,4,100,900,'2023-03-16','not completed'),
(2,2,4,10000,100,'2023-03-29','complted'),
(3,2,5,10000,200,'2023-03-29','ok');

/*Table structure for table `feed_response` */

DROP TABLE IF EXISTS `feed_response`;

CREATE TABLE `feed_response` (
  `res_id` int(5) NOT NULL AUTO_INCREMENT,
  `feed_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `response` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `feed_response` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feed_id` int(5) NOT NULL AUTO_INCREMENT,
  `feed_questions` varchar(50) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`feed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

/*Table structure for table `internal marks` */

DROP TABLE IF EXISTS `internal marks`;

CREATE TABLE `internal marks` (
  `in_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `exam name` varchar(25) DEFAULT NULL,
  `mark` bigint(15) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `internal marks` */

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`login id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

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
(9,'123456','123456','student');

/*Table structure for table `materials` */

DROP TABLE IF EXISTS `materials`;

CREATE TABLE `materials` (
  `mat_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `topic` varchar(25) DEFAULT NULL,
  `materials` text,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`mat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `materials` */

/*Table structure for table `proposedplan` */

DROP TABLE IF EXISTS `proposedplan`;

CREATE TABLE `proposedplan` (
  `p_id` int(5) NOT NULL AUTO_INCREMENT,
  `no` bigint(50) DEFAULT NULL,
  `topic` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hour` bigint(50) DEFAULT NULL,
  `module` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proposedplan` */

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `questions` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`stud_id`,`login_id`,`name`,`addmission no`,`smester`,`gender`,`dob`,`address`,`phone`,`jointdate`,`photo`) values 
(1,4,'Ashique',123456,1,'male','2000-07-06','Wayanad',9876543211,'2023-03-01','Screenshot_1.png'),
(3,9,'Nihad',16665,2,'male','2023-04-07','korome',9874563211,'2023-04-26','Screenshot_1.png');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subj_id` int(5) NOT NULL AUTO_INCREMENT,
  `subject name` varchar(25) DEFAULT NULL,
  `semester` bigint(25) DEFAULT NULL,
  `credit` bigint(25) DEFAULT NULL,
  PRIMARY KEY (`subj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `subject` */

insert  into `subject`(`subj_id`,`subject name`,`semester`,`credit`) values 
(2,'DBMS',2,4),
(3,'Network',2,4),
(4,'os',1,4),
(5,'OR',1,2);

/*Table structure for table `survey` */

DROP TABLE IF EXISTS `survey`;

CREATE TABLE `survey` (
  `sur_id` int(5) NOT NULL AUTO_INCREMENT,
  `survey questions` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`sur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `survey` */

/*Table structure for table `survey_response` */

DROP TABLE IF EXISTS `survey_response`;

CREATE TABLE `survey_response` (
  `surres_id` int(5) NOT NULL AUTO_INCREMENT,
  `sur_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `sub_id` int(5) DEFAULT NULL,
  `response` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`surres_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `survey_response` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `teacher` */

insert  into `teacher`(`t_id`,`login_id`,`name`,`qualification`,`designation`,`gender`,`age`,`phone`,`photo`) values 
(1,'2','Vasudevan','MCA','Assistant professor','male',45,7894561233,'Screenshot_1.png'),
(2,'3','Geevar','MCA','Assistant Professor','male',46,456789123334,'Screenshot_1.png');

/*Table structure for table `time_table` */

DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `time_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `semester` bigint(10) DEFAULT NULL,
  `day` varchar(20) DEFAULT NULL,
  `hours` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;

/*Data for the table `time_table` */

insert  into `time_table`(`time_id`,`sub_id`,`semester`,`day`,`hours`) values 
(1,2,2,'Monday',1),
(2,3,2,'Monday',2),
(3,2,2,'Monday',3),
(4,2,2,'Monday',4),
(5,2,2,'Monday',5),
(6,2,2,'Monday',6),
(7,2,2,'Tuesday',1),
(8,2,2,'Tuesday',2),
(9,2,2,'Tuesday',3),
(10,2,2,'Tuesday',4),
(11,2,2,'Tuesday',5),
(12,2,2,'Tuesday',6),
(13,2,2,'Wednesday',1),
(14,2,2,'Wednesday',2),
(15,2,2,'Wednesday',3),
(16,2,2,'Wednesday',4),
(17,2,2,'Wednesday',5),
(18,2,2,'Wednesday',6),
(19,2,2,'Thursday',1),
(20,2,2,'Thursday',2),
(21,2,2,'Thursday',3),
(22,2,2,'Thursday',4),
(23,2,2,'Thursday',5),
(24,2,2,'Thursday',6),
(25,2,2,'Friday',1),
(26,2,2,'Friday',2),
(27,2,2,'Friday',3),
(28,2,2,'Friday',4),
(29,2,2,'Friday',5),
(30,2,2,'Friday',6),
(31,5,1,'Monday',1),
(32,5,1,'Monday',2),
(33,4,1,'Monday',3),
(34,4,1,'Monday',4),
(35,4,1,'Monday',5),
(36,4,1,'Monday',6),
(37,5,1,'Tuesday',1),
(38,5,1,'Tuesday',2),
(39,4,1,'Tuesday',3),
(40,4,1,'Tuesday',4),
(41,4,1,'Tuesday',5),
(42,4,1,'Tuesday',6),
(43,4,1,'Wednesday',1),
(44,4,1,'Wednesday',2),
(45,5,1,'Wednesday',3),
(46,4,1,'Wednesday',4),
(47,4,1,'Wednesday',5),
(48,4,1,'Wednesday',6),
(49,4,1,'Thursday',1),
(50,4,1,'Thursday',2),
(51,4,1,'Thursday',3),
(52,4,1,'Thursday',4),
(53,4,1,'Thursday',5),
(54,4,1,'Thursday',6),
(55,4,1,'Friday',1),
(56,4,1,'Friday',2),
(57,4,1,'Friday',3),
(58,4,1,'Friday',4),
(59,4,1,'Friday',5),
(60,4,1,'Friday',6),
(61,6,3,'Monday',1),
(62,6,3,'Monday',2),
(63,6,3,'Monday',3),
(64,6,3,'Monday',4),
(65,6,3,'Monday',5),
(66,6,3,'Monday',6),
(67,6,3,'Tuesday',1),
(68,6,3,'Tuesday',2),
(69,6,3,'Tuesday',3),
(70,6,3,'Tuesday',4),
(71,6,3,'Tuesday',5),
(72,6,3,'Tuesday',6),
(73,6,3,'Wednesday',1),
(74,6,3,'Wednesday',2),
(75,6,3,'Wednesday',3),
(76,6,3,'Wednesday',4),
(77,6,3,'Wednesday',5),
(78,6,3,'Wednesday',6),
(79,6,3,'Thursday',1),
(80,6,3,'Thursday',2),
(81,6,3,'Thursday',3),
(82,6,3,'Thursday',4),
(83,6,3,'Thursday',5),
(84,6,3,'Thursday',6),
(85,6,3,'Friday',1),
(86,6,3,'Friday',2),
(87,6,3,'Friday',3),
(88,6,3,'Friday',4),
(89,6,3,'Friday',5),
(90,6,3,'Friday',6);

/*Table structure for table `up_assignment` */

DROP TABLE IF EXISTS `up_assignment`;

CREATE TABLE `up_assignment` (
  `up_id` int(5) NOT NULL AUTO_INCREMENT,
  `ass_id` int(5) DEFAULT NULL,
  `stud_id` int(5) DEFAULT NULL,
  `date upload` date DEFAULT NULL,
  `data` text,
  `marks` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`up_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `up_assignment` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
