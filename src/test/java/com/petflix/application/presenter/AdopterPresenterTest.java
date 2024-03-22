package com.petflix.application.presenter;

import com.petflix.application.dto.AdopterViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdopterPresenterTest {

	private AdopterPresenter adopterPresenter;

	@BeforeEach
	public void setUp() {
		this.adopterPresenter = new AdopterPresenter();
	}

	@Test
	public void shouldReturnPresentedViewModels() {
		// Act
		ResponseEntity<String> actualPresented = this.adopterPresenter.presentAll(createViewModels());

		// Assert
		ResponseEntity<String> expectedPresented = ResponseEntity.ok(createAllPresented());

		assertThat(actualPresented).isEqualTo(expectedPresented);
	}

	private static String createAllPresented() {
		return "[{\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"address\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\"},{\"firstName\":\"Martin\",\"lastName\":\"Matin\",\"address\":\"Lille\",\"mail\":\"martin.matin.@mail-ecv.fr\"}]";
	}

	private static List<AdopterViewModel> createViewModels() {
		return List.of(
			new AdopterViewModel(
				"Alvin",
				"Hamaide",
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr"
			),
			new AdopterViewModel(
				"Martin",
				"Matin",
				"Lille",
				"martin.matin.@mail-ecv.fr"
			)
		);
	}

}