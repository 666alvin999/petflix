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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
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
		MemberDTO memberDTO = createMemberDTOs().get(0);
		Member member = createMembers().get(0);

		when(this.memberDao.getMemberById(0)).thenReturn(List.of(memberDTO));
		when(this.memberMapper.mapToDomain(memberDTO)).thenReturn(member);

		//Act
		Member actualMember = this.memberAdapter.getMemberById(0);

		//Assert
		Member expectedMember = createMembers().get(0);

		assertThat(actualMember).isEqualTo(expectedMember);
	}

	@Test
	public void shouldReturnMembersByIds() {
		//Arrange
		List<MemberDTO> memberDTOs = createMemberDTOs();
		List<Member> members = createMembers();

		when(this.memberDao.getMembersByIds(Set.of(0, 1))).thenReturn(memberDTOs);
		when(this.memberMapper.mapAllToDomain(memberDTOs)).thenReturn(members);

		//Act
		List<Member> actualMembers = this.memberAdapter.getMembersByIds(Set.of(0, 1));

		//Assert
		List<Member> expectedMembers = createMembers();

		assertThat(actualMembers).isEqualTo(expectedMembers);
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