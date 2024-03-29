package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.PresentationVideoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPresentationVideosWithFilterTest {

	private GetPresentationVideosWithFilter getPresentationVideosWithFilter;

	@Mock
	private PresentationVideoPort presentationVideoPort;

	@BeforeEach
	public void setUp() {
		this.getPresentationVideosWithFilter = new GetPresentationVideosWithFilter(presentationVideoPort);
	}

	@Test
	public void shouldReturnPresentationVideos_whenTypeIsDogAndCityIsParis() {
		// Arrange
		List<PresentationVideo> videos = List.of(new PresentationVideo(new VideoId("1"), "title1", "description1", LocalDate.of(2024, 2, 29)));

		when(this.presentationVideoPort.getPresentationVideosWithFilter("dog", "paris")).thenReturn(videos);

		// Act
		List<PresentationVideo> actualVideos = this.getPresentationVideosWithFilter.execute("dog", "paris");

		// Assert
		List<PresentationVideo> expectedVideos = List.of(new PresentationVideo(new VideoId("1"), "title1", "description1", LocalDate.of(2024, 2, 29)));

		assertThat(actualVideos).isEqualTo(expectedVideos);
	}

}