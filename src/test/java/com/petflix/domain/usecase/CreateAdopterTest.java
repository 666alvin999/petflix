package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAdopterTest {

	private CreateAdopter createAdopter;

	@Mock
	private AdopterPort adopterPort;

	@BeforeEach
	public void setUp() {
		this.createAdopter = new CreateAdopter(this.adopterPort);
	}

	@Test
	public void Name() {
	    // Arrange
		Adopter adopter = createAdopter();

	    when(this.adopterPort.createAdopter(adopter)).thenReturn(new ActionSuccess(true));

	    // Act
	    ActionSuccess actualActionSuccess = this.createAdopter.execute(adopter);

	    // Assert
	    ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
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

}