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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllControlsTest {

	private GetAllControls getAllControls;

	@Mock
	private ControlPort controlPort;

	@BeforeEach
	public void setUp() {
		this.getAllControls = new GetAllControls(controlPort);
	}

	@Test
	public void shouldReturnAllControls() {
		// Arrange
		List<Control> controls = createControls();

		when(this.controlPort.getAllControls()).thenReturn(controls);

		// Act
		List<Control> actualControls = this.getAllControls.execute();

		// Assert
		List<Control> expectedControls = createControls();

		assertThat(actualControls).isEqualTo(expectedControls);
	}

	private static List<Control> createControls() {
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

		return List.of(
			new Control(
				adoption,
				LocalDate.of(2024, 2, 29)
			),
			new Control(
				adoption,
				LocalDate.of(2024, 3, 8)
			)
		);
	}

}