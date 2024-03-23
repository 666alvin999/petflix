package com.petflix.domain.usecase;

import com.petflix.domain.bean.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAdoptionTest {

	private CreateAdoption createAdoption;

	@Mock
	private AdoptionPort adoptionPort;

	@BeforeEach
	public void setUp() {
		this.createAdoption = new CreateAdoption(this.adoptionPort);
	}

	@Test
	public void shouldReturnActionSuccess() {
		// Arrange
		Adoption adoption = createAdoption();

		when(this.adoptionPort.createAdoption(adoption)).thenReturn(new ActionSuccess(true));

		// Act
		ActionSuccess actualActionSuccess = this.createAdoption.execute(adoption);

		// Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static Adoption createAdoption() {
		return new Adoption(
			createAnimal(),
			createAdopter(),
			LocalDate.of(2024, 2, 29)
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
		Member member = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			new MemberCity("Valenciennes"),
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		return new Animal(
			new Id(0),
			"Oslo",
			new AnimalType("chat"),
			3,
			new VideoId("id1"),
			member,
			LocalDate.of(2024, 3, 8),
			false
		);
	}

}