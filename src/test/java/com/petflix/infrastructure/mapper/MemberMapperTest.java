package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.infrastructure.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		MemberDTO memberDTO = new MemberDTO(
			0,
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		// Act
		Member actualMember = this.memberMapper.mapToDomain(memberDTO);

		// Assert
		Member expectedMember = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		assertThat(actualMember).isEqualTo(expectedMember);
	}

	@Test
	public void shouldMapDomainToDTO() {
		// Arrange
		Member member = new Member(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		// Act
		MemberDTO actualMemberDTO = this.memberMapper.mapToDTO(member);

		// Assert
		MemberDTO expectedMemberDTO = new MemberDTO(
			0,
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);

		assertThat(actualMemberDTO).isEqualTo(expectedMemberDTO);
	}

}