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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPresentationVideoByIdTest {

	private GetPresentationVideoById getPresentationVideoById;

	@Mock
	private PresentationVideoPort presentationVideoPort;

	@BeforeEach
	public void setUp() {
		this.getPresentationVideoById = new GetPresentationVideoById(presentationVideoPort);
	}

	@Test
	void shouldReturnVideoWithGoodId() {
		// Arrange
		PresentationVideo presentationVideo = new PresentationVideo(new VideoId("1"), "title1", "description1", LocalDate.of(2024, 2, 29));

		when(this.presentationVideoPort.getPresentationVideoById("1")).thenReturn(presentationVideo);

		// Act
		PresentationVideo actualPresentationVideo = this.getPresentationVideoById.execute("1");

		// Assert
		PresentationVideo expectedPresentationVideo = new PresentationVideo(new VideoId("1"), "title1", "description1", LocalDate.of(2024, 2, 29));

		assertThat(actualPresentationVideo).isEqualTo(expectedPresentationVideo);
	}

}