package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.ControlDTO;
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
class ControlDaoTest {

	private ControlDao controlDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.controlDao = new ControlDao();
		setField(this.controlDao, "jdbcTemplate", this.jdbcTemplate);

		initTables();
	}

	@Test
	public void shouldReturnControl() {
		//Act
		List<ControlDTO> actualControlDTO = this.controlDao.getControlById(0);

		//Assert
		ControlDTO expectedControlDTO = new ControlDTO(0, 0, "27-02-2024");

		assertThat(actualControlDTO).isEqualTo(List.of(expectedControlDTO));
	}

	@Test
	public void shouldReturnAllControls() {
		// Act
		List<ControlDTO> actualControlDTOs = this.controlDao.getAllControls();

		//Assert
		List<ControlDTO> expectedControlDTOs = List.of(
			new ControlDTO(0, 0, "27-02-2024"),
			new ControlDTO(1, 1, "08-03-2024")
		);

		assertThat(actualControlDTOs).isEqualTo(expectedControlDTOs);
	}

	@SneakyThrows
	private void initTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/control_init.sql"))),
			new HashMap<>()
		);
	}

}