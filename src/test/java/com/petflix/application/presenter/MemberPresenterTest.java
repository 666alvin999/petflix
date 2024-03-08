package com.petflix.application.presenter;

import com.petflix.application.dto.MemberPresentationDTO;
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
	public void shouldReturnPresentedMember() {
		//Act
		ResponseEntity<String> actualPresentedMember = this.memberPresenter.present(createMemberDTOs().get(0));

	    //Assert
		ResponseEntity<String> expectedPresentedMember = ResponseEntity.ok(createPresentedMember());

		assertThat(actualPresentedMember).isEqualTo(expectedPresentedMember);
	}

	@Test
	public void shouldReturnPresentedMembers() {
		//Act
		ResponseEntity<String> actualPresentedMember = this.memberPresenter.presentAll(createMemberDTOs());

		//Assert
		ResponseEntity<String> expectedPresentedMember = ResponseEntity.ok(createPresentedMembers());

		assertThat(actualPresentedMember).isEqualTo(expectedPresentedMember);
	}

	private static String createPresentedMember() {
		return "{\"id\":0,\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"city\":\"Valenciennes\",\"email\":\"alvin.hamaide@mail-ecv.fr\",\"phone\":\"06XXXXXXXX\"}";
	}

	private static String createPresentedMembers() {
		return "[{\"id\":0,\"firstName\":\"Alvin\",\"lastName\":\"Hamaide\",\"city\":\"Valenciennes\",\"email\":\"alvin.hamaide@mail-ecv.fr\",\"phone\":\"06XXXXXXXX\"},{\"id\":1,\"firstName\":\"Martin\",\"lastName\":\"Matin\",\"city\":\"Valenciennes\",\"email\":\"martin.matin@mail-ecv.fr\",\"phone\":\"06XXXXXXXX\"}]";
	}

	private static List<MemberPresentationDTO> createMemberDTOs() {
		return List.of(
			new MemberPresentationDTO(0, "Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX"),
			new MemberPresentationDTO(1, "Martin", "Matin", "Valenciennes", "martin.matin@mail-ecv.fr", "06XXXXXXXX")
		);
	}

}