package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.mapper.AnimalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalAdapterTest {

	private AnimalAdapter animalAdapter;

	@Mock
	private AnimalDao animalDao;

	@Mock
	private AnimalMapper animalMapper;

	@Mock
	private MemberAdapter memberAdapter;

	@BeforeEach
	public void setUp() {
		this.animalAdapter = new AnimalAdapter(this.animalDao, this.memberAdapter, this.animalMapper);
	}

	@Test
	public void shouldReturnAllTypes() {
	    //Arrange
		List<String> animalTypesDTO = createAnimalTypeDTOs();
		List<AnimalType> animalTypes = createAnimalTypes();

		when(this.animalDao.getAllTypes()).thenReturn(animalTypesDTO);
		when(this.animalMapper.mapAllToAnimalTypes(animalTypesDTO)).thenReturn(animalTypes);

	    //Act
		List<AnimalType> actualAnimalTypes = this.animalAdapter.getAllTypes();

	    //Assert
		List<AnimalType> expectedAnimalTypes = createAnimalTypes();

		assertThat(actualAnimalTypes).isEqualTo(expectedAnimalTypes);
	}

	@Test
	public void shouldReturnAnimalById() {
	    //Arrange
		AnimalDTO animalDTO = createAnimalDTOs().get(0);
		Member member = createMember();
		String url = "https://www.url1.com";

	    when(this.animalDao.getAnimalById(0)).thenReturn(List.of(animalDTO));
		when(this.animalMapper.mapToDomain(animalDTO, member)).thenReturn(createAnimals(url, member).get(0));
		when(this.memberAdapter.getMemberById(0)).thenReturn(member);

	    //Act
		Animal actualAnimal = this.animalAdapter.getAnimalById(0);

	    //Assert
		Animal expectedAnimal = createAnimals(url, member).get(0);

		assertThat(actualAnimal).isEqualTo(expectedAnimal);
	}

	@Test
	public void shouldReturnAnimalsByIds() {
		//Arrange
		List<AnimalDTO> animalDTOs = createAnimalDTOs();
		Member member = createMember();
		String url = "https://www.url1.com";

		when(this.animalDao.getAnimalsByIds(Set.of(0, 1))).thenReturn(animalDTOs);
		when(this.animalMapper.mapAllToDomain(animalDTOs, List.of(member))).thenReturn(createAnimals(url, member));
		when(this.memberAdapter.getMembersByIds(Set.of(0))).thenReturn(List.of(member));

		//Act
		List<Animal> actualAnimals = this.animalAdapter.getAnimalsByIds(Set.of(0, 1));

		//Assert
		List<Animal> expectedAnimals = createAnimals(url, member);

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	@Test
	public void shouldReturnAnimalsByUrl() {
	    //Arrange
		String url = "https://www.url1.com";

		List<Member> members = List.of(createMember());
		List<AnimalDTO> animalDTOs = createAnimalDTOs();
		List<Animal> animals = createAnimals(url, members.get(0));

		when(this.animalDao.getAnimalsByPresentationVideoUrl(url)).thenReturn(animalDTOs);
		when(this.memberAdapter.getMembersByIds(Set.of(0))).thenReturn(members);
		when(this.animalMapper.mapAllToDomain(animalDTOs, members)).thenReturn(animals);

	    //Act
		List<Animal> actualAnimals = this.animalAdapter.getAnimalsByPresentationVideoUrl(url);

	    //Assert
		List<Animal> expectedAnimals = createAnimals(url, createMember());

		assertThat(actualAnimals).isEqualTo(expectedAnimals);
	}

	private static List<Animal> createAnimals(String url, Member member) {
		return List.of(
			new Animal(new Id(0), "Oslo", new AnimalType("chat"), 3, new Url(url), member),
			new Animal(new Id(0), "Uta", new AnimalType("chat"), 1, new Url(url), member),
			new Animal(new Id(0), "Maul", new AnimalType("chien"), 4, new Url(url), member)
		);
	}

	private static List<AnimalDTO> createAnimalDTOs() {
		return List.of(
			new AnimalDTO(0, "Oslo", "chat", 3, "https://www.url1.com", 0),
			new AnimalDTO(1, "Uta", "chat", 1, "https://www.url1.com", 0),
			new AnimalDTO(2, "Maul", "chien", 4, "https://www.url1.com", 0)
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