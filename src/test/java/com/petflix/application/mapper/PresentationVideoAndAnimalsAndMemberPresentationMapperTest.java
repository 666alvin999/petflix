package com.petflix.application.mapper;

import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationVideoAndAnimalsAndMemberPresentationMapperTest {

	private PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper;

	@BeforeEach
	public void setUp() {
		this.presentationVideoAndAnimalsAndMemberPresentationMapper = new PresentationVideoAndAnimalsAndMemberPresentationMapper();
	}

	@Test
	public void shouldMapToViewModel() {
		// Act
		PresentationVideoAndAnimalsAndMemberViewModel actualPresentationVideoAndAnimalsAndMemberViewModel = this.presentationVideoAndAnimalsAndMemberPresentationMapper.mapToViewModel(createPresentationVideo(), createAnimals());

		// Assert
		PresentationVideoAndAnimalsAndMemberViewModel expectedPresentationVideoAndAnimalsAndMemberViewModel = createViewModel();

		assertThat(actualPresentationVideoAndAnimalsAndMemberViewModel).isEqualTo(expectedPresentationVideoAndAnimalsAndMemberViewModel);
	}

	private static PresentationVideoAndAnimalsAndMemberViewModel createViewModel() {
		return new PresentationVideoAndAnimalsAndMemberViewModel(
			new PresentationVideoViewModel(
				"id1",
				"title",
				"description",
				"2024-03-13"
			),
			List.of(
				new AnimalViewModel("Oslo", "chat", 3, "id1", "2024-03-13", false),
				new AnimalViewModel("Uta", "chat", 1, "id1", "2024-03-13", false)
			),
			new MemberViewModel(0, "Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX")
		);
	}

	private static PresentationVideo createPresentationVideo() {
		return new PresentationVideo(
			new VideoId("id1"),
			"title",
			"description",
			LocalDate.of(2024, 3, 13)
		);
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