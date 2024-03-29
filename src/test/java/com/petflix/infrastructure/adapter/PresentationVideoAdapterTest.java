package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dao.PresentationVideoDao;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.infrastructure.mapper.PresentationVideoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PresentationVideoAdapterTest {

	private PresentationVideoAdapter presentationVideoAdapter;

	@Mock
	private PresentationVideoDao presentationVideoDao;

	@Mock
	private AnimalDao animalDao;

	@Mock
	private PresentationVideoMapper presentationVideoMapper;

	@BeforeEach
	public void setUp() {
		this.presentationVideoAdapter = new PresentationVideoAdapter(
			this.presentationVideoDao,
			this.animalDao,
			this.presentationVideoMapper
		);
	}

	@Test
	public void shouldReturnPresentationVideoById() {
		// Arrange
		PresentationVideoDTO presentationVideoDTO = createPresentationVideoDTOs().get(0);

		when(this.presentationVideoDao.getPresentationVideoById("0")).thenReturn(List.of(presentationVideoDTO));
		when(this.presentationVideoMapper.mapToDomain(presentationVideoDTO)).thenReturn(createPresentationVideos().get(0));

		// Act
		PresentationVideo actualPresentationVideo = this.presentationVideoAdapter.getPresentationVideoById("0");

		// Assert
		PresentationVideo expectedPresentationVideo = createPresentationVideos().get(0);

		assertThat(actualPresentationVideo).isEqualTo(expectedPresentationVideo);
	}

	@Test
	public void shouldReturnPresentationVideosWithAnimalTypeFilter() {
		// Arrange
		List<AnimalDTO> animalDTOs = createAnimalsDTO();

		when(this.animalDao.getAnimalsByTypeAndMemberCity("chat", "Valenciennes")).thenReturn(animalDTOs);
		when(this.presentationVideoDao.getPresentationVideosByIds(Set.of("id1", "id2"))).thenReturn(createPresentationVideoDTOs());
		when(this.presentationVideoMapper.mapAllToDomain(createPresentationVideoDTOs())).thenReturn(createPresentationVideos());

		// Act
		List<PresentationVideo> actualPresentationVideos = this.presentationVideoAdapter.getPresentationVideosWithFilter("chat", "Valenciennes");

		// Assert
		List<PresentationVideo> expectedPresentationVideos = createPresentationVideos();

		assertThat(actualPresentationVideos).isEqualTo(expectedPresentationVideos);
	}

	@Test
	public void shouldReturnActionSuccess() {
		// Arrange
		PresentationVideoDTO presentationVideoDTO = createPresentationVideoDTOs().get(0);
		PresentationVideo presentationVideo = createPresentationVideos().get(0);

		when(this.presentationVideoMapper.mapToDTO(presentationVideo)).thenReturn(presentationVideoDTO);
		when(this.presentationVideoDao.submitPresentationDTO(presentationVideoDTO)).thenReturn(new ActionSuccess(true));

		// Act
		ActionSuccess actualActionSuccess = this.presentationVideoAdapter.submitPresentationVideo(presentationVideo);

		// Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	private static List<AnimalDTO> createAnimalsDTO() {
		return List.of(
			new AnimalDTO(0, "Oslo", "chat", 3, "id1", 0, "2024-03-08"),
			new AnimalDTO(1, "Uta", "chat", 1, "id2", 0, "2024-03-08")
		);
	}

	private static List<PresentationVideoDTO> createPresentationVideoDTOs() {
		return List.of(
			new PresentationVideoDTO(
				"0",
				"title",
				"description",
				"2024-03-08"
			),
			new PresentationVideoDTO(
				"1",
				"title",
				"description",
				"2024-03-08"
			)
		);
	}

	private static List<PresentationVideo> createPresentationVideos() {
		return List.of(
			new PresentationVideo(
				new VideoId("0"),
				"title",
				"description",
				LocalDate.of(2024, 3, 1)
			),
			new PresentationVideo(
				new VideoId("1"),
				"title",
				"description",
				LocalDate.of(2024, 3, 1)
			)
		);
	}

}