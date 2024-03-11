package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideoFilters;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.MemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPresentationVideoFiltersTest {

	private GetAllVideoFilters getAllVideoFilters;

	@Mock
	private AnimalPort animalPort;

	@Mock
	private MemberPort memberPort;

	@BeforeEach
	public void setUp() {
		this.getAllVideoFilters = new GetAllVideoFilters(this.animalPort, this.memberPort);
	}

	@Test
	public void shouldReturnAnimalTypes() {
		// Arrange
		List<AnimalType> animalTypes = createAnimalTypes();
		List<MemberCity> memberCities = createMemberCities();

		when(this.animalPort.getAllTypes()).thenReturn(animalTypes);
		when(this.memberPort.getAllMembersCity()).thenReturn(memberCities);

		// Act
		PresentationVideoFilters actualPresentationVideoFilters = this.getAllVideoFilters.execute();

		// Assert
		PresentationVideoFilters expectedPresentationVideoFilters = createVideoFilters();

		assertThat(actualPresentationVideoFilters).isEqualTo(expectedPresentationVideoFilters);
	}

	private static PresentationVideoFilters createVideoFilters() {
		return new PresentationVideoFilters(createAnimalTypes(), createMemberCities());
	}

	private static List<AnimalType> createAnimalTypes() {
		return List.of(new AnimalType("chat"), new AnimalType("lapin"), new AnimalType("chien"));
	}

	private static List<MemberCity> createMemberCities() {
		return List.of(new MemberCity("Valenciennes"), new MemberCity("Lille"));
	}

}