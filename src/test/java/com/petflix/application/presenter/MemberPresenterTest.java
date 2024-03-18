package com.petflix.application.presenter;

import com.petflix.application.dto.MemberViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberPresenterTest {


	private MemberPresenter memberPresenter;

	@BeforeEach
	public void setUp() {
		this.memberPresenter = new MemberPresenter();
	}

	@Test
	public void shouldReturnPresentedAnimalsAndMember() {
		// Act
		ResponseEntity<String> actualPresentedMember = this.memberPresenter.presentAll(createMemberViewModels());

		// Assert
		ResponseEntity<String> expectedPresentedMember = ResponseEntity.ok(createPresentedMembers());

		assertThat(actualPresentedMember).isEqualTo(expectedPresentedMember);
	}

	private static String createPresentedMembers() {
		return "[{\"id\":0,\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"city\":\"Valenciennes\",\"mail\":\"alvin.hamaide@mail-ecv.fr\",\"phone\":\"06XXXXXXXX\"}]";
	}

	private static List<MemberViewModel> createMemberViewModels() {
		return List.of(
			new MemberViewModel(0, "Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX")
		);
	}

}