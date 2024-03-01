package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_BY_ID = "SELECT * FROM ANIMAL WHERE ID = :id;";
	private final String GET_BY_PRESENTATION_VIDEO_URL = "SELECT * FROM ANIMAL WHERE PRESENTATION_VIDEO_URL = :url;";
	private final String GET_ALL_TYPES = "SELECT DISTINCT TYPE FROM ANIMAL;";
	private final String GET_BY_TYPE = "SELECT * FROM ANIMAL WHERE TYPE = :animalType";

	public AnimalDao() {
	}

	public AnimalDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<AnimalDTO> getAnimalById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return this.jdbcTemplate.query(GET_BY_ID, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}

	public List<AnimalDTO> getAnimalsByPresentationVideoUrl(String url) {
		Map<String, String> parameters = Map.of("url", url);

		return this.jdbcTemplate.query(GET_BY_PRESENTATION_VIDEO_URL, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}

	public List<String> getAllTypes() {
		return this.jdbcTemplate.queryForList(GET_ALL_TYPES, new HashMap<>(), String.class);
	}

	public List<AnimalDTO> getAnimalsByType(String animalType) {
		Map<String, String> parameters = Map.of("animalType", animalType);

		return this.jdbcTemplate.query(GET_BY_TYPE, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}
}
