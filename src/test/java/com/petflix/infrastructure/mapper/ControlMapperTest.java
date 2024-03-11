package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dto.ControlDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlMapperTest {

	private ControlMapper controlMapper;

	@BeforeEach
	public void setUp() {
		this.controlMapper = new ControlMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		// Arrange
		ControlDTO controlDTO = createControlDTOs().get(0);

		// Act
		Control actualControl = this.controlMapper.mapToDomain(controlDTO, createAdoptions().get(0));

		// Assert
		Control expectedControl = createControls().get(0);

		assertThat(actualControl).isEqualTo(expectedControl);
	}

	@Test
	public void shouldMapAllDtosToDomain() {
		// Arrange
		List<ControlDTO> controlDTOs = createControlDTOs();

		// Act
		List<Control> actualControl = this.controlMapper.mapAllToDomain(controlDTOs, createAdoptions());

		// Assert
		List<Control> expectedControls = createControls();

		assertThat(actualControl).isEqualTo(expectedControls);
	}

	@Test
	public void shouldMapDomainToDto() {
		// Arrange
		Control control = createControls().get(0);

		// Act
		ControlDTO actualControlDTO = this.controlMapper.mapToDTO(control);

		// Assert
		ControlDTO expectedControlDTO = createControlDTOs().get(0);

		assertThat(actualControlDTO).isEqualTo(expectedControlDTO);
	}

	private List<Control> createControls() {
		return List.of(
			new Control(
				createAdoptions().get(0),
				LocalDate.of(2024, 3, 8)
			),
			new Control(
				createAdoptions().get(1),
				LocalDate.of(2024, 3, 8)
			)
		);
	}

	private static List<ControlDTO> createControlDTOs() {
		return List.of(
			new ControlDTO(0, "2024-03-08"),
			new ControlDTO(0, "2024-03-08")
		);
	}

	private List<Adoption> createAdoptions() {
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

		return List.of(
			new Adoption(
				animal,
				adopter,
				LocalDate.of(2024, 2, 29)
			),
			new Adoption(
				animal,
				adopter,
				LocalDate.of(2024, 2, 29)
			)
		);
	}

}