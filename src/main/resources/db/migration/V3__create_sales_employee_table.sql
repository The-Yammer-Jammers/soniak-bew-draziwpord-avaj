CREATE TABLE SalesEmployee (
	employeeId int NOT NULL,
	commissionRate decimal(4,3) NOT NULL,
	CHECK (commissionRate<=1),
	PRIMARY KEY (employeeId),
	FOREIGN KEY (employeeId) REFERENCES Employee(employeeId)
);