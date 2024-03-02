package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.infrastructure.dao.MemberDao;
import com.petflix.infrastructure.dto.MemberDTO;
import com.petflix.infrastructure.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberAdapterTest {

	private MemberAdapter memberAdapter;

	@Mock
	private MemberDao memberDao;

	@Mock
	private MemberMapper memberMapper;

	@BeforeEach
	public void setUp() {
		this.memberAdapter = new MemberAdapter(this.memberDao, this.memberMapper);
	}

	@Test
	public void shouldReturnAllCities() {
	    //Arrange
		List<String> cities = List.of("Valenciennes", "Lille");

		when(this.memberDao.getAllCities()).thenReturn(cities);
		when(this.memberMapper.mapCities(cities)).thenReturn(List.of(new MemberCity("Valenciennes"), new MemberCity("Lille")));

	    //Act
		List<MemberCity> actualCities = this.memberAdapter.getAllMembersCity();

	    //Assert
		List<MemberCity> expectedCities = List.of(new MemberCity("Valenciennes"), new MemberCity("Lille"));

		assertThat(actualCities).isEqualTo(expectedCities);
	}

	@Test
	public void shouldReturnMemberById() {
	    //Arrange
		MemberDTO memberDTO = createMemberDTO();
		Member member = createMember();

		when(this.memberDao.getMemberById(0)).thenReturn(List.of(memberDTO));
		when(this.memberMapper.mapToDomain(memberDTO)).thenReturn(member);

	    //Act
		Member actualMember = this.memberAdapter.getMemberById(0);

	    //Assert
		Member expectedMember = createMember();

		assertThat(actualMember).isEqualTo(expectedMember);
	}

	private static Member createMember() {
		return new Member(new Id(1), new FirstName("Alvin"), new LastName("Hamaide"), new MemberCity("Valenciennes"), "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");
	}

	private static MemberDTO createMemberDTO() {
		return new MemberDTO(
			0,
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr",
			"06XXXXXXXX"
		);
	}

}