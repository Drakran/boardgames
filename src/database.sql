CREATE TABLE Users(
	userID int(11) Primary Key not null AUTO_INCREMENT,
	username VARCHAR(50) not null,
	password VARCHAR(50) not null,
	latitude decimal(9,6) not null DEFAULT 0,
	longitude decimal(9,6) not null DEFAULT 0
	);
	
INSERT INTO Users(username,password) values ('test','test');