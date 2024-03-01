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
		PresentationVideoDTO presentationVideoDTO = new PresentationVideoDTO(
			0,
			"https://www.url1.com",
			"title",
			"description",
			"29-02-2024"
		);

	    //Act
		PresentationVideo actualPresentationVideo = this.presentationVideoMapper.mapToDomain(presentationVideoDTO);

	    //Assert
		PresentationVideo expectedPresentationVideo = new PresentationVideo(
			new Id(0),
			new Url("https://www.url1.com"),
			"title",
			"description",
			LocalDate.of(2024, 2, 29)
		);

		assertThat(actualPresentationVideo).isEqualTo(expectedPresentationVideo);
	}

	@Test
	public void shouldMapDomainToDTO() {
		//Arrange
		PresentationVideo presentationVideo = new PresentationVideo(
			new Id(0),
			new Url("https://www.url1.com"),
			"title",
			"description",
			LocalDate.of(2024, 2, 29)
		);

		//Act
		PresentationVideoDTO actualPresentationVideoDTO = this.presentationVideoMapper.mapToDTO(presentationVideo);

		//Assert
		PresentationVideoDTO expectedPresentationVideoDTO = new PresentationVideoDTO(
			0,
			"https://www.url1.com",
			"title",
			"description",
			"29-02-2024"
		);

		assertThat(actualPresentationVideoDTO).isEqualTo(expectedPresentationVideoDTO);
	}

}