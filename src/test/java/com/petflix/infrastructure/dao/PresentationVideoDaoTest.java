package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static java.nio.file.Files.readAllBytes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(BasicDatabaseExtension.class)
class PresentationVideoDaoTest {

	private PresentationVideoDao presentationVideoDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.presentationVideoDao = new PresentationVideoDao();
		setField(this.presentationVideoDao, "jdbcTemplate", this.jdbcTemplate);

		initTables();
	}

	@Test
	public void shouldReturnAllPresentationVideoDTOs() {
		//Act
		List<PresentationVideoDTO> presentationVideo = this.presentationVideoDao.getAllPresentationVideos();

		//Assert
		List<PresentationVideoDTO> expectedPresentationVideo = createPresentationVideoDTOs();

		assertThat(presentationVideo).isEqualTo(expectedPresentationVideo);
	}

	@Test
	public void shouldReturnPresentationVideoDTOs() {
		//Act
		List<PresentationVideoDTO> presentationVideo = this.presentationVideoDao.getPresentationVideoById(1);

		//Assert
		PresentationVideoDTO expectedPresentationVideo = createPresentationVideoDTOs().get(0);

		assertThat(presentationVideo).isEqualTo(List.of(expectedPresentationVideo));
	}

	@Test
	public void shouldReturnPresentationVideosWithUrls() {
		//Act
		List<PresentationVideoDTO> actualPresentationVideosDTO = this.presentationVideoDao.getPresentationVideosByUrls(Set.of("https://www.url1.com", "https://www.url2.com"));

		//Assert
		List<PresentationVideoDTO> expectedPresentationVideoDTOs = this.createPresentationVideoDTOs();

		assertThat(actualPresentationVideosDTO).isEqualTo(expectedPresentationVideoDTOs);
	}

	@Test
	public void shouldSubmitPresentationVideoDTO() {
		//Arrange
		PresentationVideoDTO presentationVideoDTO = new PresentationVideoDTO("3", "https://www.url3.com", "title", "description", "2024-03-08");

		//Act
		ActionSuccess actualActionSuccess = this.presentationVideoDao.submitPresentationDTO(presentationVideoDTO);

		//Assert
		ActionSuccess expectedActionSuccess = new ActionSuccess(true);

		assertThat(actualActionSuccess).isEqualTo(expectedActionSuccess);
	}

	@SneakyThrows
	private void initTables() {
		this.jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/video_init.sql"))),
			new HashMap<>()
		);
	}

	private static List<PresentationVideoDTO> createPresentationVideoDTOs() {
		return List.of(
			new PresentationVideoDTO(
				"1",
				"https://www.url1.com",
				"title1",
				"description1",
				"2024-03-08"
			),
			new PresentationVideoDTO(
				"2",
				"https://www.url2.com",
				"title2",
				"description2",
				"2024-03-08"
			)
		);
	}

}