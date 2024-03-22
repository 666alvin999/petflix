package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.AdopterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

import static java.util.Objects.nonNull;

@Component
public class AdopterDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM ADOPTER;";
	private final String GET_ADOPTERS_BY_IDS = "SELECT * FROM ADOPTER WHERE ID IN (:ids);";
	private final String INSERT_BASE = "INSERT INTO ADOPTER ";

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

	public ActionSuccess createAdopter(AdopterDTO adopter) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("firstName", adopter.getFirstName());
		parameters.put("lastName", adopter.getLastName());
		parameters.put("address", adopter.getAddress());
		parameters.put("mail", adopter.getMail());


		StringBuilder builder = new StringBuilder(INSERT_BASE);

		builder.append("(");

		if (nonNull(adopter.getId())) {
			builder.append("ID, ");
			parameters.put("id", adopter.getId());
		}

		builder.append("FIRST_NAME, LAST_NAME, ADDRESS, MAIL) VALUES (");

		if (nonNull(adopter.getId())) {
			builder.append(":id, ");
		}

		builder.append(":firstName, :lastName, :address, :mail);");

		try {
			this.jdbcTemplate.update(builder.toString(), parameters);

			return new ActionSuccess(true);
		} catch (DataAccessException e) {
			return new ActionSuccess(false, Optional.of(e.getMessage()));
		}
	}

}
