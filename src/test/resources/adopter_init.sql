DROP TABLE IF EXISTS ADOPTER;

CREATE TABLE ADOPTER
(
    ID         INT PRIMARY KEY,
    FIRST_NAME VARCHAR(30)  NOT NULL,
    LAST_NAME  VARCHAR(30)  NOT NULL,
    ADDRESS    VARCHAR(128) NOT NULL,
    MAIL       VARCHAR(255) NOT NULL
);

INSERT INTO ADOPTER
VALUES (0, 'Alvin', 'Hamaide', 'Valenciennes', 'alvin.hamaide@mail-ecv.fr');