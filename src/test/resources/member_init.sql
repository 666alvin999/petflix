DROP TABLE IF EXISTS MEMBER;

CREATE TABLE MEMBER
(
    ID         INT PRIMARY KEY,
    FIRST_NAME VARCHAR(30)  NOT NULL,
    LAST_NAME  VARCHAR(30)  NOT NULL,
    CITY       VARCHAR(30)  NOT NULL,
    EMAIL      VARCHAR(255) NOT NULL,
    PHONE      VARCHAR(20)  NOT NULL
);

INSERT INTO MEMBER
VALUES (0, 'Alvin', 'Hamaide', 'Valenciennes', 'alvin.hamaide@mail-ecv.fr', '06XXXXXXXX'),
       (1, 'Citanimal', 'Asso', 'Valenciennes', 'citanimal@gmail.com', '06XXXXXXXX');