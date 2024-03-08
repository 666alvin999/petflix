DROP TABLE IF EXISTS ANIMAL;
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

CREATE TABLE ANIMAL
(
    ID                     INT PRIMARY KEY,
    NAME                   VARCHAR(30)  NOT NULL,
    TYPE                   VARCHAR(20)  NOT NULL,
    AGE                    INT          NOT NULL,
    PRESENTATION_VIDEO_URL VARCHAR(512) NOT NULL,
    MANAGING_MEMBER        INT          NOT NULL,
    FOREIGN KEY (MANAGING_MEMBER) REFERENCES MEMBER (ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO MEMBER
VALUES (0, 'Alvin', 'Hamaide', 'Valenciennes', 'alvin.hamaide@mail-ecv.fr', '06XXXXXXXX');

INSERT INTO ANIMAL
VALUES (0, 'Oslo', 'chat', 3, 'https://www.url1.com', 0),
       (1, 'Uta', 'chat', 1, 'https://www.url1.com', 0),
       (2, 'Maul', 'chien', 4, 'https://www.url1.com', 0);