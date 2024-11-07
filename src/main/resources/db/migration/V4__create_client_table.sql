CREATE TABLE Client (
	clientId int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	phoneNumber varchar(15) NOT NULL,
	addressLine1 varchar(120) NOT NULL,
	addressLine2 varchar(120),
	addressCity varchar(50) NOT NULL,
	addressProvince varchar(50) NOT NULL,
	addressRegion varchar(50) NOT NULL,
	addressCountry varchar(50) NOT NULL,
	addressPostCode varchar(10) NOT NULL,
	PRIMARY KEY(clientId)
);