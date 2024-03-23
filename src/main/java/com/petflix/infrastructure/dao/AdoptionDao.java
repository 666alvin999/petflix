package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.AdoptionDTO;
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
import java.util.Set;

@Component
public class AdoptionDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_BY_IDS = "SELECT * FROM ADOPTION WHERE ANIMAL_ID IN (:ids);";

	private final String INSERT = "INSERT INTO ADOPTION VALUES (:animalId, :adopterId, :adoptionDate);";

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

	public ActionSuccess createAdoption(AdoptionDTO adoptionDTO) {
		Map<String, Object> parameters = Map.of(
			"animalId", adoptionDTO.getAnimalId(),
			"adopterId", adoptionDTO.getAdopterId(),
			"adoptionDate", adoptionDTO.getAdoptionDate()
		);

		try {
			this.jdbcTemplate.update(INSERT, parameters);

			return new ActionSuccess(true);
		} catch (DataAccessException e) {
			return new ActionSuccess(false, Optional.of(e.getMessage()));
		}
	}

}
