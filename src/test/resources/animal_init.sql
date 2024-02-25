CREATE TABLE ANIMAL (
    ID                 INT AUTO_INCREMENT PRIMARY KEY,
    NAME               VARCHAR(30) NOT NULL,
    TYPE               VARCHAR(20) NOT NULL,
    AGE                INT         NOT NULL,
    PRESENTATION_VIDEO INT         NOT NULL,
    MANAGING_MEMBER    INT         NOT NULL
);