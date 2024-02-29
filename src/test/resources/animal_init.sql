DROP TABLE IF EXISTS ANIMAL;

CREATE TABLE ANIMAL
(
    ID               INT PRIMARY KEY,
    NAME             VARCHAR(30)  NOT NULL,
    TYPE             VARCHAR(20)  NOT NULL,
    AGE              INT          NOT NULL,
    PRESENTATION_URL VARCHAR(512) NOT NULL,
    MANAGING_MEMBER  INT          NOT NULL
);

INSERT INTO ANIMAL
VALUES (0, 'Oslo', 'chat', 3, 'https://www.url1.com', 0),
       (1, 'Uta', 'chat', 1, 'https://www.url1.com', 0),
       (2, 'Maul', 'chien', 4, 'https://www.url2.com', 0);