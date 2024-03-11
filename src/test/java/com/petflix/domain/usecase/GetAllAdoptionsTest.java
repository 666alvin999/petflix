package com.petflix.domain.usecase;

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
		// Arrange
		Adopter adopter = GetAllAdoptionsTest.createAdopter();
		Member managingMember = GetAllAdoptionsTest.createMember();
		Animal animal = GetAllAdoptionsTest.createAnimal(0, "Oslo", 3, managingMember);
		Animal secondAnimal = GetAllAdoptionsTest.createAnimal(1, "Uta", 1, managingMember);

		List<Adoption> adoptions = GetAllAdoptionsTest.createAdoptions(adopter, animal, secondAnimal);

		when(this.adoptionPort.getAllAdoptions()).thenReturn(adoptions);

		// Act
		List<Adoption> actualAdoptions = this.getAllAdoptions.execute();

		// Assert
		Adopter expectedAdopter = GetAllAdoptionsTest.createAdopter();
		Member expectedManagingMember = GetAllAdoptionsTest.createMember();
		Animal expectedAnimal = GetAllAdoptionsTest.createAnimal(0, "Oslo", 3, expectedManagingMember);
		Animal expectedSecondAnimal = GetAllAdoptionsTest.createAnimal(1, "Uta", 1, expectedManagingMember);

		List<Adoption> expectedAdoptions = GetAllAdoptionsTest.createAdoptions(expectedAdopter, expectedAnimal, expectedSecondAnimal);

		assertThat(actualAdoptions).isEqualTo(expectedAdoptions);
	}

	private static List<Adoption> createAdoptions(Adopter adopter, Animal animal, Animal secondAnimal) {
		return List.of(
			new Adoption(animal, adopter, LocalDate.of(2024, 2, 29)),
			new Adoption(secondAnimal, adopter, LocalDate.of(2024, 2, 29))
		);
	}

	private static Animal createAnimal(int value, String animalName, int age, Member managingMember) {
		return new Animal(
			new Id(value),
			animalName,
			new AnimalType("chat"),
			age,
			new VideoId("id1"),
			managingMember,
			LocalDate.of(2024, 3, 8)
		);
	}

	private static Member createMember() {
		return new Member(new Id(0), new FirstName("Citanimal"), new LastName("Asso"), new MemberCity("Valenciennes"), "citanimal@gmail.com", "06XXXXXXXX");
	}

	private static Adopter createAdopter() {
		return new Adopter(new Id(0), new FirstName("Alvin"), new LastName("Hamaide"), "Valenciennes", "alvin.hamaide@mail-ecv.fr");
	}

}