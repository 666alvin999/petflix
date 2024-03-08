package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.infrastructure.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberMapperTest {

	private MemberMapper memberMapper;

	@BeforeEach
	public void setUp() {
		this.memberMapper = new MemberMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		// Arrange
		MemberDTO memberDTO = createMemberDTOs().get(0);

		// Act
		Member actualMember = this.memberMapper.mapToDomain(memberDTO);

		// Assert
		Member expectedMember = createMembers().get(0);

		assertThat(actualMember).isEqualTo(expectedMember);
	}

	@Test
	public void shouldMapAllDtosToDomain() {
		// Arrange
		List<MemberDTO> memberDTOs = createMemberDTOs();

		// Act
		List<Member> actualMembers = this.memberMapper.mapAllToDomain(memberDTOs);

		// Assert
		List<Member> expectedMembers = createMembers();

		assertThat(actualMembers).isEqualTo(expectedMembers);
	}

	@Test
	public void shouldMapDomainToDTO() {
		// Arrange
		Member member = createMembers().get(0);

		// Act
		MemberDTO actualMemberDTO = this.memberMapper.mapToDTO(member);

		// Assert
		MemberDTO expectedMemberDTO = createMemberDTOs().get(0);

		assertThat(actualMemberDTO).isEqualTo(expectedMemberDTO);
	}

	@Test
	public void shouldReturnMemberCities() {
		//Arrange
		List<String> cities = List.of("Valenciennes", "Lille");

		//Act
		List<MemberCity> actualCities = this.memberMapper.mapCities(cities);

		//Assert
		List<MemberCity> expectedMemberCity = List.of(new MemberCity("Valenciennes"), new MemberCity("Lille"));

		assertThat(actualCities).isEqualTo(expectedMemberCity);
	}

	private static List<Member> createMembers() {
		return List.of(
			new Member(
				new Id(0),
				new FirstName("Alvin"),
				new LastName("Hamaide"),
				new MemberCity("Valenciennes"),
				"alvin.hamaide@mail-ecv.fr",
				"06XXXXXXXX"
			),
			new Member(
				new Id(1),
				new FirstName("Citanimal"),
				new LastName("Asso"),
				new MemberCity("Valenciennes"),
				"citanimal@gmail.com",
				"06XXXXXXXX"
			)
		);
	}

	private static List<MemberDTO> createMemberDTOs() {
		return List.of(
			new MemberDTO(
				0,
				"Alvin",
				"Hamaide",
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr",
				"06XXXXXXXX"
			),
			new MemberDTO(
				1,
				"Citanimal",
				"Asso",
				"Valenciennes",
				"citanimal@gmail.com",
				"06XXXXXXXX"
			)
		);
	}

}