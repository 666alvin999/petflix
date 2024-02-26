package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
	public void shouldReturnPresentationVideo() {
		//Act
		List<PresentationVideoDTO> presentationVideo = this.presentationVideoDao.getPresentationVideoById(1);

		//Assert
		PresentationVideoDTO expectedPresentationVideo = new PresentationVideoDTO(1, "https://www.url1.com", "title1", "description1", "26-02-2024");

		assertThat(presentationVideo).isEqualTo(List.of(expectedPresentationVideo));
	}

	@SneakyThrows
	private void initTables() {
		jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/video_init.sql"))),
			new HashMap<>()
		);
	}

}