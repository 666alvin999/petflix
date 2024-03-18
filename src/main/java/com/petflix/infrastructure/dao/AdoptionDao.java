package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AdoptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AdoptionDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_BY_IDS = "SELECT * FROM ADOPTION WHERE ANIMAL_ID IN (:ids);";

	public AdoptionDao() {
	}

	@Autowired
	public AdoptionDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<AdoptionDTO> getAdoptionsByIds(Set<Integer> ids) {
		Map<String, Set<Integer>> parameters = Map.of("ids", ids);

		return this.jdbcTemplate.query(GET_BY_IDS, parameters, new BeanPropertyRowMapper<>(AdoptionDTO.class));
	}

}
