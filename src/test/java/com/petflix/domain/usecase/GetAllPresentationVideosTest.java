package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.port.PresentationVideoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPresentationVideosTest {

	private GetAllPresentationVideos getAllPresentationVideos;

	@Mock
	PresentationVideoPort videoAdapter;

	@BeforeEach
	public void setUp() {
		this.getAllPresentationVideos = new GetAllPresentationVideos(videoAdapter);
	}

	@Test
	public void shouldReturnPresentationVideos() {
	    //Arrange
		List<PresentationVideo> presentationVideos = List.of(
			new PresentationVideo(new Id(1), new Url("https://www.url1.com/"), "title1", "description1", new Date(2024, Calendar.FEBRUARY, 27)),
			new PresentationVideo(new Id(2), new Url("https://www.url2.com"), "title2", "description2", new Date(2024, Calendar.FEBRUARY, 27)),
			new PresentationVideo(new Id(3), new Url("https://www.url3.com"), "title3", "description3", new Date(2024, Calendar.FEBRUARY, 27)),
			new PresentationVideo(new Id(4), new Url("https://www.url4.com"), "title4", "description4", new Date(2024, Calendar.FEBRUARY, 27))
		);

		when(videoAdapter.getAllPresentationVideos()).thenReturn(presentationVideos);

	    //Act
		List<PresentationVideo> actualPresentationVideos = getAllPresentationVideos.execute();

	    //Assert
		List<PresentationVideo> expectedPresentationVideos = List.of(
			new PresentationVideo(new Id(1), new Url("https://www.url1.com/"), "title1", "description1", new Date(2024, Calendar.FEBRUARY, 27)),
			new PresentationVideo(new Id(2), new Url("https://www.url2.com"), "title2", "description2", new Date(2024, Calendar.FEBRUARY, 27)),
			new PresentationVideo(new Id(3), new Url("https://www.url3.com"), "title3", "description3", new Date(2024, Calendar.FEBRUARY, 27)),
			new PresentationVideo(new Id(4), new Url("https://www.url4.com"), "title4", "description4", new Date(2024, Calendar.FEBRUARY, 27))
		);

		assertThat(actualPresentationVideos).isEqualTo(expectedPresentationVideos);
	}

}