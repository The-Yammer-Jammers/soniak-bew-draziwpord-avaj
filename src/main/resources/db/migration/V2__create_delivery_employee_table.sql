CREATE TABLE DeliveryEmployee (
	employeeId int NOT NULL,
	PRIMARY KEY(employeeId),
	FOREIGN KEY (employeeId) REFERENCES Employee(employeeId)
);