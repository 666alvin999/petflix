CREATE TABLE ADOPTION
(
    ANIMAL_ID     INT PRIMARY KEY,
    ADOPTER_ID    INT         NOT NULL,
    ADOPTION_DATE VARCHAR(10) NOT NULL
);

INSERT INTO ADOPTION
VALUES (0, 0, '2024-03-08'),
       (1, 1, '2024-03-08');
