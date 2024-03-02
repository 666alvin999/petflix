package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.AdoptionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class AdoptionMapperTest {

	private AdoptionMapper adoptionMapper;

	@BeforeEach
	public void setUp() {
		this.adoptionMapper = new AdoptionMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		//Arrange
		AdoptionDTO adoptionDTO = new AdoptionDTO(0, 0, 0, "01-03-2024");

		//Act
		Adoption actualAdoption = this.adoptionMapper.mapToDomain(adoptionDTO, createAdopter(), createAnimal());

		//Assert
		Adoption expectedAdoption = new Adoption(
			new Id(0),
			createAdopter(),
			createAnimal(),
			LocalDate.of(2024, 3, 1)
		);

		assertThat(actualAdoption).isEqualTo(expectedAdoption);
	}

	@Test
	public void shouldMapDomainToDto() {
		//Arrange
		Adoption adoption = new Adoption(
			new Id(0),
			createAdopter(),
			createAnimal(),
			LocalDate.of(2024, 3, 1)
		);

		//Act
		AdoptionDTO actualAdoptionDTO = this.adoptionMapper.mapToDTO(adoption);

		//Assert
		AdoptionDTO expectedAdoptionDTO = new AdoptionDTO(0, 0, 0, "01-03-2024");

		assertThat(actualAdoptionDTO).isEqualTo(expectedAdoptionDTO);
	}

	private Adopter createAdopter() {
		return new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);
	}

	private Animal createAnimal() {
		Member expectedMember = new Member(
			new Id(0),
			new FirstName("Citanimal"),
			new LastName("Asso"),
			"Valenciennes",
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);

		return new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new Url("https://www.url1.com"),
			expectedMember
		);
	}

}