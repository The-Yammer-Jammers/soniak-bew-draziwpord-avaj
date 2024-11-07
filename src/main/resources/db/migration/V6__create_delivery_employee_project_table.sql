CREATE TABLE DeliveryEmployee_Project (
    deliveryEmployeeId INT NOT NULL,
    projectId INT NOT NULL,
    workingOnProject BOOLEAN NOT NULL,
    PRIMARY KEY (deliveryEmployeeId, projectId),
    FOREIGN KEY (deliveryEmployeeId) REFERENCES DeliveryEmployee(employeeId),
    FOREIGN KEY (projectId) REFERENCES Project(projectId)
);