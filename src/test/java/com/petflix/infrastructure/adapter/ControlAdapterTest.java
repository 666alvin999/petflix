package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.*;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.infrastructure.dao.ControlDao;
import com.petflix.infrastructure.dto.ControlDTO;
import com.petflix.infrastructure.mapper.ControlMapper;
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
class ControlAdapterTest {

	private ControlAdapter controlAdapter;

	@Mock
	private ControlDao controlDao;

	@Mock
	private AdoptionAdapter adoptionAdapter;

	@Mock
	private ControlMapper controlMapper;

	@BeforeEach
	public void setUp() {
		this.controlAdapter = new ControlAdapter(this.controlDao, this.adoptionAdapter, this.controlMapper);
	}

	@Test
	public void shouldReturnControlById() {
	    //Arrange
		ControlDTO controlDTO = createControlDTO();

		when(this.controlDao.getControlById(0)).thenReturn(List.of(controlDTO));
		when(this.adoptionAdapter.getAdoptionById(0)).thenReturn(createAdoption());
		when(this.controlMapper.mapToDomain(controlDTO, createAdoption())).thenReturn(createControl());

	    //Act
	    Control actualControl = this.controlAdapter.getControlById(0);

	    //Assert
	    Control expectedControl = createControl();

		assertThat(actualControl).isEqualTo(expectedControl);
	}

	@Test
	public void shouldReturnAllControls() {
		//Arrange
		List<ControlDTO> controlDTOs = List.of(createControlDTO());
		List<Adoption> adoptions = List.of(createAdoption());

		when(this.controlDao.getAllControls()).thenReturn(controlDTOs);
		when(this.adoptionAdapter.getAdoptionsByIds(Set.of(0))).thenReturn(adoptions);
		when(this.controlMapper.mapAllToDomain(controlDTOs, adoptions)).thenReturn(List.of(createControl()));

		//Act
		List<Control> actualControls = this.controlAdapter.getAllControls();

		//Assert
		List<Control> expectedControls = List.of(createControl());

		assertThat(actualControls).isEqualTo(expectedControls);
	}

	private Control createControl() {
		return new Control(
			new Id(0),
			createAdoption(),
			LocalDate.of(2024, 2, 29)
		);
	}

	private static ControlDTO createControlDTO() {
		return new ControlDTO(0, 0, "2024-03-08");
	}

	private Adoption createAdoption() {
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
			new Url("https://www.url1.com"),
			managingMember,
			LocalDate.of(2024, 3, 8),
			null
		);

		Adopter adopter = new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);

		return new Adoption(
			new Id(0),
			adopter,
			animal,
			LocalDate.of(2024, 2, 29)
		);
	}

}