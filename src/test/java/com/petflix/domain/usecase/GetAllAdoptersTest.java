package com.petflix.domain.usecase;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.port.AdopterPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllAdoptersTest {

	private GetAllAdopters getAllAdopters;

	@Mock
	private AdopterPort adopterPort;

	@BeforeEach
	public void setUp() {
		this.getAllAdopters = new GetAllAdopters(this.adopterPort);
	}

	@Test
	public void shouldReturnAllAdopters() {
	    // Arrange
		when(this.adopterPort.getAllAdopters()).thenReturn(createAdopters());

	    // Act
	    List<Adopter> actualAdopters = this.getAllAdopters.execute();

	    // Assert
	    List<Adopter> expectedAdopters = createAdopters();

		assertThat(actualAdopters).isEqualTo(expectedAdopters);
	}

	private static List<Adopter> createAdopters() {
		return List.of(
			new Adopter(
				new Id(0),
				new FirstName("Alvin"),
				new LastName("Hamaide"),
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr"
			),
			new Adopter(
				new Id(1),
				new FirstName("Martin"),
				new LastName("Matin"),
				"Valenciennes",
				"martin.matin@mail-ecv.fr"
			)
		);
	}

}