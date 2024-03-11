package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoFiltersViewModel;
import com.petflix.domain.bean.PresentationVideoFilters;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationVideoFiltersMapperTest {

	private PresentationVideoFiltersMapper presentationVideoFiltersMapper;

	@BeforeEach
	public void setUp() {
		this.presentationVideoFiltersMapper = new PresentationVideoFiltersMapper();
	}

	@Test
	public void shouldMapToViewModel() {
	    // Arrange
		PresentationVideoFilters presentationVideoFilters = new PresentationVideoFilters(
			List.of(
				new AnimalType("chat"),
				new AnimalType("chien")
			),
			List.of(
				new MemberCity("Valenciennes"),
				new MemberCity("Lille")
			)
		);

	    // Act
		PresentationVideoFiltersViewModel actualViewModel = this.presentationVideoFiltersMapper.mapToViewModel(presentationVideoFilters);

	    // Assert
		PresentationVideoFiltersViewModel expectedViewModel = new PresentationVideoFiltersViewModel(
			List.of("chat", "chien"),
			List.of("Valenciennes", "Lille")
		);

		assertThat(actualViewModel).isEqualTo(expectedViewModel);
	}

}