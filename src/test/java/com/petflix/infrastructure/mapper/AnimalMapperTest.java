package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.AnimalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnimalMapperTest {

	private AnimalMapper animalMapper;

	@BeforeEach
	public void setUp() {
		this.animalMapper = new AnimalMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
	    //Arrange
		Member member = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

	    AnimalDTO animalDTO = new AnimalDTO(
			0,
		    "Oslo",
		    "chat",
		    3,
		    "https://www.url1.com",
		    0
	    );

	    //Act
		Animal actualAnimal = this.animalMapper.mapToDomain(animalDTO, member);

	    //Assert
		Member expectedMember = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

	    Animal expectedAnimal = new Animal(
			new Id(0),
		    "Oslo",
		    new AnimalType("chat"),
		    3,
		    new Url("https://www.url1.com"),
		    expectedMember
	    );

		assertThat(actualAnimal).isEqualTo(expectedAnimal);
	}

	@Test
	public void shouldMapDomainToDto() {
		//Arrange
		Member member = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		Animal animal = new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new Url("https://www.url1.com"),
			member
		);

		//Act
		AnimalDTO actualAnimalDTO = this.animalMapper.mapToDTO(animal);

		//Assert
		AnimalDTO expectedAnimalDTO = new AnimalDTO(
			0,
			"Oslo",
			"chat",
			3,
			"https://www.url1.com",
			0
		);

		assertThat(actualAnimalDTO).isEqualTo(expectedAnimalDTO);
	}

}