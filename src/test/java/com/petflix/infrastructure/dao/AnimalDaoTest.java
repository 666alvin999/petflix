package com.petflix.infrastructure.dao;

import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;

import static java.nio.file.Files.readAllBytes;
import static org.springframework.test.util.ReflectionTestUtils.setField;

class AnimalDaoTest {

	private AnimalDao animalDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.animalDao = new AnimalDao();
		setField(this.animalDao, "jcdbTemplate", this.jdbcTemplate);

		initTables();
	}

	@SneakyThrows
	private void initTables() {
		jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/animal_init.sql"))),
			new HashMap<>()
		);
	}

}