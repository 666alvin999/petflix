package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.AnimalTypesByPresentationVideoIdDTO;
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
public class AnimalDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private final String GET_BY_IDS = "SELECT * FROM ANIMAL WHERE ID IN (:ids);";
	private final String GET_BY_PRESENTATION_VIDEO_ID = "SELECT * FROM ANIMAL WHERE PRESENTATION_VIDEO_ID = :id;";
	private final String GET_ALL_TYPES = "SELECT DISTINCT TYPE FROM ANIMAL;";
	private final String GET_BY_TYPE_AND_CITY_BASE = "SELECT * FROM ANIMAL";
	private final String GET_TYPE_GROUP_BY_PRESENTATION_VIDEO_ID = "SELECT PRESENTATION_VIDEO_ID, GROUP_CONCAT(DISTINCT TYPE) as ANIMAL_TYPES FROM ANIMAL WHERE PRESENTATION_VIDEO_ID IN (:ids) GROUP BY PRESENTATION_VIDEO_ID";
	private final String INSERT_BASE = "INSERT INTO ANIMAL ";

	public AnimalDao() {
	}

	@Autowired
	public AnimalDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<AnimalDTO> getAnimalsByIds(Set<Integer> ids) {
		Map<String, Set<Integer>> parameters = Map.of("ids", ids);

		return this.jdbcTemplate.query(GET_BY_IDS, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}

	public List<AnimalDTO> getAnimalsByPresentationVideoId(String id) {
		Map<String, String> parameters = Map.of("id", id);

		return this.jdbcTemplate.query(GET_BY_PRESENTATION_VIDEO_ID, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}

	public List<String> getAllTypes() {
		return this.jdbcTemplate.queryForList(GET_ALL_TYPES, new HashMap<>(), String.class);
	}

	public List<AnimalDTO> getAnimalsByTypeAndMemberCity(String type, String city) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<String, String> parameters = new HashMap<>();

		if (nonNull(type) || nonNull(city)) {
			stringBuilder.append(" WHERE ");
		}

		if (nonNull(type)) {
			parameters.put("type", type);
			stringBuilder.append("TYPE = :type");

			if (nonNull(city)) {
				stringBuilder.append(" AND ");
			}
		}

		if (nonNull(city)) {
			parameters.put("city", city);
			stringBuilder.append("MANAGING_MEMBER IN (SELECT ID FROM MEMBER WHERE CITY = :city)");
		}

		String sqlQuery = GET_BY_TYPE_AND_CITY_BASE + stringBuilder + ";";

		return this.jdbcTemplate.query(sqlQuery, parameters, new BeanPropertyRowMapper<>(AnimalDTO.class));
	}

	public List<AnimalTypesByPresentationVideoIdDTO> getAnimalTypesGroupByPresentationVideoIds(Set<String> ids) {
		Map<String, Set<String>> parameters = Map.of("ids", ids);

		return this.jdbcTemplate.query(GET_TYPE_GROUP_BY_PRESENTATION_VIDEO_ID, parameters, new BeanPropertyRowMapper<>(AnimalTypesByPresentationVideoIdDTO.class));
	}

	public ActionSuccess submitAnimals(List<AnimalDTO> animalDTOs) {
		Map<String, Object> parameters = new HashMap<>();
		StringBuilder builder = new StringBuilder(INSERT_BASE);

		builder.append("(");

		if (animalDTOs.stream().anyMatch(animalDTO -> nonNull(animalDTO.getId()))) {
			builder.append("ID, ");
		}

		builder.append("NAME, TYPE, AGE, MANAGING_MEMBER, PRESENTATION_VIDEO_ID, ARRIVAL_DATE) VALUES ");

		animalDTOs.forEach(animalDTO -> {
			int animalIndex = animalDTOs.indexOf(animalDTO);

			builder.append("(");

			if (nonNull(animalDTO.getId())) {
				parameters.put("id" + animalIndex, animalDTO.getId());
				builder.append(":id").append(animalIndex).append(",");
			}

			parameters.put("name" + animalIndex, animalDTO.getName());
			parameters.put("type" + animalIndex, animalDTO.getType());
			parameters.put("age" + animalIndex, animalDTO.getAge());
			parameters.put("managingMember" + animalIndex, animalDTO.getManagingMember());
			parameters.put("presentationVideoId" + animalIndex, animalDTO.getPresentationVideoId());
			parameters.put("arrivalDate" + animalIndex, animalDTO.getArrivalDate());

			builder
				.append(":name").append(animalIndex)
				.append(", :type").append(animalIndex)
				.append(", :age").append(animalIndex)
				.append(", :managingMember").append(animalIndex)
				.append(", :presentationVideoId").append(animalIndex)
				.append(", :arrivalDate").append(animalIndex);

			if (animalIndex != (animalDTOs.size() - 1)) {
				builder.append("), ");
			} else {
				builder.append(");");
			}
		});

		try {
			this.jdbcTemplate.update(builder.toString(), parameters);

			return new ActionSuccess(true);
		} catch (DataAccessException e) {
			return new ActionSuccess(false, Optional.of(e.getMessage()));
		}
	}

}
