package com.petflix.application.mapper;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdopterPresentationMapperTest {

	private AdopterPresentationMapper adopterPresentationMapper;

	@BeforeEach
	public void setUp() {
		this.adopterPresentationMapper = new AdopterPresentationMapper();
	}

	@Test
	public void shouldMapToViewModel() {
	    // Act
		AdopterViewModel actualAdopterViewModel = this.adopterPresentationMapper.mapToViewModel(createAdopter());

	    // Assert
		AdopterViewModel expectedAdopterViewModel = createAdopterViewModel();

		assertThat(actualAdopterViewModel).isEqualTo(expectedAdopterViewModel);
	}

	@Test
	public void shouldMapAllToViewModels() {
		// Act
		List<AdopterViewModel> actualAdopterViewModels = this.adopterPresentationMapper.mapAllToViewModels(List.of(createAdopter()));

		// Assert
		List<AdopterViewModel> expectedAdopterViewModels = List.of(createAdopterViewModel());

		assertThat(actualAdopterViewModels).isEqualTo(expectedAdopterViewModels);
	}

	@Test
	public void shouldMapToDomain() {
		// Act
		Adopter actualAdopter = this.adopterPresentationMapper.mapToDomain(createAdopterViewModel());

		// Assert
		Adopter expectedAdopter = createAdopter();

		assertThat(actualAdopter).isEqualTo(expectedAdopter);
	}

	private static AdopterViewModel createAdopterViewModel() {
		return new AdopterViewModel(
			0,
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
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

}