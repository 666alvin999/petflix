package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.ControlDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ControlMapperTest {

	private ControlMapper controlMapper;

	@BeforeEach
	public void setUp() {
		this.controlMapper = new ControlMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
	    //Arrange
		ControlDTO controlDTO = new ControlDTO(0, 0, "29-02-2024");

	    //Act
		Control actualControl = this.controlMapper.mapToDomain(controlDTO, createAdoption());

	    //Assert
	    Control expectedControl = new Control(
			new Id(0),
		    createAdoption(),
		    LocalDate.of(2024, 2, 29)
	    );

		assertThat(actualControl).isEqualTo(expectedControl);
	}

	@Test
	public void shouldMapDomainToDto() {
		//Arrange
		Control control = new Control(
			new Id(0),
			createAdoption(),
			LocalDate.of(2024, 2, 29)
		);

		//Act
		ControlDTO actualControlDTO = this.controlMapper.mapToDTO(control);

		//Assert
		ControlDTO expectedControlDTO = new ControlDTO(0, 0, "29-02-2024");

		assertThat(actualControlDTO).isEqualTo(expectedControlDTO);
	}

	private Adoption createAdoption() {
		Member managingMember = new Member(
			new Id(0),
			new FirstName("Citanimal"),
			new LastName("Asso"),
			"Valenciennes",
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);

		Animal animal = new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new Url("https://www.url1.com"),
			managingMember
		);

		Adopter adopter = new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);

		return new Adoption(
			new Id(0),
			adopter,
			animal,
			LocalDate.of(2024, 2, 29)
		);
	}

}