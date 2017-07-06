--INSERT INTO IMAGE (id, name, description, picture ) SELECT 1, 'Kims Play', 'A game were you need three in arrow', BulkColumn FROM Openrowset ( Bulk '/Users/paulhasfjord/Documents/Skole-fag/JAVA/Java-Prosjekter/WebshopDemo/src/main/webapp/images/image2.jpg', Single_Blob) as img;

--CREATE PROCEDURE sp_archive_books @archiveDate DATE , @warehouseCode VARCHAR AS UPDATE T_inventory SET Number_Of_Books_Left -1 WHERE  Archive_Date < @archiveDate AND Warehouse_Code = @warehouseCode;
--UPDATE T_transport SET Warehouse_To_Take_Books_From = @warehouseCode;

--INSERT INTO BOOK(id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, year, quantity, itemType) VALUES (1, 'Harry Potter and the seven wise stones', 199.0, 'Harry Potter and the seven wise stone is a wonderful illustrated edition.', '9788202459772', '2015-10-10 10:00:00', 'true', 350, 1, 'Rowling J.K' , 2015, 1, 'BOOK');
INSERT INTO BOOK(id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, year, quantity, itemType) VALUES (2, 'My story by Jens Stoltenberg', 393.0, 'Jens Stoltenberg the previous prime minister.', '9788205483837', '2016-05-05 08:00:00', 'false', 244, 1, 'Jens Stoltenberg' , 2016, 1, 'BOOK');
INSERT INTO BOOK(id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, year, quantity, itemType) VALUES (3, 'When it get darken', 262.0, 'Meet William Wisting as a young man. Stavern 1983 its getting close to christmas.', '9788205497997', '2016-04-01 10:00:00', 'false', 300, 1, 'Jørn Lier Horst' , 2016, 1, 'BOOK');

INSERT INTO ADDRESS (FK_ADDRESS, street, city, state, zipcode, country) VALUES (1, '189 2 Ave','New York','New York','10003','USA')
INSERT INTO ADDRESS (FK_ADDRESS, street, city, state, zipcode, country) VALUES (2, '901 N Ocean Blvd ','Myrtle Beach','South Carolina','29577','USA')

INSERT INTO CUSTOMER (FK_CUSTOMER, firstName, lastName, email, phone, birth, FK_ADDRESS) VALUES (1, 'Kim', 'Pedersen','kim@yahoo.no','90045870', '1980-11-05', 1);
INSERT INTO CUSTOMER (FK_CUSTOMER, firstName, lastName, email, phone, birth, FK_ADDRESS) VALUES (2, 'Silje', 'Kyrra','silje@yahoo.no','45236585', '1999-1-15', 2);

INSERT INTO LOGIN (FK_LOGIN, username,password) VALUES (1,'kimPedda', 'kimSimDimSum!123#');
INSERT INTO LOGIN (FK_LOGIN, username,password) VALUES (2,'Silkyra', 'SanriKorraDigo!@#1314');

INSERT INTO ACCOUNT (id, FK_CUSTOMER, FK_LOGIN) VALUES (1, 1, 1 );
INSERT INTO ACCOUNT (id, FK_CUSTOMER, FK_LOGIN) VALUES (2, 2, 2 );

INSERT INTO Order_OrderLine (id, item, unitPrice, quantity) VALUES (1, 'Macbook PRO', 10000.0, 1);
INSERT INTO Order_OrderLine (id, item, unitPrice, quantity) VALUES (2, 'Macbook PRO', 10000.0, 1);

INSERT INTO Order_ (id, creationdate, totalAmount, paymentDate, deliveryDate, quantity, MIN_PRICE ) VALUES (1, '2017-01-05 20:00:00', 10000.0, '2017-01-12 10:00:00', '2017-01-14 09:00:00', 1, 1.0 );

