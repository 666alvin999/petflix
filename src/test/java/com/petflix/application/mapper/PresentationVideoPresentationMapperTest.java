package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationVideoPresentationMapperTest {

	private PresentationVideoPresentationMapper mapper;

	@BeforeEach
	public void setUp() {
		this.mapper = new PresentationVideoPresentationMapper();
	}

	@Test
	public void shouldMapToViewModel() {
	    // Act
		PresentationVideoViewModel actualViewModel = this.mapper.mapToViewModel(createPresentationVideo());

	    // Assert
	    PresentationVideoViewModel expectedViewModel = createPresentationVideoViewModel();

		assertThat(actualViewModel).isEqualTo(expectedViewModel);
	}

	@Test
	public void shouldMapToDomain() {
		// Act
		PresentationVideo actualPresentationVideo = this.mapper.mapToDomain(createPresentationVideoViewModel());

		// Assert
		PresentationVideo expectedPresentationVideo = createPresentationVideo();

		assertThat(actualPresentationVideo).isEqualTo(expectedPresentationVideo);
	}

	private static PresentationVideo createPresentationVideo() {
		return new PresentationVideo(
			new VideoId("id1"),
			"title",
			"description",
			LocalDate.of(2024, 3, 13)
		);
	}

	private static PresentationVideoViewModel createPresentationVideoViewModel() {
		return new PresentationVideoViewModel("id1", "title", "description", "2024-03-13");
	}

}