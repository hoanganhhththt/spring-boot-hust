DROP SCHEMA IF EXISTS `project`;

CREATE SCHEMA `project`;

use `project`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `fee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fee_amount` decimal DEFAULT 0,
   `date_time` timestamp,
 --  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(10) not NULL,
  `password` varchar(20) NOT NULL,
  `account_number` varchar(45) DEFAULT NULL,
  `bank_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci default null,
  `is_admin` int default 2,
  `status` int default 1,
  -- `fee_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
  -- KEY `FK_DETAIL_idx` (`fee_id`),
  -- CONSTRAINT `FK_DETAIL` FOREIGN KEY (`fee_id`) 
  -- REFERENCES `fee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci not NULL,
  `date_time` timestamp,
  -- `user_id` int default null,
  `average_amount` decimal default 0,
  `fee_amount` decimal default 0,
  `user_buy` int not null,
  `note` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci default null,
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_transaction` (
	`id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `transaction_id` int NOT NULL,
  
  PRIMARY KEY (`id`,`user_id`,`transaction_id`),
  
  KEY `FK_STUDENT_idx` (`transaction_id`),
  
  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`transaction_id`) 
  REFERENCES `transaction` (`id`) 
  -- ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;