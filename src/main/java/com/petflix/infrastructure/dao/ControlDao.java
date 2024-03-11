package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.ControlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class ControlDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM CONTROL;";
	private final String GET_BY_ID = "SELECT * FROM CONTROL WHERE ANIMAL_ID = :id;";

	public ControlDao() {
	}

	public ControlDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ControlDTO> getControlById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return this.jdbcTemplate.query(GET_BY_ID, parameters, new BeanPropertyRowMapper<>(ControlDTO.class));
	}

	public List<ControlDTO> getAllControls() {
		return this.jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(ControlDTO.class));
	}

}
