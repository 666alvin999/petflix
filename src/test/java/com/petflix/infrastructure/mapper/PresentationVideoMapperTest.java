package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PresentationVideoMapperTest {

	private PresentationVideoMapper presentationVideoMapper;

	@BeforeEach
	public void setUp() {
		this.presentationVideoMapper = new PresentationVideoMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
	    //Arrange
		PresentationVideoDTO presentationVideoDTO = createPresentationVideoDTOs().get(0);

	    //Act
		PresentationVideo actualPresentationVideo = this.presentationVideoMapper.mapToDomain(presentationVideoDTO);

	    //Assert
		PresentationVideo expectedPresentationVideo = createPresentationVideos().get(0);

		assertThat(actualPresentationVideo).isEqualTo(expectedPresentationVideo);
	}

	@Test
	public void shouldMapAllDtosToDomain() {
		//Arrange
		List<PresentationVideoDTO> presentationVideoDTOs = createPresentationVideoDTOs();

		//Act
		List<PresentationVideo> actualPresentationVideos = this.presentationVideoMapper.mapAllToDomain(presentationVideoDTOs);

		//Assert
		List<PresentationVideo> expectedPresentationVideos = createPresentationVideos();

		assertThat(actualPresentationVideos).isEqualTo(expectedPresentationVideos);
	}

	@Test
	public void shouldMapDomainToDTO() {
		//Arrange
		PresentationVideo presentationVideos = createPresentationVideos().get(0);

		//Act
		PresentationVideoDTO actualPresentationVideoDTO = this.presentationVideoMapper.mapToDTO(presentationVideos);

		//Assert
		PresentationVideoDTO expectedPresentationVideoDTO = createPresentationVideoDTOs().get(0);

		assertThat(actualPresentationVideoDTO).isEqualTo(expectedPresentationVideoDTO);
	}

	@Test
	public void shouldMapAllDomainToDTO() {
		//Arrange
		List<PresentationVideo> presentationVideos = createPresentationVideos();

		//Act
		List<PresentationVideoDTO> actualPresentationVideoDTOs = this.presentationVideoMapper.mapAllToDTO(presentationVideos);

		//Assert
		List<PresentationVideoDTO> expectedPresentationVideoDTOs = createPresentationVideoDTOs();

		assertThat(actualPresentationVideoDTOs).isEqualTo(expectedPresentationVideoDTOs);
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