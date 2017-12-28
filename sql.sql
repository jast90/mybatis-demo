CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `population` decimal(10,0) DEFAULT NULL,
  `proportion` decimal(10,0) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `area_code` varchar(20) DEFAULT NULL,
  `zip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8