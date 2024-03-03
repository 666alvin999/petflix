package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.AdopterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class AdopterDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ADOPTER_BY_ID = "SELECT * FROM ADOPTER WHERE ID = :id;";
	private final String GET_ADOPTERS_BY_IDS = "SELECT * FROM ADOPTER WHERE ID IN (:ids);";

	public AdopterDao() {
	}

	@Autowired
	public AdopterDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<AdopterDTO> getAdopterById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return this.jdbcTemplate.query(GET_ADOPTER_BY_ID, parameters, new BeanPropertyRowMapper<>(AdopterDTO.class));
	}

	public List<AdopterDTO> getAdoptersByIds(List<Integer> ids) {
		Map<String, List<Integer>> parameters = Map.of("ids", ids);

		return this.jdbcTemplate.query(GET_ADOPTERS_BY_IDS, parameters, new BeanPropertyRowMapper<>(AdopterDTO.class));
	}

}
