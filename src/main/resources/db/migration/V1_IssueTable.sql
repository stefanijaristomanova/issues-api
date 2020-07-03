CREATE TABLE issue (
id UUID NOT NULL PRIMARY KEY,
createdBy VARCHAR(250),
modifiedBy VARCHAR(250),
dateCreated DATE,
dateModified DATE,
deleted INT,
name VARCHAR(250) NOT NULL,
description VARCHAR(250),
status VARCHAR(100),
type VARCHAR(100),
version INT,
templateID BIGINT
);