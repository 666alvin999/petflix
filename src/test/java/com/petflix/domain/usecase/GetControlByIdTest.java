package com.petflix.domain.usecase;

import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
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
		Control control = createControl();

		when(this.controlPort.getControlById(0)).thenReturn(control);

		//Act
		Control actualControl = this.getControlById.execute(0);

		//Assert
		Control expectedControl = createControl();

		assertThat(actualControl).isEqualTo(expectedControl);
	}

	private static Control createControl() {
		Member managingMember = new Member(
			new Id(0),
			new FirstName("Citanimal"),
			new LastName("Asso"),
			new MemberCity("Valenciennes"),
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);

		Animal animal = new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new VideoId("id1"),
			managingMember,
			LocalDate.of(2024, 3, 8)
		);

		Adopter adopter = new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);

		Adoption adoption = new Adoption(
			animal,
			adopter,
			LocalDate.of(2024, 2, 29)
		);

		return new Control(
			adoption,
			LocalDate.of(2024, 2, 29)
		);
	}

}