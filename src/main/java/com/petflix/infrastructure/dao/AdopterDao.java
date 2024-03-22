package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AdopterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AdopterDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM ADOPTER;";
	private final String GET_ADOPTERS_BY_IDS = "SELECT * FROM ADOPTER WHERE ID IN (:ids);";

	public AdopterDao() {
	}

	@Autowired
	public AdopterDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<AdopterDTO> getAllAdopters() {
		return this.jdbcTemplate.query(GET_ALL, new HashMap<>(), new BeanPropertyRowMapper<>(AdopterDTO.class));
	}

	public List<AdopterDTO> getAdoptersByIds(Set<Integer> ids) {
		Map<String, Set<Integer>> parameters = Map.of("ids", ids);

		return this.jdbcTemplate.query(GET_ADOPTERS_BY_IDS, parameters, new BeanPropertyRowMapper<>(AdopterDTO.class));
	}

}
