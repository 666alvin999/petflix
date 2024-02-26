package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.PresentationVideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class PresentationVideoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String PRESENTATION_VIDEO_BY_ID = "SELECT * FROM VIDEO WHERE ID = :id;";

	public PresentationVideoDao() {
	}

	public PresentationVideoDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PresentationVideoDTO> getPresentationVideoById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return jdbcTemplate.query(PRESENTATION_VIDEO_BY_ID, parameters, new BeanPropertyRowMapper<>(PresentationVideoDTO.class));
	}
}
