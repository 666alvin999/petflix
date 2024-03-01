package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.MemberDTO;
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
public class MemberDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String MEMBER_BY_ID = "SELECT * FROM MEMBER WHERE ID = :id;";

	public MemberDao() {
	}

	public MemberDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MemberDTO> getMemberById(int id) {
		Map<String, Integer> parameters = Map.of("id", id);

		return jdbcTemplate.query(MEMBER_BY_ID, parameters, new BeanPropertyRowMapper<>(MemberDTO.class));
	}

}
