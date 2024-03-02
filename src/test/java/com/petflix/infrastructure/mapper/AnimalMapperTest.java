package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.infrastructure.dto.AnimalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalMapperTest {

	private AnimalMapper animalMapper;

	@BeforeEach
	public void setUp() {
		this.animalMapper = new AnimalMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		//Arrange
		Member member = createMember();
		AnimalDTO animalDTO = createAnimalDTO();

		//Act
		Animal actualAnimal = this.animalMapper.mapToDomain(animalDTO, member);

		//Assert
		Animal expectedAnimal = createAnimal();

		assertThat(actualAnimal).isEqualTo(expectedAnimal);
	}

	@Test
	public void shouldMapDomainToDto() {
		//Arrange
		Animal animal = createAnimal();

		//Act
		AnimalDTO actualAnimalDTO = this.animalMapper.mapToDTO(animal);

		//Assert
		AnimalDTO expectedAnimalDTO = createAnimalDTO();

		assertThat(actualAnimalDTO).isEqualTo(expectedAnimalDTO);
	}

	private static Animal createAnimal() {
		return new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new Url("https://www.url1.com"),
			createMember()
		);
	}

	private static AnimalDTO createAnimalDTO() {
		return new AnimalDTO(
			0,
			"Oslo",
			"chat",
			3,
			"https://www.url1.com",
			0
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