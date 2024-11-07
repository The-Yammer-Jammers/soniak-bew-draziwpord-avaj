CREATE TABLE `Role`(
    RoleID TINYINT NOT NULL,
    Name varchar(64) NOT NULL,
    PRIMARY KEY (RoleID)
);

CREATE TABLE `User` (
    Username varchar(64) NOT NULL,
    Password varbinary(128) NOT NULL,
    Salt varbinary(16) NOT NULL,
    RoleID TINYINT NOT NULL,
    PRIMARY KEY (Username),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);
