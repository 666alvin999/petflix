package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPresentationVideosTest {

	private GetAllPresentationVideos getAllPresentationVideos;

	@Mock
	PresentationVideoAdapter videoAdapter;

	@BeforeEach
	public void setUp() {
		this.getAllPresentationVideos = new GetAllPresentationVideos(videoAdapter);
	}

	@Test
	public void shouldReturnPresentationVideos() {
	    //Arrange
		List<PresentationVideo> presentationVideos = List.of(
			new PresentationVideo("https://www.url1.com", "title1", "description1"),
			new PresentationVideo("https://www.url2.com", "title2", "description2"),
			new PresentationVideo("https://www.url3.com", "title3", "description3"),
			new PresentationVideo("https://www.url4.com", "title4", "description4")
		);

		when(videoAdapter.getAllPresentationVideos()).thenReturn(presentationVideos);

	    //Act
		List<PresentationVideo> actualPresentationVideos = getAllPresentationVideos.execute();

	    //Assert
		List<PresentationVideo> expectedPresentationVideos = List.of(
			new PresentationVideo("https://www.url1.com", "title1", "description1"),
			new PresentationVideo("https://www.url2.com", "title2", "description2"),
			new PresentationVideo("https://www.url3.com", "title3", "description3"),
			new PresentationVideo("https://www.url4.com", "title4", "description4")
		);

		assertThat(actualPresentationVideos).isEqualTo(expectedPresentationVideos);
	}

}