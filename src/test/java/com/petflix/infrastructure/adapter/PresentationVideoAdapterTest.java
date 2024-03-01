package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dao.MemberDao;
import com.petflix.infrastructure.dao.PresentationVideoDao;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.infrastructure.mapper.PresentationVideoMapper;
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
	public void shouldReturnAllPresentationVideos() {
		//Arrange
		List<PresentationVideoDTO> presentationVideoDTOs = this.createPresentationVideoDTOs();

		when(this.presentationVideoDao.getAllPresentationVideos()).thenReturn(presentationVideoDTOs);
		when(this.presentationVideoMapper.mapAllToDomain(presentationVideoDTOs)).thenReturn(this.createPresentationVideos());

		//Act
		List<PresentationVideo> actualPresentationVideos = this.presentationVideoAdapter.getAllPresentationVideos();

		//Assert
		List<PresentationVideo> expectedPresentationVideos = this.createPresentationVideos();

		assertThat(actualPresentationVideos).isEqualTo(expectedPresentationVideos);
	}

	@Test
	public void shouldReturnPresentationVideoById() {
		//Arrange
		PresentationVideoDTO presentationVideoDTO = this.createPresentationVideoDTOs().get(0);

		when(this.presentationVideoDao.getPresentationVideoById(0)).thenReturn(List.of(presentationVideoDTO));
		when(this.presentationVideoMapper.mapToDomain(presentationVideoDTO)).thenReturn(this.createPresentationVideos().get(0));

		//Act
		PresentationVideo actualPresentationVideo = this.presentationVideoAdapter.getPresentationVideoById(0);

		//Assert
		PresentationVideo expectedPresentationVideo = this.createPresentationVideos().get(0);

		assertThat(actualPresentationVideo).isEqualTo(expectedPresentationVideo);
	}

	@Test
	public void shouldReturnPresentationVideosWithFilter() {
	    //Arrange

	    //Act

	    //Assert

	}

	private List<PresentationVideoDTO> createPresentationVideoDTOs() {
		return List.of(
			new PresentationVideoDTO(
				0,
				"https://www.url1.com",
				"title",
				"description",
				"01-03-2024"
			),
			new PresentationVideoDTO(
				1,
				"https://www.url2.com",
				"title",
				"description",
				"01-03-2024"
			)
		);
	}

	private List<PresentationVideo> createPresentationVideos() {
		return List.of(
			new PresentationVideo(
				new Id(0),
				new Url("https://www.url1.com"),
				"title",
				"description",
				LocalDate.of(2024, 3, 1)
			),
			new PresentationVideo(
				new Id(1),
				new Url("https://www.url2.com"),
				"title",
				"description",
				LocalDate.of(2024, 3, 1)
			)
		);
	}

}