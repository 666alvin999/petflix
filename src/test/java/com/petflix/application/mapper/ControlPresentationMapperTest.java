package com.petflix.application.mapper;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.ControlViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlPresentationMapperTest {

	private ControlPresentationMapper controlPresentationMapper;

	@BeforeEach
	public void setUp() {
		this.controlPresentationMapper = new ControlPresentationMapper();
	}

	@Test
	public void shouldMapToControlViewModel() {
	    // Arrange
	    Control control = createControls().get(0);

	    // Act
		ControlViewModel actualControlViewModel = this.controlPresentationMapper.mapToViewModel(control);

	    // Assert
	    ControlViewModel expectedControlViewModel = createControlViewModels().get(0);

		assertThat(actualControlViewModel).isEqualTo(expectedControlViewModel);
	}

	@Test
	public void ShouldMapAllToControlViewModel() {
		// Arrange
		Control control = createControls().get(0);

		// Act
		ControlViewModel actualControlViewModel = this.controlPresentationMapper.mapToViewModel(control);

		// Assert
		ControlViewModel expectedControlViewModel = createControlViewModels().get(0);

		assertThat(actualControlViewModel).isEqualTo(expectedControlViewModel);
	}

	private static List<ControlViewModel> createControlViewModels() {
		AnimalViewModel animal =  new AnimalViewModel(
			"Oslo",
			"chat",
			3,
			"id1",
			"2024-03-08",
			true
		);

		MemberViewModel member = new MemberViewModel(
			"Citanimal",
			"Asso",
			"Valenciennes",
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);

		AdopterViewModel adopter = new AdopterViewModel(
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);

		return List.of(
			new ControlViewModel(
				animal,
				member,
				adopter,
				"2024-02-29",
				"2024-08-29"
			),
			new ControlViewModel(
				animal,
				member,
				adopter,
				"2024-03-14",
				"20224-09-14"
			)
		);
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
			LocalDate.of(2024, 3, 8),
			true
		);

		Adopter adopter = new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);

		Adoption adoption1 = new Adoption(
			animal,
			adopter,
			LocalDate.of(2024, 2, 29)
		);

		Adoption adoption2 = new Adoption(
			animal,
			adopter,
			LocalDate.of(2024, 3, 14)
		);

		return List.of(
			new Control(
				adoption1,
				LocalDate.of(2024, 8, 29)
			),
			new Control(
				adoption2,
				LocalDate.of(2024, 9, 14)
			)
		);
	}

}