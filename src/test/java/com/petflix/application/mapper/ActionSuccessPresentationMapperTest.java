package com.petflix.application.mapper;

import com.petflix.application.dto.ActionSuccessViewModel;
import com.petflix.domain.bean.ActionSuccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ActionSuccessPresentationMapperTest {

	private ActionSuccessPresentationMapper actionSuccessPresentationMapper;

	@BeforeEach
	public void setUp() {
		this.actionSuccessPresentationMapper = new ActionSuccessPresentationMapper();
	}

	@Test
	public void shouldMapToViewModel() {
		// Arrange
		ActionSuccess actionSuccess = new ActionSuccess(true);

		// Act
		ActionSuccessViewModel actualViewModel = this.actionSuccessPresentationMapper.mapToViewModel(actionSuccess);

	    // Assert
	    ActionSuccessViewModel expectedViewModel = new ActionSuccessViewModel(true, null);

		assertThat(actualViewModel).isEqualTo(expectedViewModel);
	}

	@Test
	public void shouldMapToViewModelWithError() {
		// Arrange
		ActionSuccess actionSuccess = new ActionSuccess(false, Optional.of("error"));

		// Act
		ActionSuccessViewModel actualViewModel = this.actionSuccessPresentationMapper.mapToViewModel(actionSuccess);

		// Assert
		ActionSuccessViewModel expectedViewModel = new ActionSuccessViewModel(false, "error");

		assertThat(actualViewModel).isEqualTo(expectedViewModel);
	}

}