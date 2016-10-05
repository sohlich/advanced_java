/**
 * Author:  Frantisek Spacek
 * Created: Oct 3, 2016
 */

CREATE TABLE AUTHOR (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE);

CREATE TABLE POST (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  content VARCHAR(500),
  published boolean not null default false,
  author_id INT NOT NULL,
  FOREIGN KEY(author_id) REFERENCES AUTHOR(ID)
);

CREATE TABLE TAG (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE,
  visible boolean NOT NULL default false
);