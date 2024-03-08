package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class PresentationVideoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL_PRESENTATION_VIDEOS = "SELECT * FROM VIDEO;";
	private final String GET_PRESENTATION_VIDEO_BY_ID = "SELECT * FROM VIDEO WHERE ID = :id;";
	private final String GET_PRESENTATION_VIDEOS_BY_URLS = "SELECT * FROM VIDEO WHERE URL IN (:urls);";

	private final String INSERT = "INSERT INTO VIDEO VALUES (:id, :url, :title, :description, :postingDate);";

	public PresentationVideoDao() {
	}

	public PresentationVideoDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PresentationVideoDTO> getPresentationVideoById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return this.jdbcTemplate.query(GET_PRESENTATION_VIDEO_BY_ID, parameters, new BeanPropertyRowMapper<>(PresentationVideoDTO.class));
	}

	public List<PresentationVideoDTO> getAllPresentationVideos() {
		return this.jdbcTemplate.query(GET_ALL_PRESENTATION_VIDEOS, new BeanPropertyRowMapper<>(PresentationVideoDTO.class));
	}

	public List<PresentationVideoDTO> getPresentationVideosByUrls(Set<String> urls) {
		Map<String, Set<String>> parameters = Map.of("urls", urls);

		return this.jdbcTemplate.query(GET_PRESENTATION_VIDEOS_BY_URLS, parameters, new BeanPropertyRowMapper<>(PresentationVideoDTO.class));
	}

	public ActionSuccess submitPresentationDTO(PresentationVideoDTO presentationVideoDTO) {
		Map<String, Object> parameters = Map.of(
			"id", presentationVideoDTO.getId(),
			"url", presentationVideoDTO.getUrl(),
			"title", presentationVideoDTO.getTitle(),
			"description", presentationVideoDTO.getDescription(),
			"postingDate", presentationVideoDTO.getPostingDate()
		);

		try {
			jdbcTemplate.update(INSERT, parameters);

			return new ActionSuccess(true);
		} catch (DataAccessException e) {
			return new ActionSuccess(false, Optional.ofNullable(e.getMessage()));
		}
	}

}
