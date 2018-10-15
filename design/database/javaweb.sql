SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS user;




/* Create Tables */

CREATE TABLE user
(
	user_id int(11) NOT NULL AUTO_INCREMENT,
	email varchar(256),
	password char(64),
	PRIMARY KEY (user_id),
	UNIQUE (email)
);



