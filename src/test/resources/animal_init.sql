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
    ID                    INT PRIMARY KEY,
    NAME                  VARCHAR(30)  NOT NULL,
    TYPE                  VARCHAR(20)  NOT NULL,
    AGE                   INT          NOT NULL,
    PRESENTATION_VIDEO_ID VARCHAR(255) NOT NULL,
    MANAGING_MEMBER       INT          NOT NULL,
    ARRIVAL_DATE          VARCHAR(10)  NOT NULL,
    FOREIGN KEY (MANAGING_MEMBER) REFERENCES MEMBER (ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO MEMBER
VALUES (0, 'Alvin', 'Hamaide', 'Valenciennes', 'alvin.hamaide@mail-ecv.fr', '06XXXXXXXX');

INSERT INTO ANIMAL
VALUES (0, 'Oslo', 'chat', 3, 'id1', 0, '2024-03-08'),
       (1, 'Uta', 'chat', 1, 'id1', 0, '2024-03-08'),
       (2, 'Maul', 'chien', 4, 'id2', 0, '2024-03-08');
