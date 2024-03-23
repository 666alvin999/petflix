package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dao.AdoptionDao;
import com.petflix.infrastructure.dto.AdoptionDTO;
import com.petflix.infrastructure.mapper.AdoptionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdoptionAdapterTest {

	private AdoptionAdapter adoptionAdapter;

	@Mock
	private AdoptionDao adoptionDao;

	@Mock
	private AnimalAdapter animalAdapter;

	@Mock
	private AdopterAdapter adopterAdapter;

	@Mock
	private AdoptionMapper adoptionMapper;

	@BeforeEach
	public void setUp() {
		this.adoptionAdapter = new AdoptionAdapter(this.adoptionDao, this.animalAdapter, this.adopterAdapter, this.adoptionMapper);
	}

	@Test
	public void shouldReturnAdoptionsByIds() {
		// Arrange
		List<AdoptionDTO> adoptionDTOs = createAdoptionDTOs();
		List<Adopter> adopters = createAdopters();
		List<Animal> animals = createAnimals();
		List<Adoption> adoptions = createAdoptions();


		when(this.adoptionDao.getAdoptionsByIds(Set.of(0, 1))).thenReturn(adoptionDTOs);
		when(this.adopterAdapter.getAdoptersByIds(Set.of(0, 1))).thenReturn(adopters);
		when(this.animalAdapter.getAnimalsByIds(Set.of(0, 1))).thenReturn(animals);
		when(this.adoptionMapper.mapAllToDomain(adoptionDTOs, adopters, animals)).thenReturn(adoptions);

		// Act
		List<Adoption> actualAdoptions = this.adoptionAdapter.getAdoptionsByIds(Set.of(0, 1));

		// Assert
		List<Adoption> expectedAdoptions = createAdoptions();

		assertThat(actualAdoptions).isEqualTo(expectedAdoptions);
	}

	@Test
	public void shouldCreateAdoption() {
	    // Arrange
		AdoptionDTO adoptionDTO = createAdoptionDTOs().get(0);
		Adoption adoption = createAdoptions().get(0);

		when(this.adoptionMapper.mapToDTO(adoption)).thenReturn(adoptionDTO);
		when(this.adoptionDao.createAdoption(adoptionDTO)).thenReturn(new ActionSuccess(true));

	    // Act
		ActionSuccess actualActionSuccess = this.adoptionAdapter.createAdoption(adoption);

	    // Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static List<Adoption> createAdoptions() {
		return List.of(
			new Adoption(
				createAnimals().get(0),
				createAdopters().get(0),
				LocalDate.of(2024, 3, 3)
			),
			new Adoption(
				createAnimals().get(1),
				createAdopters().get(1),
				LocalDate.of(2024, 3, 3)
			)
		);
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