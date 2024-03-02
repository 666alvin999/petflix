package com.petflix.infrastructure.dao;

import com.petflix.infrastructure.dto.MemberDTO;
import com.petflix.utils.BasicDatabaseExtension;
import com.petflix.utils.EzDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(BasicDatabaseExtension.class)
class MemberDaoTest {

	private MemberDao memberDao;

	@EzDatabase
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		this.memberDao = new MemberDao();
		setField(this.memberDao, "jdbcTemplate", this.jdbcTemplate);

		initTables();
	}

	@Test
	public void shouldReturnMemberDTO() {
		//Act
		List<MemberDTO> actualMemberDTO = this.memberDao.getMemberById(0);

		//Assert
		MemberDTO expectedMemberDTO = new MemberDTO(0, "Alvin", "Hamaide", "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");

		assertThat(actualMemberDTO).isEqualTo(List.of(expectedMemberDTO));
	}

	@Test
	public void shouldReturnAllCities() {
		//Act
		List<String> actualCities = this.memberDao.getAllCities();

		//Assert
		List<String> expectedCities = List.of("Lille", "Valenciennes");

		assertThat(actualCities).isEqualTo(expectedCities);
	}

	@SneakyThrows
	private void initTables() {
		jdbcTemplate.update(
			new String(readAllBytes(Paths.get("src/test/resources/member_init.sql"))),
			new HashMap<>()
		);
	}

}