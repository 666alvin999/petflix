package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.bean.presentationvideofields.VideoId;
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
class GetAnimalTypesByPresentationVideoIdTest {

	private GetAnimalTypesByPresentationVideoId getAnimalTypesByPresentationVideoId;

	@Mock
	private AnimalPort animalPort;

	@BeforeEach
	public void setUp() {
		this.getAnimalTypesByPresentationVideoId = new GetAnimalTypesByPresentationVideoId(this.animalPort);
	}

	@Test
	public void Name() {
	    //Arrange
		List<AnimalType> animalTypes = createAnimalTypes();

		when(this.animalPort.getAnimalTypesByPresentationVideoId("id1")).thenReturn(animalTypes);

	    //Act
	    List<AnimalType> actualAnimalTypes = this.getAnimalTypesByPresentationVideoId.execute(new VideoId("id1"));

	    //Assert
		List<AnimalType> expectedAnimalTypes = createAnimalTypes();

		assertThat(actualAnimalTypes).isEqualTo(expectedAnimalTypes);
	}

	private static List<AnimalType> createAnimalTypes() {
		return List.of(
			new AnimalType("chien"),
			new AnimalType("chat")
		);
	}

}