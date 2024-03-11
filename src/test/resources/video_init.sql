DROP TABLE IF EXISTS VIDEO;

CREATE TABLE VIDEO
(
    ID           VARCHAR(255) PRIMARY KEY,
    URL          VARCHAR(512) UNIQUE NOT NULL,
    TITLE        VARCHAR(128)        NOT NULL,
    DESCRIPTION  VARCHAR(1024)       NOT NULL,
    POSTING_DATE VARCHAR(10)         NOT NULL
);

INSERT INTO VIDEO
VALUES ('1', 'https://www.url1.com', 'title1', 'description1', '2024-03-08'),
       ('2', 'https://www.url2.com', 'title2', 'description2', '2024-03-08');