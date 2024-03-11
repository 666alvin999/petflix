CREATE TABLE VIDEO
(
    ID          VARCHAR(255) PRIMARY KEY,
    TITLE       VARCHAR(128)  NOT NULL,
    DESCRIPTION VARCHAR(1024) NOT NULL,
    UPLOAD_DATE VARCHAR(10)   NOT NULL
);

INSERT INTO VIDEO
VALUES ('id1', 'title1', 'description1', '2024-03-08'),
       ('id2', 'title2', 'description2', '2024-03-08');
