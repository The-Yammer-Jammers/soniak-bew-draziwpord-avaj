CREATE TABLE Project (
	projectId int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	projectValue decimal(11, 2) NOT NULL,
	clientId int NOT NULL,
	techLeadId int NOT NULL,
	salesEmployeeId int NOT NULL,
	completed bool NOT NULL DEFAULT FALSE,
	PRIMARY KEY(projectId),
	FOREIGN KEY (clientId) REFERENCES Client(clientId),
	FOREIGN KEY (techLeadId) REFERENCES DeliveryEmployee(employeeId),
	FOREIGN KEY (salesEmployeeId) REFERENCES SalesEmployee(employeeId)
);