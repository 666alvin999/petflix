package com.petflix.application.presenter;

import com.petflix.application.dto.AnimalsAndMemberViewModel;
import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.MemberViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalsAndMemberPresenterTest {

	private AnimalsAndMemberPresenter animalsAndMemberPresenter;

	@BeforeEach
	public void setUp() {
		this.animalsAndMemberPresenter = new AnimalsAndMemberPresenter();
	}

	@Test
	public void shouldReturnPresentedAnimalsAndMember() {
		// Act
		ResponseEntity<String> actualPresentedMember = this.animalsAndMemberPresenter.present(createAnimalsAndMemberDTOs());

	    // Assert
		ResponseEntity<String> expectedPresentedMember = ResponseEntity.ok(createPresentedMember());

		assertThat(actualPresentedMember).isEqualTo(expectedPresentedMember);
	}

	private static String createPresentedMember() {
		return "{\"animals\":[{\"name\":\"Oslo\",\"type\":\"chat\",\"age\":3,\"presentationVideoUrl\":\"https://www.url1.com\",\"arrivalDate\":\"2024-03-08\",\"adopted\":false},{\"name\":\"Uta\",\"type\":\"chat\",\"age\":1,\"presentationVideoUrl\":\"https://www.url1.com\",\"arrivalDate\":\"2024-03-08\",\"adopted\":false}],\"member\":{\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"city\":\"Valenciennes\",\"email\":\"alvin.hamaide@mail-ecv.fr\",\"phone\":\"06XXXXXXXX\"}}";
	}

	private static AnimalsAndMemberViewModel createAnimalsAndMemberDTOs() {
		return new AnimalsAndMemberViewModel(
			List.of(
				new AnimalViewModel("Oslo", "chat", 3, "https://www.url1.com", "2024-03-08", false),
				new AnimalViewModel("Uta", "chat", 1, "https://www.url1.com", "2024-03-08", false)
			),
			new MemberViewModel("Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX")
		);
	}

}