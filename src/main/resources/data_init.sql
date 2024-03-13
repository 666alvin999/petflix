CREATE TABLE PUBLIC.VIDEO (
                              ID CHARACTER VARYING(255) PRIMARY KEY,
                              TITLE CHARACTER VARYING(128) NOT NULL,
                              DESCRIPTION CHARACTER VARYING(1024) NOT NULL,
                              UPLOAD_DATE CHARACTER VARYING(10) NOT NULL
);

CREATE TABLE PUBLIC.MEMBER (
                               ID INT PRIMARY KEY AUTO_INCREMENT,
                               FIRST_NAME CHARACTER VARYING(30) NOT NULL,
                               LAST_NAME CHARACTER VARYING(30) NOT NULL,
                               CITY CHARACTER VARYING(30) NOT NULL,
                               EMAIL CHARACTER VARYING(255) NOT NULL,
                               PHONE CHARACTER VARYING(20) NOT NULL
);

CREATE TABLE PUBLIC.ANIMAL (
                               ID INT PRIMARY KEY AUTO_INCREMENT,
                               NAME CHARACTER VARYING(30) NOT NULL,
                               TYPE CHARACTER VARYING(20) NOT NULL,
                               AGE INT NOT NULL,
                               PRESENTATION_VIDEO_ID CHARACTER VARYING(255) NOT NULL,
                               MANAGING_MEMBER INT NOT NULL,
                               ARRIVAL_DATE CHARACTER VARYING(10) NOT NULL,
                               FOREIGN KEY (PRESENTATION_VIDEO_ID) REFERENCES VIDEO (ID) ON DELETE SET NULL ON UPDATE CASCADE,
                               FOREIGN KEY (MANAGING_MEMBER) REFERENCES MEMBER (ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE PUBLIC.ADOPTER (
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                FIRST_NAME CHARACTER VARYING(30) NOT NULL,
                                LAST_NAME CHARACTER VARYING(30) NOT NULL,
                                ADDRESS CHARACTER VARYING(128) NOT NULL,
                                MAIL CHARACTER VARYING(255) NOT NULL
);

CREATE TABLE PUBLIC.ADOPTION (
                                 ANIMAL_ID INT PRIMARY KEY,
                                 ADOPTER_ID INT NOT NULL,
                                 ADOPTION_DATE CHARACTER VARYING(10) NOT NULL,
                                 FOREIGN KEY (ANIMAL_ID) REFERENCES ANIMAL (ID) ON DELETE RESTRICT ON UPDATE CASCADE,
                                 FOREIGN KEY (ADOPTER_ID) REFERENCES ADOPTER (ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE PUBLIC.CONTROL (
                                ANIMAL_ID INT PRIMARY KEY,
                                CONTROL_DATE CHARACTER VARYING(10) NOT NULL,
                                FOREIGN KEY (ANIMAL_ID) REFERENCES ADOPTION (ANIMAL_ID) ON DELETE CASCADE ON UPDATE CASCADE
);