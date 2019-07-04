CREATE TABLE USER (ID INT PRIMARY KEY auto_increment,
login VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
NAME VARCHAR(255),
address VARCHAR(255),
phoneNumber VARCHAR(255),
score DECIMAL(20,2),
sale INT,
role VARCHAR(255));


CREATE TABLE ORDERS (ID INT PRIMARY KEY auto_increment,
score DECIMAL(20,2),
dateCreate DATE,
dateClose DATE,
phoneNumber VARCHAR(255));

CREATE TABLE FLOWERS (ID INT PRIMARY KEY auto_increment,
name VARCHAR(255),
price DECIMAL(20,2),
number INT);