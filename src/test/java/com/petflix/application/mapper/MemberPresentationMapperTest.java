package com.petflix.application.mapper;

import com.petflix.application.dto.MemberViewModel;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberPresentationMapperTest {

	private MemberPresentationMapper memberPresentationMapper;

	@BeforeEach
	public void setUp() {
		this.memberPresentationMapper = new MemberPresentationMapper();
	}

	@Test
	public void shouldMapToViewModel() {
		// Act
		MemberViewModel actualMemberViewModel = this.memberPresentationMapper.mapToViewModel(createMembers().get(0));

		// Assert
		MemberViewModel expectedMemberViewModel = createMemberViewModels().get(0);

		assertThat(actualMemberViewModel).isEqualTo(expectedMemberViewModel);
	}

	@Test
	public void shouldmapAllToViewModels() {
		// Act
		List<MemberViewModel> actualMemberViewModels = this.memberPresentationMapper.mapAllToViewModels(createMembers());

		// Assert
		List<MemberViewModel> expectedMemberViewModels = createMemberViewModels();

		assertThat(actualMemberViewModels).isEqualTo(expectedMemberViewModels);
	}

	@Test
	public void shouldMapToDomain() {
		// Act
		Member actualMember = this.memberPresentationMapper.mapToDomain(createMemberViewModels().get(0));

		// Assert
		Member expectedMember = createMembers().get(0);

		assertThat(actualMember).isEqualTo(expectedMember);
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
			)
		);
	}

	private static List<MemberViewModel> createMemberViewModels() {
		return List.of(
			new MemberViewModel(
				0,
				"Alvin",
				"Hamaide",
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr",
				"06XXXXXXXX"
			)
		);
	}

}