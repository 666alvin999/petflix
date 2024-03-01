package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.MemberDTO;
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
public class MemberDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_BY_ID = "SELECT * FROM MEMBER WHERE ID = :id;";
	private final String GET_ALL_CITIES = "SELECT CITY FROM MEMBER";

	public MemberDao() {
	}

	public MemberDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MemberDTO> getMemberById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return jdbcTemplate.query(GET_BY_ID, parameters, new BeanPropertyRowMapper<>(MemberDTO.class));
	}

	public List<String> getAllCities() {
		return this.jdbcTemplate.queryForList(GET_ALL_CITIES, new HashMap<>(), String.class);
	}

}
