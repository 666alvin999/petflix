package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dto.AdoptionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdoptionMapperTest {

	private AdoptionMapper adoptionMapper;

	@BeforeEach
	public void setUp() {
		this.adoptionMapper = new AdoptionMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		// Arrange
		AdoptionDTO adoptionDTO = createAdoptionDTOs().get(0);

		// Act
		Adoption actualAdoption = this.adoptionMapper.mapToDomain(adoptionDTO, createAdopters().get(0), createAnimals().get(0));

		// Assert
		Adoption expectedAdoption = createAdoptions().get(0);

		assertThat(actualAdoption).isEqualTo(expectedAdoption);
	}

	@Test
	public void shouldMapAllDtosToDomain() {
		// Arrange
		List<AdoptionDTO> adoptionDTOs = createAdoptionDTOs();

		// Act
		List<Adoption> actualAdoptions = this.adoptionMapper.mapAllToDomain(adoptionDTOs, createAdopters(), createAnimals());

		// Assert
		List<Adoption> expectedAdoptions = createAdoptions();

		assertThat(actualAdoptions).isEqualTo(expectedAdoptions);
	}

	@Test
	public void shouldMapDomainToDto() {
		// Arrange
		Adoption adoption = createAdoptions().get(0);

		// Act
		AdoptionDTO actualAdoptionDTO = this.adoptionMapper.mapToDTO(adoption);

		// Assert
		AdoptionDTO expectedAdoptionDTO = createAdoptionDTOs().get(0);

		assertThat(actualAdoptionDTO).isEqualTo(expectedAdoptionDTO);
	}

	private static List<Adoption> createAdoptions() {
		return List.of(
			new Adoption(
				createAnimals().get(0),
				createAdopters().get(0),
				LocalDate.of(2024, 3, 8)
			),
			new Adoption(
				createAnimals().get(1),
				createAdopters().get(1),
				LocalDate.of(2024, 3, 8)
			));
	}

	private static List<AdoptionDTO> createAdoptionDTOs() {
		return List.of(
			new AdoptionDTO(0, 0, "2024-03-08"),
			new AdoptionDTO(1, 1, "2024-03-08")
		);
	}

	private static List<Adopter> createAdopters() {
		return List.of(
			new Adopter(
				new Id(0),
				new FirstName("Alvin"),
				new LastName("Hamaide"),
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr"
			),
			new Adopter(
				new Id(1),
				new FirstName("Martin"),
				new LastName("Matin"),
				"Valenciennes",
				"martin.matin@mail-ecv.fr"
			)
		);
	}

	private static List<Animal> createAnimals() {
		Member member = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			new MemberCity("Valenciennes"),
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		return List.of(
			new Animal(
				new Id(0),
				"Oslo",
				new AnimalType("chat"),
				3,
				new VideoId("id1"),
				member,
				LocalDate.of(2024, 3, 8),
				false
			),
			new Animal(
				new Id(1),
				"Uta",
				new AnimalType("chat"),
				1,
				new VideoId("id1"),
				member,
				LocalDate.of(2024, 3, 8),
				false
			)
		);
	}

}