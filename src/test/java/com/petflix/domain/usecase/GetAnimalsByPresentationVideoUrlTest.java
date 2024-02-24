package com.petflix.domain.usecase;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
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
		Member managingMember = new Member(1, "Alvin", "Hamaide", "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");
		String url = "http://www.url1.com";
		Animal animal = new Animal(1, "Oslo", "chat", 2, url, managingMember);

		when(this.animalPort.getAnimalsByPresentationVideoUrl(url)).thenReturn(List.of(animal));

	    //Act
		List<Animal> actualAnimal = this.getAnimalsByPresentationVideoUrl.execute(url);

	    //Assert
		Member expectedManagingMember = new Member(1, "Alvin", "Hamaide", "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");
		String expectedUrl = "http://www.url1.com";
		Animal expectedAnimal = new Animal(1, "Oslo", "chat", 2, expectedUrl, expectedManagingMember);

		assertThat(actualAnimal).isEqualTo(List.of(expectedAnimal));
	}

}