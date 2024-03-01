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

import static java.util.Objects.nonNull;

@Component
public class AnimalDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_BY_ID = "SELECT * FROM ANIMAL WHERE ID = :id;";
	private final String GET_BY_PRESENTATION_VIDEO_URL = "SELECT * FROM ANIMAL WHERE PRESENTATION_VIDEO_URL = :url;";
	private final String GET_ALL_TYPES = "SELECT DISTINCT TYPE FROM ANIMAL;";
	private final String GET_BY_TYPE_AND_CITY_BASE = "SELECT * FROM ANIMAL";

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

	public List<AnimalDTO> getAnimalsByTypeAndMemberCity(String type, String city) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<String, String> parameters = new HashMap<>();

		if (nonNull(type) || nonNull(city)) {
			stringBuilder.append(" WHERE ");
		}

		if (nonNull(type)) {
			parameters.put("type", type);
			stringBuilder.append("TYPE = :type");

			if (nonNull(city)) {
				stringBuilder.append(" AND ");
			}
		}

		if (nonNull(city)) {
			parameters.put("city", city);
			stringBuilder.append("MANAGING_MEMBER IN (SELECT ID FROM MEMBER WHERE CITY = :city)");
		}

		String sqlQuery = GET_BY_TYPE_AND_CITY_BASE + stringBuilder + ";";

		return this.jdbcTemplate.query(sqlQuery, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}
}
