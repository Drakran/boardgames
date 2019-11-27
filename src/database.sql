CREATE TABLE users(
	userID int(11) Primary Key not null AUTO_INCREMENT,
	username VARCHAR(50) not null,
	password VARCHAR(50) not null
	);
	
CREATE TABLE games(
	gameID int(11) Primary Key not null AUTO_INCREMENT,
	gameName VARCHAR(255) not null,
	imgLink VARCHAR(10000) not null
	);

CREATE TABLE owned(
	userID int(11) not null,
	gameID int(11) not null,
	FOREIGN KEY (userID) REFERENCES users(userID),
	FOREIGN KEY (gameID) REFERENCES games(gameID)
	);
	
CREATE TABLE wish(
	userID int(11) not null,
	gameID int(11) not null,
	FOREIGN KEY (userID) REFERENCES users(userID),
	FOREIGN KEY (gameID) REFERENCES games(gameID)
	);
	
CREATE TABLE meetups(
	meetupID int(11) Primary Key not null AUTO_INCREMENT,
	gameID int(11) not null,
	capacity int(11) not null,
	currPlayers int(11) not null,
	location VARCHAR(11) not null,
	meetTime DATETIME not null,
	frequency VARCHAR(11) not null,
	creatorID int(11) not null,
	FOREIGN KEY (creatorID) REFERENCES users(userID),
	FOREIGN KEY (gameID) REFERENCES games(gameID)
	);
	
CREATE TABLE usersMeetup(
	meetupID int(11) not null,
	userID int(11) not null,
	FOREIGN KEY (userID) REFERENCES users(userID),
	FOREIGN KEY (meetupID) REFERENCES meetups(meetupID)
	)