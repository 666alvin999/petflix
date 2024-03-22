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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControlPresentationMapperTest {

	private ControlPresentationMapper controlPresentationMapper;

	@Mock
	private AdopterPresentationMapper adopterPresentationMapper;

	@Mock
	private AnimalPresentationMapper animalPresentationMapper;

	@Mock
	private MemberPresentationMapper memberPresentationMapper;


	@BeforeEach
	public void setUp() {
		this.controlPresentationMapper = new ControlPresentationMapper(this.adopterPresentationMapper, this.animalPresentationMapper, this.memberPresentationMapper);
	}

	@Test
	public void shouldMapToControlViewModel() {
		// Arrange
		Control control = createControls().get(0);

		when(this.animalPresentationMapper.mapToViewModel(createAnimal())).thenReturn(createAnimalViewModel());
		when(this.memberPresentationMapper.mapToViewModel(createMember())).thenReturn(createMemberViewModel());
		when(this.adopterPresentationMapper.mapToViewModel(createAdopter())).thenReturn(createAdopterViewModel());

		// Act
		ControlViewModel actualControlViewModel = this.controlPresentationMapper.mapToViewModel(control);

		// Assert
		ControlViewModel expectedControlViewModel = createControlViewModels().get(0);

		assertThat(actualControlViewModel).isEqualTo(expectedControlViewModel);
	}

	@Test
	public void ShouldMapAllToControlViewModel() {
		// Arrange
		List<Control> controls = createControls();

		when(this.animalPresentationMapper.mapToViewModel(createAnimal())).thenReturn(createAnimalViewModel());
		when(this.memberPresentationMapper.mapToViewModel(createMember())).thenReturn(createMemberViewModel());
		when(this.adopterPresentationMapper.mapToViewModel(createAdopter())).thenReturn(createAdopterViewModel());

		// Act
		List<ControlViewModel> actualControlViewModels = this.controlPresentationMapper.mapAllToViewModels(controls);

		// Assert
		List<ControlViewModel> expectedControlViewModels = createControlViewModels();

		assertThat(actualControlViewModels).isEqualTo(expectedControlViewModels);
	}

	private static List<ControlViewModel> createControlViewModels() {
		AnimalViewModel animal = createAnimalViewModel();
		MemberViewModel member = createMemberViewModel();
		AdopterViewModel adopter = createAdopterViewModel();

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
				"2024-09-14"
			)
		);
	}

	private static AdopterViewModel createAdopterViewModel() {
		return new AdopterViewModel(
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);
	}

	private static AnimalViewModel createAnimalViewModel() {
		return new AnimalViewModel(
			"Oslo",
			"chat",
			3,
			"id1",
			"2024-03-08",
			true
		);
	}

	private static MemberViewModel createMemberViewModel() {
		return new MemberViewModel(
			0,
			"Citanimal",
			"Asso",
			"Valenciennes",
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);
	}

	private static List<Control> createControls() {
		return List.of(
			new Control(
				createAdoption().get(0),
				LocalDate.of(2024, 8, 29)
			),
			new Control(
				createAdoption().get(1),
				LocalDate.of(2024, 9, 14)
			)
		);
	}

	private static List<Adoption> createAdoption() {
		return List.of(
			new Adoption(
				createAnimal(),
				createAdopter(),
				LocalDate.of(2024, 2, 29)
			),
			new Adoption(
				createAnimal(),
				createAdopter(),
				LocalDate.of(2024, 3, 14)
			)
		);
	}

	private static Adopter createAdopter() {
		return new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);
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
			true
		);
	}

	private static Member createMember() {
		return new Member(
			new Id(0),
			new FirstName("Citanimal"),
			new LastName("Asso"),
			new MemberCity("Valenciennes"),
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);
	}

}