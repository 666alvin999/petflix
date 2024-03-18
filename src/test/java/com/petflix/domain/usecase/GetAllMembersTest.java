package com.petflix.domain.usecase;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.port.MemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllMembersTest {

	private GetAllMembers getAllMembers;

	@Mock
	private MemberPort memberPort;

	@BeforeEach
	public void setUp() {
		this.getAllMembers = new GetAllMembers(this.memberPort);
	}

	@Test
	public void shouldReturnAllMembers() {
		// Arrange
		List<Member> members = createMembers();

		when(this.memberPort.getAllMembers()).thenReturn(members);

		// Act
		List<Member> actualMembers = this.getAllMembers.execute();

		// Assert
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

}