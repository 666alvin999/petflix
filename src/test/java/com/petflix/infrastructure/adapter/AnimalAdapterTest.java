package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dao.AdoptionDao;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.AnimalTypesByPresentationVideoIdDTO;
import com.petflix.infrastructure.mapper.AnimalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalAdapterTest {

	private AnimalAdapter animalAdapter;

	@Mock
	private AnimalDao animalDao;

	@Mock
	private AdoptionDao adoptionDao;

	@Mock
	private AnimalMapper animalMapper;

	@Mock
	private MemberAdapter memberAdapter;

	@BeforeEach
	public void setUp() {
		this.animalAdapter = new AnimalAdapter(this.animalDao, this.adoptionDao, this.memberAdapter, this.animalMapper);
	}

	@Test
	public void shouldReturnAllTypes() {
		// Arrange
		List<String> animalTypesDTO = createAnimalTypeDTOs();
		List<AnimalType> animalTypes = createAnimalTypes();

		when(this.animalDao.getAllTypes()).thenReturn(animalTypesDTO);
		when(this.animalMapper.mapAllToAnimalTypes(animalTypesDTO)).thenReturn(animalTypes);

		// Act
		List<AnimalType> actualAnimalTypes = this.animalAdapter.getAllTypes();

		// Assert
		List<AnimalType> expectedAnimalTypes = createAnimalTypes();

		assertThat(actualAnimalTypes).isEqualTo(expectedAnimalTypes);
	}

	@Test
	public void shouldReturnAnimalsByIds() {
		// Arrange
		List<AnimalDTO> animalDTOs = createAnimalDTOs();
		Member member = createMember();
		Set<Integer> animalIds = animalDTOs.stream().map(AnimalDTO::getId).collect(toSet());

		when(this.animalDao.getAnimalsByIds(animalIds)).thenReturn(animalDTOs);
		when(this.adoptionDao.getAdoptionsByIds(animalIds)).thenReturn(emptyList());
		when(this.animalMapper.mapAllToDomain(animalDTOs, List.of(member), emptyList())).thenReturn(createAnimals());
		when(this.memberAdapter.getMembersByIds(Set.of(0))).thenReturn(List.of(member));

		// Act
		List<Animal> actualAnimals = this.animalAdapter.getAnimalsByIds(animalIds);

		// Assert
		List<Animal> expectedAnimals = createAnimals();

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldReturnAnimalsByPresentationVideoId() {
		// Arrange
		String videoId = "id1";

		List<Member> members = List.of(createMember());
		List<AnimalDTO> animalDTOs = createAnimalDTOs();
		List<Animal> animals = createAnimals();

		Set<Integer> animalIds = animalDTOs.stream().map(AnimalDTO::getId).collect(toSet());

		when(this.animalDao.getAnimalsByPresentationVideoId(videoId)).thenReturn(animalDTOs);
		when(this.adoptionDao.getAdoptionsByIds(animalIds)).thenReturn(emptyList());
		when(this.memberAdapter.getMembersByIds(Set.of(0))).thenReturn(members);
		when(this.animalMapper.mapAllToDomain(animalDTOs, members, emptyList())).thenReturn(animals);

		// Act
		List<Animal> actualAnimals = this.animalAdapter.getAnimalsByPresentationVideoId(videoId);

		// Assert
		List<Animal> expectedAnimals = createAnimals();

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldGetAnimalTypesByPresentationVideoIds() {
		// Arrange
		Set<String> videoIds = Set.of("id1", "id2");

		List<AnimalTypesByPresentationVideoIdDTO> animalTypesByPresentationVideoIdDTOs = List.of(
			new AnimalTypesByPresentationVideoIdDTO("id1", "chat,chien"),
			new AnimalTypesByPresentationVideoIdDTO("id2", "chat,chien")
		);

		Map<VideoId, List<AnimalType>> animalTypesByPresentationVideoId = Map.of(
			new VideoId("id1"), List.of(new AnimalType("chat"), new AnimalType("chien")),
			new VideoId("id2"), List.of(new AnimalType("chat"), new AnimalType("chien"))
		);

		when(this.animalDao.getAnimalTypesGroupByPresentationVideoIds(videoIds)).thenReturn(animalTypesByPresentationVideoIdDTOs);
		when(this.animalMapper.mapToAnimalTypesByPresentationVideoIds(animalTypesByPresentationVideoIdDTOs)).thenReturn(animalTypesByPresentationVideoId);

		// Act
		Map<VideoId, List<AnimalType>> actualMap = this.animalAdapter.getAnimalTypesByPresentationVideoIds(videoIds);

		// Assert
		Map<VideoId, List<AnimalType>> expectedMap = Map.of(
			new VideoId("id1"), List.of(new AnimalType("chat"), new AnimalType("chien")),
			new VideoId("id2"), List.of(new AnimalType("chat"), new AnimalType("chien"))
		);

		assertThat(actualMap).isEqualTo(expectedMap);
	}

	@Test
	public void shouldSubmitAnimals() {
		// Arrange
		List<Animal> animals = createAnimals();
		List<AnimalDTO> animalDTOs = createAnimalDTOs();

		when(this.animalMapper.mapAllToDTO(animals)).thenReturn(animalDTOs);
		when(this.animalDao.submitAnimals(animalDTOs)).thenReturn(new ActionSuccess(true));

		// Act
		ActionSuccess actualActionSuccess = this.animalAdapter.submitAnimals(animals);

		// Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static List<Animal> createAnimals() {
		Member member = createMember();

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
			),
			new Animal(
				new Id(2),
				"Maul",
				new AnimalType("chien"),
				4,
				new VideoId("id1"),
				member,
				LocalDate.of(2024, 3, 8),
				false
			)
		);
	}

	private static List<AnimalDTO> createAnimalDTOs() {
		return List.of(
			new AnimalDTO(0, "Oslo", "chat", 3, "id1", 0, "2024-03-08"),
			new AnimalDTO(1, "Uta", "chat", 1, "id1", 0, "2024-03-08"),
			new AnimalDTO(2, "Maul", "chien", 4, "id1", 0, "2024-03-08")
		);
	}

	private static Member createMember() {
		return new Member(
			new Id(1), new FirstName("Alvin"), new LastName("Hamaide"), new MemberCity("Valenciennes"), "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");
	}

	private static List<AnimalType> createAnimalTypes() {
		return List.of(new AnimalType("chat"), new AnimalType("lapin"), new AnimalType("chien"));
	}

	private static List<String> createAnimalTypeDTOs() {
		return List.of("chat", "lapin", "chien");
	}

}