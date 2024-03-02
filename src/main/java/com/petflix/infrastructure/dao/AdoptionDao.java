package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AdoptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class AdoptionDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL_ADOPTIONS = "SELECT * FROM ADOPTION;";

	public AdoptionDao() {
	}

	@Autowired
	public AdoptionDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<AdoptionDTO> getAllAdoptions() {
		return this.jdbcTemplate.query(GET_ALL_ADOPTIONS, new BeanPropertyRowMapper<>(AdoptionDTO.class));
	}

}
