package com.petflix.infrastructure.dbinit;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;

@Component
public class DbInit {

	private JdbcTemplate jdbcTemplate;

	public DbInit(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PostConstruct
	public void init() throws IOException {
		this.jdbcTemplate.execute(
			"DROP TABLE IF EXISTS CONTROL;\n" +
				"DROP TABLE IF EXISTS ADOPTION;\n" +
				"DROP TABLE IF EXISTS ADOPTER;\n" +
				"DROP TABLE IF EXISTS ANIMAL;\n" +
				"DROP TABLE IF EXISTS MEMBER;\n" +
				"DROP TABLE IF EXISTS VIDEO;\n" +
				"\n" +
				"CREATE TABLE PUBLIC.VIDEO\n" +
				"(\n" +
				"    ID          CHARACTER VARYING(255) PRIMARY KEY,\n" +
				"    TITLE       CHARACTER VARYING(128)  NOT NULL,\n" +
				"    DESCRIPTION CHARACTER VARYING(512) NOT NULL,\n" +
				"    UPLOAD_DATE CHARACTER VARYING(10)   NOT NULL\n" +
				");\n" +
				"\n" +
				"CREATE TABLE PUBLIC.MEMBER\n" +
				"(\n" +
				"    ID         INT PRIMARY KEY AUTO_INCREMENT,\n" +
				"    FIRST_NAME CHARACTER VARYING(30)  NOT NULL,\n" +
				"    LAST_NAME  CHARACTER VARYING(30)  NOT NULL,\n" +
				"    CITY       CHARACTER VARYING(30)  NOT NULL,\n" +
				"    MAIL       CHARACTER VARYING(255) NOT NULL,\n" +
				"    PHONE      CHARACTER VARYING(20)  NOT NULL\n" +
				");\n" +
				"\n" +
				"CREATE TABLE PUBLIC.ANIMAL\n" +
				"(\n" +
				"    ID                    INT PRIMARY KEY AUTO_INCREMENT,\n" +
				"    NAME                  CHARACTER VARYING(30)  NOT NULL,\n" +
				"    TYPE                  CHARACTER VARYING(20)  NOT NULL,\n" +
				"    AGE                   INT                    NOT NULL,\n" +
				"    PRESENTATION_VIDEO_ID CHARACTER VARYING(255) NOT NULL,\n" +
				"    MANAGING_MEMBER       INT                    NOT NULL,\n" +
				"    ARRIVAL_DATE          CHARACTER VARYING(10)  NOT NULL,\n" +
				"    FOREIGN KEY (PRESENTATION_VIDEO_ID) REFERENCES VIDEO (ID) ON DELETE SET NULL ON UPDATE CASCADE,\n" +
				"    FOREIGN KEY (MANAGING_MEMBER) REFERENCES MEMBER (ID) ON DELETE SET NULL ON UPDATE CASCADE\n" +
				");\n" +
				"\n" +
				"CREATE TABLE PUBLIC.ADOPTER\n" +
				"(\n" +
				"    ID         INT PRIMARY KEY AUTO_INCREMENT,\n" +
				"    FIRST_NAME CHARACTER VARYING(30)  NOT NULL,\n" +
				"    LAST_NAME  CHARACTER VARYING(30)  NOT NULL,\n" +
				"    ADDRESS    CHARACTER VARYING(128) NOT NULL,\n" +
				"    MAIL       CHARACTER VARYING(255) NOT NULL\n" +
				");\n" +
				"\n" +
				"CREATE TABLE PUBLIC.ADOPTION\n" +
				"(\n" +
				"    ANIMAL_ID     INT PRIMARY KEY,\n" +
				"    ADOPTER_ID    INT                   NOT NULL,\n" +
				"    ADOPTION_DATE CHARACTER VARYING(10) NOT NULL,\n" +
				"    FOREIGN KEY (ANIMAL_ID) REFERENCES ANIMAL (ID) ON DELETE RESTRICT ON UPDATE CASCADE,\n" +
				"    FOREIGN KEY (ADOPTER_ID) REFERENCES ADOPTER (ID) ON DELETE RESTRICT ON UPDATE CASCADE\n" +
				");\n" +
				"\n" +
				"CREATE TABLE PUBLIC.CONTROL\n" +
				"(\n" +
				"    ANIMAL_ID    INT PRIMARY KEY,\n" +
				"    CONTROL_DATE CHARACTER VARYING(10) NOT NULL,\n" +
				"    FOREIGN KEY (ANIMAL_ID) REFERENCES ADOPTION (ANIMAL_ID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
				");\n" +
				"\n" +
				"INSERT INTO VIDEO\n" +
				"VALUES ('z6EchXyieos', 'Igor et Pikachu, un lapin et un chien amis',\n" +
				"        'necessitatibus sed nisi reformidans arcu adipiscing dolorem luctus partiendo saepe facilis vix non fuisset nobis doming pro contentiones nam parturient consul platonem intellegat nulla maluisset faucibus porta per ad ridiculus eu aptent eirmod hendrerit definiebas felis causae nullam magna sollicitudin mentitum dicta tincidunt diam salutatus hac efficitur natum vituperata posidonium liber alia novum nibh tale vocent sonet ',\n" +
				"        '2024-03-13'),\n" +
				"       ('DHfRfU3XUEo', 'Rencontrez Uta et Oslo notre duo de chats, et Maul leur compagnon canin',\n" +
				"        'necessitatibus sed nisi reformidans arcu adipiscing dolorem luctus partiendo saepe facilis vix non fuisset nobis doming pro contentiones nam parturient consul platonem intellegat nulla maluisset faucibus porta per ad ridiculus eu aptent eirmod hendrerit definiebas felis causae nullam magna sollicitudin mentitum dicta tincidunt diam salutatus hac efficitur natum vituperata posidonium liber alia novum nibh tale vocent sonet ',\n" +
				"        '2024-03-14');\n" +
				"\n" +
				"INSERT INTO MEMBER (FIRST_NAME, LAST_NAME, CITY, MAIL, PHONE)\n" +
				"VALUES ('Alvin', 'Hamaide', 'Valenciennes', 'alvin.hamaide@mail-ecv.fr', '06XXXXXXXX'),\n" +
				"       ('Martin', 'Matin', 'Lille', 'martin.matin@mail-ecv.fr', '06XXXXXXXX');\n" +
				"\n" +
				"INSERT INTO ANIMAL (NAME, TYPE, AGE, PRESENTATION_VIDEO_ID, MANAGING_MEMBER, ARRIVAL_DATE)\n" +
				"VALUES ('Oslo', 'chat', 3, 'DHfRfU3XUEo', 1, '2024-02-22'),\n" +
				"       ('Uta', 'chat', 1, 'DHfRfU3XUEo', 1, '2024-02-23'),\n" +
				"       ('Maul', 'chien', 4, 'DHfRfU3XUEo', 1, '2024-02-24'),\n" +
				"       ('Igor', 'chien', 11, 'z6EchXyieos', 2, '2024-02-25'),\n" +
				"       ('Pikachu', 'lapin', 6, 'z6EchXyieos', 2, '2024-02-26');\n" +
				"\n" +
				"INSERT INTO ADOPTER (FIRST_NAME, LAST_NAME, ADDRESS, MAIL)\n" +
				"VALUES ('Benjamin', 'Tennyson', '21 Rue du Pont, 59000 Lille', 'ben.ten@mail-ecv.fr'),\n" +
				"       ('Peter', 'Parker', '21 Rue de la Toile, 59300 Valenciennes', 'spider.man@mail-ecv.fr'),\n" +
				"       ('Harry', 'Potter', '21 Rue de la Baguette, 59000 Lille', 'harry.potter@mail-ecv.fr');\n" +
				"\n" +
				"INSERT INTO ADOPTION\n" +
				"VALUES (1, 2, '2024-02-27'),\n" +
				"       (2, 2, '2024-02-28'),\n" +
				"       (3, 1, '2024-02-29'),\n" +
				"       (4, 3, '2024-03-01');\n" +
				"\n" +
				"INSERT INTO CONTROL\n" +
				"VALUES (1, '2024-08-27'),\n" +
				"       (2, '2024-08-28'),\n" +
				"       (3, '2024-08-29'),\n" +
				"       (4, '2024-09-01');"
		);
	}

}
