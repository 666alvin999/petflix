package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.port.PresentationVideoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubmitPresentationVideoTest {

	private SubmitPresentationVideo submitPresentationVideo;

	@Mock
	private PresentationVideoPort videoAdapter;

	@BeforeEach
	public void setUp() {
		this.submitPresentationVideo = new SubmitPresentationVideo(videoAdapter);
	}

	@Test
	public void shouldReturnActionSuccess() {
	    //Arrange
		PresentationVideo presentationVideo = new PresentationVideo(new Id(1), new Url("https://www.url1.com/"), "title1", "description1", new Date(2024, Calendar.FEBRUARY, 27));

		when(this.videoAdapter.submitPresentationVideo(presentationVideo)).thenReturn(new ActionSuccess(true));

	    //Act
	    ActionSuccess actionSuccess = this.submitPresentationVideo.execute(presentationVideo);

	    //Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actionSuccess).isEqualTo(expectedActionSuccess);
	}

}