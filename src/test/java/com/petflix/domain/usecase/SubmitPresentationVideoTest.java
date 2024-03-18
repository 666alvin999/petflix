package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;
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
class SubmitPresentationVideoTest {

	private SubmitPresentationVideo submitPresentationVideo;

	@Mock
	private PresentationVideoPort presentationVideoPort;

	@Mock
	private AnimalPort animalPort;

	@BeforeEach
	public void setUp() {
		this.submitPresentationVideo = new SubmitPresentationVideo(presentationVideoPort, animalPort);
	}

	@Test
	public void shouldReturnActionSuccess() {
		// Arrange
		PresentationVideo presentationVideo = createPresentationVideo();
		List<Animal> animals = createAnimals();

		when(this.presentationVideoPort.submitPresentationVideo(presentationVideo)).thenReturn(new ActionSuccess(true));
		when(this.animalPort.submitAnimals(animals)).thenReturn(new ActionSuccess(true));

		// Act
		ActionSuccess actionSuccess = this.submitPresentationVideo.execute(presentationVideo, animals);

		// Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static PresentationVideo createPresentationVideo() {
		return new PresentationVideo(new VideoId("1"), "title1", "description1", LocalDate.of(2024, 2, 29));
	}

	private static List<Animal> createAnimals() {
		Member member = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			new MemberCity("Valenciennes"),
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		return List.of(
			new Animal(
				new Id(0),
				"Oslo",
				new AnimalType("chat"),
				3,
				new VideoId("id1"),
				member,
				LocalDate.of(2024, 3, 13),
				false
			),
			new Animal(
				new Id(1),
				"Uta",
				new AnimalType("chat"),
				1,
				new VideoId("id1"),
				member,
				LocalDate.of(2024, 3, 13),
				false
			)
		);
	}

}