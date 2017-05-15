drop database if exists db;
create database user_db;
use user_db;

create table user_list(
 user_id integer auto_increment
 username char(15),
 password char(8),
 primary key (user_id)
);

CREATE TABLE `tindercompany`.`cadet_company`(
`cadet_id` INT(11) NOT NULL,
`company_id` INT(11) NOT NULL,
PRIMARY KEY (`cadet_id`, `company_id`),
CONSTRAINT `fk_cadet` FOREIGN KEY (`cadet_id`) REFERENCES `tindercompany`.`cadets`(`id`),
CONSTRAINT `fk_company` FOREIGN KEY (`company_id`) REFERENCES `tindercompany`.`companys`(`id`) )
ENGINE=INNODB CHARSET=utf8;