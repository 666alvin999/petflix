package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
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
class GetAllAnimalTypesTest {

	private GetAllAnimalTypes getAllAnimalTypes;

	@Mock
	private AnimalPort animalPort;

	@BeforeEach
	public void setUp() {
		this.getAllAnimalTypes = new GetAllAnimalTypes(animalPort);
	}

	@Test
	public void shouldReturnAnimalTypes() {
		//Arrange
		List<AnimalType> animalTypes = createAnimalTypes();

		when(this.animalPort.getAllTypes()).thenReturn(animalTypes);

		//Act
		List<AnimalType> actualAnimalTypes = this.getAllAnimalTypes.execute();

		//Assert
		List<AnimalType> expectedAnimalTypes = createAnimalTypes();

		assertThat(actualAnimalTypes).isEqualTo(expectedAnimalTypes);
	}

	private static List<AnimalType> createAnimalTypes() {
		return List.of(new AnimalType("chat"), new AnimalType("lapin"), new AnimalType("chien"));
	}

}