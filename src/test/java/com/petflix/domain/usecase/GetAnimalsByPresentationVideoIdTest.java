package com.petflix.domain.usecase;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;
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
class GetAnimalsByPresentationVideoIdTest {

	private GetAnimalsByPresentationVideoId getAnimalsByPresentationVideoId;

	@Mock
	private AnimalPort animalPort;

	@BeforeEach
	public void setUp() {
		this.getAnimalsByPresentationVideoId = new GetAnimalsByPresentationVideoId(animalPort);
	}

	@Test
	public void shouldReturnAnimals() {
		// Arrange
		VideoId videoId = createVideoId();
		Animal animal = createAnimal();

		when(this.animalPort.getAnimalsByPresentationVideoId(videoId.value())).thenReturn(List.of(animal));

		// Act
		List<Animal> actualAnimal = this.getAnimalsByPresentationVideoId.execute(videoId);

		// Assert
		List<Animal> expectedAnimals = List.of(createAnimal());

		assertThat(actualAnimal).isEqualTo(expectedAnimals);
	}

	private static VideoId createVideoId() {
		return new VideoId("id1");
	}

	private static Member createMember() {
		return new Member(new Id(1), new FirstName("Alvin"), new LastName("Hamaide"), new MemberCity("Valenciennes"), "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");
	}

	private static Animal createAnimal() {
		return new Animal(
			new Id(1),
			"Oslo",
			new AnimalType("chat"),
			2,
			createVideoId(),
			createMember(),
			LocalDate.of(2024, 3, 8)
		);
	}

}