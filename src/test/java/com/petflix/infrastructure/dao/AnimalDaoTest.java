package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
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
import java.util.stream.Stream;

import static java.nio.file.Files.readAllBytes;
import static java.util.Collections.emptyList;
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

	@Test
	public void shouldReturnAnimalsByPresentationUrl() {
		//Act
		List<AnimalDTO> actualAnimals = this.animalDao.getAnimalsByPresentationVideoUrl("https://www.url1.com");

		//Assert
		List<AnimalDTO> expectedAnimals = createAnimals();

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldReturnAnimal() {
		//Act
		List<AnimalDTO> actualAnimals = this.animalDao.getAnimalById(0);

		//Assert
		List<AnimalDTO> expectedAnimals = List.of(createAnimals().get(0));

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldReturnAllTypes() {
		//Act
		List<String> actualTypes = this.animalDao.getAllTypes();

		//Assert
		List<String> expectedTypes = List.of("chat", "chien");

		assertThat(actualTypes).isEqualTo(expectedTypes);
	}

	@ParameterizedTest
	@MethodSource("getTestsData")
	public void shouldGetAnimalByTypeAndMemberCity(String type, String city, List<AnimalDTO> expectedAnimalDTOs) {
		//Act
		List<AnimalDTO> actualAnimalDTOs = this.animalDao.getAnimalsByTypeAndMemberCity(type, city);

		//Assert
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
			new AnimalDTO(0, "Oslo", "chat", 3, "https://www.url1.com", 0),
			new AnimalDTO(1, "Uta", "chat", 1, "https://www.url1.com", 0),
			new AnimalDTO(2, "Maul", "chien", 4, "https://www.url1.com", 0)
		);
	}

	@SneakyThrows
	private void initTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/animal_init.sql"))),
			new HashMap<>()
		);
	}

}