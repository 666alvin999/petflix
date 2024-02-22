package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubmitPresentationVideoTest {

	private SubmitPresentationVideo submitPresentationVideo;

	@Mock
	private PresentationVideoAdapter videoAdapter;

	@BeforeEach
	public void setUp() {
		this.submitPresentationVideo = new SubmitPresentationVideo(videoAdapter);
	}

	@Test
	public void shouldReturnActionSuccess() {
	    //Arrange
		PresentationVideo presentationVideo = new PresentationVideo(1, "https://www.url1.com/", "title1", "description1");

		when(this.videoAdapter.submitPresentationVideo(presentationVideo)).thenReturn(new ActionSuccess(true));

	    //Act
	    ActionSuccess actionSuccess = this.submitPresentationVideo.execute(presentationVideo);

	    //Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actionSuccess).isEqualTo(expectedActionSuccess);
	}

}