INSERT INTO BOOK (id, quantity, itemType, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, year) VALUES (1, 1, 'BOOK', 'Mio min Mio', 100.0, 'Book about two brothers', '8-32138921322', '2016-05-11 23:42:21', 'false', 400, 1, 'J.R.ROWLING', 1979);
INSERT INTO BOOK (id, quantity, itemType, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, year) VALUES (2, 1, 'BOOK', 'Franks dagbok', 10.0, 'About the war and Auchwitch', '13-3213213213', '2016-11-05 20:00:00', 'false', 200, 2, 'JOHN CRUZ', 1946);
INSERT INTO BOOK (id, quantity, itemType, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, year) VALUES (3, 1, 'CD', 'Harry Potter and the Philosophers Stone', 355.0, 'Harry Potter is a seemingly ordinary boy living with the Dursleys.', '9788700631625', '2016-06-26 20:00:00', 'false', 400 , 2,'J.R.ROWLING', 2004);


INSERT INTO BOOK2 (id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, editor, year) VALUES (1, 'Kaninjegerne', 350.0, 'Handler om en kriger', '9788202522933', '2016-05-11 20:00:00', 'false', 390, 1, 'Lars Kepler', 'Gyldendal', 2016);
INSERT INTO BOOK2 (id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, editor, year) VALUES (2, 'Pondus 15 tenner', 199.0, 'Pondus er en alkolisert morsom gammel mann', '9788242953483', '2016-05-10 10:00:00', 'true', 190, 1, 'Forde Øverli', 'Cappelen' , 2016);

INSERT INTO BOOK3 (id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, editor, year, quantity, itemType) VALUES (1, 'Harry Potter and the seven wise stones', 199.0, 'Harry Potter and the seven wise stone is a wonderful illustrated edition.', '9788202459772', '2015-10-10 10:00:00', 'true', 350, 1, 'Rowling J.K' , 'Cappelen', 2015, 1, 'BOOK');
INSERT INTO BOOK3 (id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, editor, year, quantity, itemType) VALUES (2, 'My story by Jens Stoltenberg', 393.0, 'Jens Stoltenberg the previous prime minister.', '9788205483837', '2016-05-05 08:00:00', 'false', 244, 1, 'Jens Stoltenberg' , 'Gyldendal', 2016, 1, 'BOOK');
INSERT INTO BOOK3 (id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, editor, year, quantity, itemType) VALUES (3, 'When it get darken', 262.0, 'Meet William Wisting as a young man. Stavern 1983 its getting close to christmas.', '9788205497997', '2016-04-01 10:00:00', 'false', 300, 1, 'Jørn Lier Horst' , 'Cappelen', 2016, 1, 'BOOK');

INSERT INTO ADDRESS (FK_ADDRESS, street, city, state, zipcode, country) VALUES (1, '189 2 Ave', 'New York', 'New York', '10003', 'USA')
INSERT INTO ADDRESS (FK_ADDRESS, street, city, state, zipcode, country) VALUES (2, '901 N Ocean Blvd ', 'Myrtle Beach', 'South Carolina', '29577', 'USA')

INSERT INTO CUSTOMER (FK_CUSTOMER, firstName, lastName, email, phone, birth, FK_ADDRESS) VALUES (1, 'Kim', 'Pedersen', 'kim@yahoo.no', '90045870', '1980-11-05', 1);
INSERT INTO CUSTOMER (FK_CUSTOMER, firstName, lastName, email, phone, birth, FK_ADDRESS) VALUES (2, 'Silje', 'Kyrra', 'silje@yahoo.no', '45236585', '1999-1-15', 2);

INSERT INTO LOGIN (FK_LOGIN, username,password) VALUES (1, 'kimPedda', 'kimSimDimSum!123#');
INSERT INTO LOGIN (FK_LOGIN, username,password) VALUES (2, 'Silkyra', 'SanriKorraDigo!@#1314');

INSERT INTO ACCOUNT (id, FK_CUSTOMER, FK_LOGIN) VALUES (1, 1, 1 );
INSERT INTO ACCOUNT (id, FK_CUSTOMER, FK_LOGIN) VALUES (2, 2, 2 );

INSERT INTO Order_OrderLine (id, item, unitPrice, quantity) VALUES (1, 'Macbook PRO', 10000.0, 1);
INSERT INTO Order_OrderLine (id, item, unitPrice, quantity) VALUES (2, 'Macbook PRO', 10000.0, 1);

INSERT INTO Order_ (id, creationdate, totalAmount, paymentDate, deliveryDate, quantity, MIN_PRICE ) VALUES (1, '2017-01-05 20:00:00', 10000.0, '2017-01-12 10:00:00', '2017-01-14 09:00:00', 1, 1.0 );
