package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.AnimalTypesByPresentationVideoIdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class AnimalMapperTest {

	private AnimalMapper animalMapper;

	@BeforeEach
	public void setUp() {
		this.animalMapper = new AnimalMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		// Arrange
		Member member = createMember();
		AnimalDTO animalDTO = createAnimalDTO();

		// Act
		Animal actualAnimal = this.animalMapper.mapToDomain(animalDTO, member, emptyList());

		// Assert
		Animal expectedAnimal = createAnimal();

		assertThat(actualAnimal).isEqualTo(expectedAnimal);
	}

	@Test
	public void shouldMapDomainToDto() {
		// Arrange
		Animal animal = createAnimal();

		// Act
		AnimalDTO actualAnimalDTO = this.animalMapper.mapToDTO(animal);

		// Assert
		AnimalDTO expectedAnimalDTO = createAnimalDTO();

		assertThat(actualAnimalDTO).isEqualTo(expectedAnimalDTO);
	}

	@Test
	public void shouldMapAnimalTypeDTOsToDomain() {
	    // Arrange
		List<String> animalTypeDTOs = List.of("chat", "chien");

	    // Act
		List<AnimalType> actualAnimalTypes = this.animalMapper.mapAllToAnimalTypes(animalTypeDTOs);

	    // Assert
		List<AnimalType> expectedAnimalTypes = List.of(new AnimalType("chat"), new AnimalType("chien"));

		assertThat(actualAnimalTypes).isEqualTo(expectedAnimalTypes);
	}

	@Test
	public void mapAnimalTypesAndPresentationVideoIdDtoToMap() {
		List<AnimalTypesByPresentationVideoIdDTO> animalTypesByPresentationVideoIdDTOs = List.of(
			new AnimalTypesByPresentationVideoIdDTO("id1", "chat,chien"),
			new AnimalTypesByPresentationVideoIdDTO("id2", "chat,chien")
		);

		// Act
		Map<VideoId, List<AnimalType>> actualMap = this.animalMapper.mapToAnimalTypesByPresentationVideoIds(animalTypesByPresentationVideoIdDTOs);

		// Assert
		Map<VideoId, List<AnimalType>> expectedMap = Map.of(
			new VideoId("id1"), List.of(new AnimalType("chat"), new AnimalType("chien")),
			new VideoId("id2"), List.of(new AnimalType("chat"), new AnimalType("chien"))
		);

		assertThat(actualMap).isEqualTo(expectedMap);
	}

	private static Animal createAnimal() {
		return new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new VideoId("id1"),
			createMember(),
			LocalDate.of(2024, 3, 8),
			false
		);
	}

	private static AnimalDTO createAnimalDTO() {
		return new AnimalDTO(
			0,
			"Oslo",
			"chat",
			3,
			"id1",
			0,
			"2024-03-08"
		);
	}

	private static Member createMember() {
		return new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			new MemberCity("Valenciennes"),
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);
	}

}