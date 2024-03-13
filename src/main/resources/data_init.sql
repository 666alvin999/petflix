DROP TABLE IF EXISTS CONTROL;
DROP TABLE IF EXISTS ADOPTION;
DROP TABLE IF EXISTS ADOPTER;
DROP TABLE IF EXISTS ANIMAL;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS VIDEO;

CREATE TABLE PUBLIC.VIDEO
(
    ID          CHARACTER VARYING(255) PRIMARY KEY,
    TITLE       CHARACTER VARYING(128)  NOT NULL,
    DESCRIPTION CHARACTER VARYING(1024) NOT NULL,
    UPLOAD_DATE CHARACTER VARYING(10)   NOT NULL
);

CREATE TABLE PUBLIC.MEMBER
(
    ID         INT PRIMARY KEY AUTO_INCREMENT,
    FIRST_NAME CHARACTER VARYING(30)  NOT NULL,
    LAST_NAME  CHARACTER VARYING(30)  NOT NULL,
    CITY       CHARACTER VARYING(30)  NOT NULL,
    MAIL       CHARACTER VARYING(255) NOT NULL,
    PHONE      CHARACTER VARYING(20)  NOT NULL
);

CREATE TABLE PUBLIC.ANIMAL
(
    ID                    INT PRIMARY KEY AUTO_INCREMENT,
    NAME                  CHARACTER VARYING(30)  NOT NULL,
    TYPE                  CHARACTER VARYING(20)  NOT NULL,
    AGE                   INT                    NOT NULL,
    PRESENTATION_VIDEO_ID CHARACTER VARYING(255) NOT NULL,
    MANAGING_MEMBER       INT                    NOT NULL,
    ARRIVAL_DATE          CHARACTER VARYING(10)  NOT NULL,
    FOREIGN KEY (PRESENTATION_VIDEO_ID) REFERENCES VIDEO (ID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (MANAGING_MEMBER) REFERENCES MEMBER (ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE PUBLIC.ADOPTER
(
    ID         INT PRIMARY KEY AUTO_INCREMENT,
    FIRST_NAME CHARACTER VARYING(30)  NOT NULL,
    LAST_NAME  CHARACTER VARYING(30)  NOT NULL,
    ADDRESS    CHARACTER VARYING(128) NOT NULL,
    MAIL       CHARACTER VARYING(255) NOT NULL
);

CREATE TABLE PUBLIC.ADOPTION
(
    ANIMAL_ID     INT PRIMARY KEY,
    ADOPTER_ID    INT                   NOT NULL,
    ADOPTION_DATE CHARACTER VARYING(10) NOT NULL,
    FOREIGN KEY (ANIMAL_ID) REFERENCES ANIMAL (ID) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (ADOPTER_ID) REFERENCES ADOPTER (ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE PUBLIC.CONTROL
(
    ANIMAL_ID    INT PRIMARY KEY,
    CONTROL_DATE CHARACTER VARYING(10) NOT NULL,
    FOREIGN KEY (ANIMAL_ID) REFERENCES ADOPTION (ANIMAL_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO VIDEO
VALUES ('z6EchXyieos', 'Igor et Pikachu, un lapin et un chien amis',
        'necessitatibus sed nisi reformidans arcu adipiscing dolorem luctus partiendo saepe facilis vix non fuisset nobis doming pro contentiones nam parturient consul platonem intellegat nulla maluisset faucibus porta per ad ridiculus eu aptent eirmod hendrerit definiebas felis causae nullam magna sollicitudin mentitum dicta tincidunt diam salutatus hac efficitur natum vituperata posidonium liber alia novum nibh tale vocent sonet finibus constituto labores nulla omittantur adolescens scripserit quis errem tristique volutpat detraxit iudicabit intellegat auctor auctor at fringilla vituperata antiopam maiorum pro percipit pericula torquent faucibus parturient iuvaret non ludus tractatos ultrices quidam referrentur diam sea pertinax ridiculus mollis dolorum aliquip signiferumque cursus',
        '2024-03-13'),
       ('DHfRfU3XUEo', 'Rencontrez Uta et Oslo notre duo de chats, et Maul leur compagnon canin',
        'necessitatibus sed nisi reformidans arcu adipiscing dolorem luctus partiendo saepe facilis vix non fuisset nobis doming pro contentiones nam parturient consul platonem intellegat nulla maluisset faucibus porta per ad ridiculus eu aptent eirmod hendrerit definiebas felis causae nullam magna sollicitudin mentitum dicta tincidunt diam salutatus hac efficitur natum vituperata posidonium liber alia novum nibh tale vocent sonet finibus constituto labores nulla omittantur adolescens scripserit quis errem tristique volutpat detraxit iudicabit intellegat auctor auctor at fringilla vituperata antiopam maiorum pro percipit pericula torquent faucibus parturient iuvaret non ludus tractatos ultrices quidam referrentur diam sea pertinax ridiculus mollis dolorum aliquip signiferumque cursus',
        '2024-03-14');

INSERT INTO MEMBER (FIRST_NAME, LAST_NAME, CITY, MAIL, PHONE)
VALUES ('Alvin', 'Hamaide', 'Valenciennes', 'alvin.hamaide@mail-ecv.fr', '06XXXXXXXX'),
       ('Martin', 'Matin', 'Lille', 'martin.matin@mail-ecv.fr', '06XXXXXXXX');

INSERT INTO ANIMAL (NAME, TYPE, AGE, PRESENTATION_VIDEO_ID, MANAGING_MEMBER, ARRIVAL_DATE)
VALUES ('Oslo', 'chat', 3, 'DHfRfU3XUEo', 1, '2024-02-22'),
       ('Uta', 'chat', 1, 'DHfRfU3XUEo', 1, '2024-02-23'),
       ('Maul', 'chien', 4, 'DHfRfU3XUEo', 1, '2024-02-24'),
       ('Igor', 'chien', 11, 'z6EchXyieos', 2, '2024-02-25'),
       ('Pikachu', 'lapin', 6, 'z6EchXyieos', 2, '2024-02-26');

INSERT INTO ADOPTER (FIRST_NAME, LAST_NAME, ADDRESS, MAIL)
VALUES ('Benjamin', 'Tennyson', '21 Rue du Pont, 59000 Lille', 'ben.ten@mail-ecv.fr'),
       ('Peter', 'Parker', '21 Rue de la Toile, 59300 Valenciennes', 'spider.man@mail-ecv.fr'),
       ('Harry', 'Potter', '21 Rue de la Baguette, 59000 Lille', 'harry.potter@mail-ecv.fr');

INSERT INTO ADOPTION
VALUES (1, 2, '2024-02-27'),
       (2, 2, '2024-02-28'),
       (3, 1, '2024-02-29'),
       (4, 3, '2024-03-01');

INSERT INTO CONTROL
VALUES (1, '2024-08-27'),
       (2, '2024-08-28'),
       (3, '2024-08-29'),
       (4, '2024-09-01');