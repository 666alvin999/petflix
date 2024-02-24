package com.petflix.domain.usecase;

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
		List<String> animalTypes = List.of("chat", "lapins", "chien");

		when(this.animalPort.getAllTypes()).thenReturn(animalTypes);

		//Act
		List<String> actualAnimalTypes = this.getAllAnimalTypes.execute();

		//Assert
		List<String> expectedAnimalTypes = List.of("chat", "lapins", "chien");

		assertThat(actualAnimalTypes).isEqualTo(expectedAnimalTypes);
	}
}