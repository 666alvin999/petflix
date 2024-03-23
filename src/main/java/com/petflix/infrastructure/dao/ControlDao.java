package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.ControlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ControlDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM CONTROL;";

	private final String INSERT = "INSERT INTO CONTROL VALUES (:animalId, :controlDate);";

	public ControlDao() {
	}

	@Autowired
	public ControlDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ControlDTO> getAllControls() {
		return this.jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(ControlDTO.class));
	}

	public ActionSuccess createControl(ControlDTO controlDTO) {
		Map<String, Object> parameters = Map.of(
			"animalId", controlDTO.getAnimalId(),
			"controlDate", controlDTO.getControlDate()
		);

		try {
			this.jdbcTemplate.update(INSERT, parameters);

			return new ActionSuccess(true);
		} catch(DataAccessException e) {
			return new ActionSuccess(false, Optional.of(e.getMessage()));
		}
	}

}
