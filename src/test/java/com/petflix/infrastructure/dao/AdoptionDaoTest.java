package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AdoptionDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(BasicDatabaseExtension.class)
class AdoptionDaoTest {

	private AdoptionDao adoptionDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.adoptionDao = new AdoptionDao();
		setField(this.adoptionDao, "jdbcTemplate", this.jdbcTemplate);

		initTables();
	}

	@Test
	public void shouldReturnAllAdoptions() {
		//Act
		List<AdoptionDTO> actualAdoptions = this.adoptionDao.getAllAdoptions();

		//Assert
		List<AdoptionDTO> expectedAdoptions = List.of(
			new AdoptionDTO(0, 0, 0, "29-02-2024"),
			new AdoptionDTO(1, 0, 1, "29-02-2024")
		);

		assertThat(actualAdoptions).isEqualTo(expectedAdoptions);
	}

	@SneakyThrows
	private void initTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/adoption_init.sql"))),
			new HashMap<>()
		);
	}

}