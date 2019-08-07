CREATE TABLE CARD (ID BIGINT PRIMARY KEY auto_increment,
order_Id BIGINT,
flower_Id BIGINT,
number INT,
FOREIGN KEY(order_Id) REFERENCES orders(id),
FOREIGN KEY(flower_Id) REFERENCES flowers(id)
);