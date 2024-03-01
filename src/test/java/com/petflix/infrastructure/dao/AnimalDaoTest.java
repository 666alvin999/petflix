package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AnimalDTO;
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

	@Test
	public void shouldGetAnimalByTypeAndMemberCity() {
		//Act
		List<AnimalDTO> actualAnimalDTOs = this.animalDao.getAnimalByTypeAndMemberCity("chat", "Valenciennes");

		//Assert
		List<AnimalDTO> animalDTOs = createAnimals();
		List<AnimalDTO> expectedAnimalDTOs = List.of(animalDTOs.get(0), animalDTOs.get(1));

		assertThat(actualAnimalDTOs).isEqualTo(expectedAnimalDTOs);
	}

	private List<AnimalDTO> createAnimals() {
		return List.of(
			new AnimalDTO(0, "Oslo", "chat", 3, "https://www.url1.com", 0),
			new AnimalDTO(1, "Uta", "chat", 1, "https://www.url1.com", 0),
			new AnimalDTO(2, "Maul", "chien", 4, "https://www.url1.com", 0)
		);
	}

	@SneakyThrows
	private void initTables() {
		jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/animal_init.sql"))),
			new HashMap<>()
		);
	}

}