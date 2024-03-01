DROP TABLE IF EXISTS MEMBER;

CREATE TABLE MEMBER (
    ID         INT PRIMARY KEY,
    FIRST_NAME VARCHAR(30)  NOT NULL,
    LAST_NAME  VARCHAR(30)  NOT NULL,
    CITY       VARCHAR(30)  NOT NULL,
    EMAIL      VARCHAR(255) NOT NULL,
    PHONE      VARCHAR(20)  NOT NULL
);

INSERT INTO MEMBER
VALUES (0, 'Alvin', 'Hamaide', 'Lille', 'alvin.hamaide@mail-ecv.fr', '06XXXXXXXX'),
       (1, 'Martin', 'Matin', 'Valenciennes', 'martin.matin@mail-ecv.fr', '06XXXXXXXX');