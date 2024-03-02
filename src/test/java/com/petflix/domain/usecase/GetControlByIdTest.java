package com.petflix.domain.usecase;

import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.port.ControlPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetControlByIdTest {

	private GetControlById getControlById;

	@Mock
	private ControlPort controlPort;

	@BeforeEach
	public void setUp() {
		this.getControlById = new GetControlById(controlPort);
	}

	@Test
	public void shouldGetControl() {
		//Arrange
		Control control = this.createControl();

		when(this.controlPort.getControlById(0)).thenReturn(control);

		//Act
		Control actualControl = this.getControlById.execute(0);

		//Assert
		Control expectedControl = this.createControl();

		assertThat(actualControl).isEqualTo(expectedControl);
	}

	private Control createControl() {
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

		Adoption adoption = new Adoption(
			new Id(0),
			adopter,
			animal,
			LocalDate.of(2024, 2, 29)
		);

		return new Control(
			new Id(0),
			adoption,
			LocalDate.of(2024, 2, 29)
		);
	}

}