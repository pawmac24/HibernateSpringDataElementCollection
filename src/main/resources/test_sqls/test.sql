DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `salary` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`employee_id`)
);

DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone` (
  `owner_id` bigint(20) NOT NULL,
  `type` varchar(5) NOT NULL,
  `area_code` varchar(3) NOT NULL,
  `phone_number` varchar(8) NOT NULL,
  KEY `k_phone_owner_id` (`owner_id`),
  CONSTRAINT `fk_phone_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `employee` (`employee_id`)
);
