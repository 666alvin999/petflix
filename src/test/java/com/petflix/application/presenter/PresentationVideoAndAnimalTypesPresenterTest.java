package com.petflix.application.presenter;

import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationVideoAndAnimalTypesPresenterTest {

		private PresentationVideoAndAnimalTypesPresenter presentationVideoAndAnimalTypesPresenter;

		@BeforeEach
		public void setUp() {
			this.presentationVideoAndAnimalTypesPresenter = new PresentationVideoAndAnimalTypesPresenter();
		}

		@Test
		public void shouldReturnPresentedAdopter() {
			//Act
			ResponseEntity<String> actualPresented = this.presentationVideoAndAnimalTypesPresenter.present(createViewModels().get(0));

			//Assert
			ResponseEntity<String> expectedPresented = ResponseEntity.ok(createPresented());

			assertThat(actualPresented).isEqualTo(expectedPresented);
		}

		@Test
		public void shouldReturnPresentedAdopters() {
			//Act
			ResponseEntity<String> actualPresented = this.presentationVideoAndAnimalTypesPresenter.presentAll(createViewModels());

			//Assert
			ResponseEntity<String> expectedPresented = ResponseEntity.ok(createAllPresented());

			assertThat(actualPresented).isEqualTo(expectedPresented);
		}

		private static String createPresented() {
			return "{\"id\":\"0\",\"title\":\"title1\",\"description\":\"description1\",\"date\":\"2024-03-11\",\"animalTypes\":[\"chien\",\"chat\"]}";
		}

		private static String createAllPresented() {
			return "[{\"id\":\"0\",\"title\":\"title1\",\"description\":\"description1\",\"date\":\"2024-03-11\",\"animalTypes\":[\"chien\",\"chat\"]},{\"id\":\"1\",\"title\":\"title2\",\"description\":\"description2\",\"date\":\"2024-03-11\",\"animalTypes\":[\"chien\",\"chat\"]}]";
		}

		private static List<PresentationVideoAndAnimalTypesViewModel> createViewModels() {
			return List.of(
				new PresentationVideoAndAnimalTypesViewModel(
					"0",
					"title1",
					"description1",
					"2024-03-11",
					List.of(
						"chien",
						"chat"
					)
				),
				new PresentationVideoAndAnimalTypesViewModel(
					"1",
					"title2",
					"description2",
					"2024-03-11",
					List.of(
						"chien",
						"chat"
					)
				)
			);
		}


}