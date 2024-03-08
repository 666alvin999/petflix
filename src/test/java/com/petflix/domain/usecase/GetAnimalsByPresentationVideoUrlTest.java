package com.petflix.domain.usecase;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.port.AnimalPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAnimalsByPresentationVideoUrlTest {

	private GetAnimalsByPresentationVideoUrl getAnimalsByPresentationVideoUrl;

	@Mock
	private AnimalPort animalPort;

	@BeforeEach
	public void setUp() {
		this.getAnimalsByPresentationVideoUrl = new GetAnimalsByPresentationVideoUrl(animalPort);
	}

	@Test
	public void shouldReturnAnimals() {
		//Arrange
		Url url = createUrl();
		Animal animal = createAnimal();

		when(this.animalPort.getAnimalsByPresentationVideoUrl(url.value())).thenReturn(List.of(animal));

		//Act
		List<Animal> actualAnimal = this.getAnimalsByPresentationVideoUrl.execute(url);

		//Assert
		List<Animal> expectedAnimals = List.of(createAnimal());

		assertThat(actualAnimal).isEqualTo(expectedAnimals);
	}

	private static Url createUrl() {
		return new Url("https://www.url1.com/");
	}

	private static Member createMember() {
		return new Member(new Id(1), new FirstName("Alvin"), new LastName("Hamaide"), new MemberCity("Valenciennes"), "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");
	}

	private static Animal createAnimal() {
		return new Animal(new Id(1), "Oslo", new AnimalType("chat"), 2, createUrl(), createMember());
	}

}