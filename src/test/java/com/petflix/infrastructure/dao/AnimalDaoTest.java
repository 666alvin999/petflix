package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.AnimalTypesByPresentationVideoIdDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllBytes;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(BasicDatabaseExtension.class)
class AnimalDaoTest {

	private AnimalDao animalDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.animalDao = new AnimalDao();
		setField(this.animalDao, "jdbcTemplate", this.jdbcTemplate);

		initTables();
	}

	@AfterEach
	public void clean() {
		dropTables();
	}

	@Test
	public void shouldReturnAnimalsByPresentationId() {
		// Act
		List<AnimalDTO> actualAnimals = this.animalDao.getAnimalsByPresentationVideoId("id1");

		// Assert
		List<AnimalDTO> expectedAnimals = List.of(createAnimals().get(0), createAnimals().get(1));

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldReturnAnimals() {
		// Act
		List<AnimalDTO> actualAnimals = this.animalDao.getAnimalsByIds(Set.of(0, 1, 2));

		// Assert
		List<AnimalDTO> expectedAnimals = createAnimals();

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldReturnAllTypes() {
		// Act
		List<String> actualTypes = this.animalDao.getAllTypes();

		// Assert
		List<String> expectedTypes = List.of("chat", "chien");

		assertThat(actualTypes).isEqualTo(expectedTypes);
	}

	@Test
	public void shouldReturnMap() {
		// Arrange
		Set<String> videoIds = Set.of("id1", "id2");

		// Act
		List<AnimalTypesByPresentationVideoIdDTO> actualList = this.animalDao.getAnimalTypesGroupByPresentationVideoIds(videoIds);

		// Assert
		List<AnimalTypesByPresentationVideoIdDTO> expectedList = createAnimalTypesByPresentationVideoIdDTO();

		assertThat(actualList).isEqualTo(expectedList);
	}

	@Test
	public void shouldSubmitAllAnimals() {
		// Arrange
		List<AnimalDTO> animalDTOs = createAnimals();

		this.jdbcTemplate.update("DELETE FROM ANIMAL WHERE ID IN (0,1,2);", emptyMap());

		// Act
		ActionSuccess actualActionSuccess = this.animalDao.submitAnimals(animalDTOs);

		// Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static List<AnimalTypesByPresentationVideoIdDTO> createAnimalTypesByPresentationVideoIdDTO() {
		return List.of(
			new AnimalTypesByPresentationVideoIdDTO("id1", "chat"),
			new AnimalTypesByPresentationVideoIdDTO("id2", "chien")
		);
	}

	@ParameterizedTest
	@MethodSource("getTestsData")
	public void shouldGetAnimalByTypeAndMemberCity(String type, String city, List<AnimalDTO> expectedAnimalDTOs) {
		// Act
		List<AnimalDTO> actualAnimalDTOs = this.animalDao.getAnimalsByTypeAndMemberCity(type, city);

		// Assert
		assertThat(actualAnimalDTOs).isEqualTo(expectedAnimalDTOs);
	}

	private static Stream<Arguments> getTestsData() {
		return Stream.of(
			Arguments.of(null, "Valenciennes", createAnimals()),
			Arguments.of("chat", "Valenciennes", List.of(createAnimals().get(0), createAnimals().get(1))),
			Arguments.of("chat", null, List.of(createAnimals().get(0), createAnimals().get(1))),
			Arguments.of("chien", "Valenciennes", List.of(createAnimals().get(2))),
			Arguments.of("lapin", "Valenciennes", emptyList())
		);
	}

	private static List<AnimalDTO> createAnimals() {
		return List.of(
			new AnimalDTO(0, "Oslo", "chat", 3, "id1", 0, "2024-03-08"),
			new AnimalDTO(1, "Uta", "chat", 1, "id1", 0, "2024-03-08"),
			new AnimalDTO(2, "Maul", "chien", 4, "id2", 0, "2024-03-08")
		);
	}

	@SneakyThrows
	private void initTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/animal_init.sql"))),
			new HashMap<>()
		);
	}

	@SneakyThrows
	private void dropTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/animal_clean.sql"))),
			new HashMap<>()
		);
	}

}
