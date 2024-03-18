package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.ControlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ControlDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM CONTROL;";

	public ControlDao() {
	}

	@Autowired
	public ControlDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ControlDTO> getAllControls() {
		return this.jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(ControlDTO.class));
	}

}
