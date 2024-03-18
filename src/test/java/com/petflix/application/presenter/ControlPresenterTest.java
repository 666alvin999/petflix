package com.petflix.application.presenter;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.ControlViewModel;
import com.petflix.application.dto.MemberViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlPresenterTest {

	private ControlPresenter controlPresenter;

	@BeforeEach
	public void setUp() {
		this.controlPresenter = new ControlPresenter();
	}

	@Test
	public void shouldReturnPresentedViewModels() {
		// Act
		ResponseEntity<String> actualPresented = this.controlPresenter.presentAll(createViewModels());

		// Assert
		ResponseEntity<String> expectedPresented = ResponseEntity.ok(createAllPresented());

		assertThat(actualPresented).isEqualTo(expectedPresented);
	}

	private static String createAllPresented() {
		return "[{\"animal\":{\"name\":\"Oslo\",\"type\":\"chat\",\"age\":3,\"presentationVideoId\":\"id1\",\"arrivalDate\":\"2024-03-08\",\"adopted\":true},\"member\":{\"id\":0,\"firstName\":\"Citanimal\",\"lastName\":\"Asso\",\"city\":\"Valenciennes\",\"mail\":\"citanimal@gmail.com\",\"phone\":\"06XXXXXXXX\"},\"adopter\":{\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"address\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\"},\"adoptionDate\":\"2024-02-29\",\"controlDate\":\"2024-08-29\"},{\"animal\":{\"name\":\"Oslo\",\"type\":\"chat\",\"age\":3,\"presentationVideoId\":\"id1\",\"arrivalDate\":\"2024-03-08\",\"adopted\":true},\"member\":{\"id\":0,\"firstName\":\"Citanimal\",\"lastName\":\"Asso\",\"city\":\"Valenciennes\",\"mail\":\"citanimal@gmail.com\",\"phone\":\"06XXXXXXXX\"},\"adopter\":{\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"address\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\"},\"adoptionDate\":\"2024-03-14\",\"controlDate\":\"20224-09-14\"}]";
	}

	private static List<ControlViewModel> createViewModels() {
		AnimalViewModel animal = new AnimalViewModel(
			"Oslo",
			"chat",
			3,
			"id1",
			"2024-03-08",
			true
		);

		MemberViewModel member = new MemberViewModel(
			0,
			"Citanimal",
			"Asso",
			"Valenciennes",
			"citanimal@gmail.com",
			"06XXXXXXXX"
		);

		AdopterViewModel adopter = new AdopterViewModel(
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);

		return List.of(
			new ControlViewModel(
				animal,
				member,
				adopter,
				"2024-02-29",
				"2024-08-29"
			),
			new ControlViewModel(
				animal,
				member,
				adopter,
				"2024-03-14",
				"20224-09-14"
			)
		);
	}

}