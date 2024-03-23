package com.petflix.application.presenter;

import com.petflix.application.dto.ActionSuccessViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ActionSuccessPresenterTest {

	private ActionSuccessPresenter actionSuccessPresenter;

	@BeforeEach
	public void setUp() {
		this.actionSuccessPresenter = new ActionSuccessPresenter();
	}

	@Test
	public void shouldPresentActionSuccess() {
	    // Arrange
		ActionSuccessViewModel actionSuccessViewModel = new ActionSuccessViewModel(true, null);

	    // Act
		ResponseEntity<String> actualPresented = this.actionSuccessPresenter.present(actionSuccessViewModel);

	    // Assert
	    ResponseEntity<String> expectedPresented = ResponseEntity.ok(createPresented());

		assertThat(actualPresented).isEqualTo(expectedPresented);
	}

	private static String createPresented() {
		return "{\"success\":true}";
	}

}