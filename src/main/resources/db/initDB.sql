CREATE SCHEMA `notebook` DEFAULT CHARACTER SET utf8 ;


CREATE TABLE person_list (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  PRIMARY KEY (ID));