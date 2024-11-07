CREATE TABLE `Role`(
    RoleID TINYINT NOT NULL,
    Name varchar(64) NOT NULL,
    PRIMARY KEY (RoleID)
);

INSERT INTO `Role` VALUES (1, 'HR');
INSERT INTO `Role` VALUES (2, 'Sales');
INSERT INTO `Role` VALUES (3, 'Management');

CREATE TABLE `User` (
    Username varchar(64) NOT NULL,
    Password varchar(64) NOT NULL,
    RoleID TINYINT NOT NULL,
    PRIMARY KEY (Username),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);
