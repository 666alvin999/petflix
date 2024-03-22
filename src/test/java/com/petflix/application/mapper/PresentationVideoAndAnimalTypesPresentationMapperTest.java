package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PresentationVideoAndAnimalTypesPresentationMapperTest {

	private PresentationVideoAndAnimalTypesPresentationMapper mapper;

	@Mock
	private PresentationVideoPresentationMapper presentationVideoPresentationMapper;

	@BeforeEach
	public void setUp() {
		this.mapper = new PresentationVideoAndAnimalTypesPresentationMapper(this.presentationVideoPresentationMapper);
	}

	@Test
	public void shouldMapToViewModel() {
		// Arrange
		PresentationVideo presentationVideo = createPresentationVideos().get(0);
		List<AnimalType> animalTypes = createAnimalTypesList().get(new VideoId("id1"));

		when(this.presentationVideoPresentationMapper.mapToViewModel(createPresentationVideos().get(0))).thenReturn(createPresentationVideoViewModels().get(0));

		// Act
		PresentationVideoAndAnimalTypesViewModel actualViewModel = this.mapper.mapToViewModel(presentationVideo, animalTypes);

		// Assert
		PresentationVideoAndAnimalTypesViewModel expectedViewModel = createViewModels().get(0);

		assertThat(actualViewModel).isEqualTo(expectedViewModel);
	}

	@Test
	public void shouldmapAllToViewModels() {
		// Arrange
		List<PresentationVideo> presentationVideos = createPresentationVideos();
		Map<VideoId, List<AnimalType>> animalTypesByPresentationVideoId = createAnimalTypesList();

		when(this.presentationVideoPresentationMapper.mapToViewModel(createPresentationVideos().get(0))).thenReturn(createPresentationVideoViewModels().get(0));
		when(this.presentationVideoPresentationMapper.mapToViewModel(createPresentationVideos().get(1))).thenReturn(createPresentationVideoViewModels().get(1));

		// Act
		List<PresentationVideoAndAnimalTypesViewModel> actualViewModels = this.mapper.mapAllToViewModels(presentationVideos, animalTypesByPresentationVideoId);

		// Assert
		List<PresentationVideoAndAnimalTypesViewModel> expectedViewModels = createViewModels();

		assertThat(actualViewModels).isEqualTo(expectedViewModels);
	}

	private static List<PresentationVideoAndAnimalTypesViewModel> createViewModels() {
		return List.of(
			new PresentationVideoAndAnimalTypesViewModel(
				createPresentationVideoViewModels().get(0),
				List.of("chien", "chat")
			),
			new PresentationVideoAndAnimalTypesViewModel(
				createPresentationVideoViewModels().get(1),
				List.of("chien", "chat")
			)
		);
	}

	private static List<PresentationVideoViewModel> createPresentationVideoViewModels() {
		return List.of(
			new PresentationVideoViewModel("id1", "title", "description", "2024-03-11"),
			new PresentationVideoViewModel("id2", "title", "description", "2024-03-11")
		);
	}

	private static List<PresentationVideo> createPresentationVideos() {
		return List.of(
			new PresentationVideo(
				new VideoId("id1"),
				"title",
				"description",
				LocalDate.of(2024, 3, 11)
			),
			new PresentationVideo(
				new VideoId("id2"),
				"title",
				"description",
				LocalDate.of(2024, 3, 11)
			)
		);
	}

	private static Map<VideoId, List<AnimalType>> createAnimalTypesList() {
		return Map.of(
			new VideoId("id1"),
			List.of(
				new AnimalType("chien"),
				new AnimalType("chat")
			),
			new VideoId("id2"),
			List.of(
				new AnimalType("chien"),
				new AnimalType("chat")
			)
		);
	}

}