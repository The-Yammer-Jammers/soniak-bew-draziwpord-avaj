CREATE TABLE Employee (
	employeeId int NOT NULL AUTO_INCREMENT,
	firstName varchar(50),
	lastName varchar(50),
	salary decimal(15, 2),
	bankAccountNumber varchar(34),
	nationalInsuranceNumber varchar(9),
	PRIMARY KEY(employeeId)
);
