
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `accno` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(45) NOT NULL,
  `pass_code` int NOT NULL,
  `balance` int NOT NULL,
  PRIMARY KEY (`accno`)
)
