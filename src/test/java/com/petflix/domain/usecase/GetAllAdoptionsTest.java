package com.petflix.domain.usecase;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.port.AdoptionPort;
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
class GetAllAdoptionsTest {

	private GetAllAdoptions getAllAdoptions;

	@Mock
	private AdoptionPort adoptionPort;

	@BeforeEach
	void setUp() {
		this.getAllAdoptions = new GetAllAdoptions(adoptionPort);
	}

	@Test
	public void shouldReturnAdoptions() {
		//Arrange
		Adopter adopter = new Adopter(new Id(0), new FirstName("Alvin"), new LastName("Hamaide"), "Valenciennes", "alvin.hamaide@mail-ecv.fr");
		Member managingMember = new Member(new Id(0), new FirstName("Citanimal"), new LastName("Asso"), "Valenciennes", "citanimal@gmail.com", "06XXXXXXXX");
		Animal animal = new Animal(new Id(0), "Oslo", new AnimalType("chat"), 3, new Url("https://www.url1.com"), managingMember);
		Animal secondAnimal = new Animal(new Id(1), "Uta", new AnimalType("chat"), 1, new Url("https://www.url1.com"), managingMember);

		List<Adoption> adoptions = List.of(
			new Adoption(new Id(0), adopter, animal, LocalDate.of(2024, 2, 29)),
			new Adoption(new Id(1), adopter, secondAnimal, LocalDate.of(2024, 2, 29))
		);

		when(this.adoptionPort.getAllAdoptions()).thenReturn(adoptions);

		//Act
		List<Adoption> actualAdoptions = this.getAllAdoptions.execute();

		//Assert
		Adopter expectedAdopter = new Adopter(new Id(0), new FirstName("Alvin"), new LastName("Hamaide"), "Valenciennes", "alvin.hamaide@mail-ecv.fr");
		Member expectedManagingMember = new Member(new Id(0), new FirstName("Citanimal"), new LastName("Asso"), "Valenciennes", "citanimal@gmail.com", "06XXXXXXXX");
		Animal expectedAnimal = new Animal(new Id(0), "Oslo", new AnimalType("chat"), 3, new Url("https://www.url1.com"), expectedManagingMember);
		Animal expectedSecondAnimal = new Animal(new Id(1), "Uta", new AnimalType("chat"), 1, new Url("https://www.url1.com"), expectedManagingMember);

		List<Adoption> expectedAdoptions = List.of(
			new Adoption(new Id(0), expectedAdopter, expectedAnimal, LocalDate.of(2024, 2, 29)),
			new Adoption(new Id(1), expectedAdopter, expectedSecondAnimal, LocalDate.of(2024, 2, 29))
		);

		assertThat(actualAdoptions).isEqualTo(expectedAdoptions);
	}

}