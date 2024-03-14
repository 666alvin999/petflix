package com.petflix.application.presenter;

import com.petflix.application.dto.PresentationVideoFiltersViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationVideoFiltersPresenterTest {

	private PresentationVideoFiltersPresenter presentationVideoFiltersPresenter;

	@BeforeEach
	public void setUp() {
		this.presentationVideoFiltersPresenter = new PresentationVideoFiltersPresenter();
	}

	@Test
	public void shouldReturnPresentedAnimalsAndMember() {
		// Act
		ResponseEntity<String> actualResponseEntity = this.presentationVideoFiltersPresenter.present(createViewModel());

		// Assert
		ResponseEntity<String> expectedResponseEntity = createResponseEntity();

		assertThat(actualResponseEntity).isEqualTo(expectedResponseEntity);
	}

	private static ResponseEntity<String> createResponseEntity() {
		return ResponseEntity.ok("{\"animalFilters\":[\"chat\",\"chien\"],\"cityFilters\":[\"Valenciennes\",\"Lille\"]}");
	}

	private static PresentationVideoFiltersViewModel createViewModel() {
		return new PresentationVideoFiltersViewModel(
			List.of("chat", "chien"),
			List.of("Valenciennes", "Lille")
		);
	}

}