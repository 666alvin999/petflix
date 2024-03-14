package com.petflix.application.presenter;

import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.application.dto.PresentationVideoViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationVideoAndAnimalsAndMemberPresenterTest {

	private PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter;

	@BeforeEach
	public void setUp() {
		this.presentationVideoAndAnimalsAndMemberPresenter = new PresentationVideoAndAnimalsAndMemberPresenter();
	}

	@Test
	public void shouldReturnPresentedAnimalsAndMember() {
		// Act
		ResponseEntity<String> actualPresentedMember = this.presentationVideoAndAnimalsAndMemberPresenter.present(createAnimalsAndMemberDTOs());

	    // Assert
		ResponseEntity<String> expectedPresentedMember = ResponseEntity.ok(createPresentedAnimalsAndMember());

		assertThat(actualPresentedMember).isEqualTo(expectedPresentedMember);
	}

	private static String createPresentedAnimalsAndMember() {
		return "{\"presentationVideo\":{\"id\":\"id1\",\"title\":\"title\",\"description\":\"description\",\"uploadDate\":\"2024-03-13\"},\"animals\":[{\"name\":\"Oslo\",\"type\":\"chat\",\"age\":3,\"presentationVideoId\":\"https://www.url1.com\",\"arrivalDate\":\"2024-03-08\",\"adopted\":false},{\"name\":\"Uta\",\"type\":\"chat\",\"age\":1,\"presentationVideoId\":\"https://www.url1.com\",\"arrivalDate\":\"2024-03-08\",\"adopted\":false}],\"member\":{\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"city\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\",\"phone\":\"06XXXXXXXX\"}}";
	}

	private static PresentationVideoAndAnimalsAndMemberViewModel createAnimalsAndMemberDTOs() {
		return new PresentationVideoAndAnimalsAndMemberViewModel(
			new PresentationVideoViewModel(
				"id1",
				"title",
				"description",
				"2024-03-13"
			),
			List.of(
				new AnimalViewModel("Oslo", "chat", 3, "https://www.url1.com", "2024-03-08", false),
				new AnimalViewModel("Uta", "chat", 1, "https://www.url1.com", "2024-03-08", false)
			),
			new MemberViewModel("Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX")
		);
	}

}