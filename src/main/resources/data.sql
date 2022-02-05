insert into USER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, STREET, POST_CODE, CITY, AGE, SV_NUMBER, BIRTHDAY, USER_TYPE)
values ('DummyUser', 'DummyLastName', 'ekersabri1050@hotmail.at', 'password', 'Dummystreet', '6370', 'Kitzbühel', 22,
        4667881, '2001-05-20', 'CUSTOMER');

insert into EVENT (DATE, DESCRIPTION, NAME, PRICE, TICKET_COUNTER, TICKET_ID)
VALUES ('2022-01-20', 'Tst Event Description', 'Ellmau Event', 200, 100, 1);

INSERT INTO TICKET (QUANTITY, STATUS, EVENT_ID, USER_ID)
VALUES (100, 1, 1, 1);