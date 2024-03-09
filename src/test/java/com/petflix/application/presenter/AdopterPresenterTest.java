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
	public void shouldReturnPresentedAdopter() {
		//Act
		ResponseEntity<String> actualPresentedAdopter = this.adopterPresenter.present(createAdopterDTOs().get(0));

		//Assert
		ResponseEntity<String> expectedPresentedAdopter = ResponseEntity.ok(createPresentedAdopter());

		assertThat(actualPresentedAdopter).isEqualTo(expectedPresentedAdopter);
	}

	@Test
	public void shouldReturnPresentedAdopters() {
		//Act
		ResponseEntity<String> actualPresentedAdopters = this.adopterPresenter.presentAll(createAdopterDTOs());

		//Assert
		ResponseEntity<String> expectedPresentedAdopters = ResponseEntity.ok(createPresentedAdopters());

		assertThat(actualPresentedAdopters).isEqualTo(expectedPresentedAdopters);
	}

	private static String createPresentedAdopter() {
		return "{\"id\":0,\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"address\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\"}";
	}

	private static String createPresentedAdopters() {
		return "[{\"id\":0,\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"address\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\"},{\"id\":1,\"firstName\":\"Martin\",\"lastName\":\"Matin\",\"address\":\"Valenciennes\",\"mail\":\"martin.matin@mail-ecv.fr\"}]";
	}

	private static List<AdopterViewModel> createAdopterDTOs() {
		return List.of(
			new AdopterViewModel(0, "Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr"),
			new AdopterViewModel(1, "Martin", "Matin", "Valenciennes", "martin.matin@mail-ecv.fr")
		);
	}

}