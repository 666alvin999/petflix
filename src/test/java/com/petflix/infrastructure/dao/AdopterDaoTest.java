package com.petflix.infrastructure.dao;

import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;

import static java.nio.file.Files.readAllBytes;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(BasicDatabaseExtension.class)
class AdopterDaoTest {

	private AdopterDao adopterDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.adopterDao = new AdopterDao();
		setField(this.adopterDao, "jdbcTemplate", this.jdbcTemplate);

		initTables();
	}

	@SneakyThrows
	private void initTables() {
		jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/adopter_init.sql"))),
			new HashMap<>()
		);
	}

}