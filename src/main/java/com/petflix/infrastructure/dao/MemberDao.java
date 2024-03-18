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
import java.util.Set;

@Component
public class MemberDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM MEMBER";
	private final String GET_BY_IDS = "SELECT * FROM MEMBER WHERE ID IN (:ids);";
	private final String GET_ALL_CITIES = "SELECT DISTINCT CITY FROM MEMBER";

	public MemberDao() {
	}

	@Autowired
	public MemberDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MemberDTO> getAllMembers() {
		return this.jdbcTemplate.query(GET_ALL, new HashMap<>(), new BeanPropertyRowMapper<>(MemberDTO.class));
	}

	public List<MemberDTO> getMembersByIds(Set<Integer> ids) {
		Map<String, Set<Integer>> parameters = Map.of("ids", ids);

		return this.jdbcTemplate.query(GET_BY_IDS, parameters, new BeanPropertyRowMapper<>(MemberDTO.class));
	}

	public List<String> getAllCities() {
		return this.jdbcTemplate.queryForList(GET_ALL_CITIES, new HashMap<>(), String.class);
	}

}
