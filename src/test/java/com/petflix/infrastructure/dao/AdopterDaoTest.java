package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.AdopterDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static java.nio.file.Files.readAllBytes;
import static org.assertj.core.api.Assertions.assertThat;
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

	@AfterEach
	public void clean() {
		dropTables();
	}

	@Test
	public void shouldReturnAllAdopters() {
	    // Act
		List<AdopterDTO> actualAdopters = this.adopterDao.getAllAdopters();

		// Assert
		List<AdopterDTO> expectedAdopters = createAdopters();

		assertThat(actualAdopters).isEqualTo(expectedAdopters);
	}

	@Test
	public void shouldReturnAdoptersByIds() {
		// Act
		List<AdopterDTO> actualAdopters = this.adopterDao.getAdoptersByIds(Set.of(0, 1));

		// Assert
		List<AdopterDTO> expectedAdopters = createAdopters();

		assertThat(actualAdopters).isEqualTo(expectedAdopters);
	}

	@Test
	public void shouldCreateAdopter() {
	    // Arrange
		AdopterDTO adopter = new AdopterDTO(
			3,
			"test",
			"test",
			"test",
			"test"
		);

	    // Act
		ActionSuccess actualActionSuccess = this.adopterDao.createAdopter(adopter);

	    // Assert
	    ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static List<AdopterDTO> createAdopters() {
		return List.of(
			new AdopterDTO(
				0,
				"Alvin",
				"Hamaide",
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr"
			),
			new AdopterDTO(
				1,
				"Martin",
				"Matin",
				"Valenciennes",
				"martin.matin@mail-ecv.fr"
			)
		);
	}

	@SneakyThrows
	private void initTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/adopter_init.sql"))),
			new HashMap<>()
		);
	}

	@SneakyThrows
	private void dropTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/adopter_clean.sql"))),
			new HashMap<>()
		);
	}

}
