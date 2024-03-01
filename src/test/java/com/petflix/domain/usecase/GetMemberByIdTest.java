package com.petflix.domain.usecase;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.port.MemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetMemberByIdTest {

	private GetMemberById getMemberById;

	@Mock
	private MemberPort memberPort;

	@BeforeEach
	public void setUp() {
		this.getMemberById = new GetMemberById(memberPort);
	}

	@Test
	public void shouldGetMember() {
	    //Arrange
		Member member = new Member(new Id(1), new FirstName("Alvin"), new LastName("Hamaide"), "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");

		when(this.memberPort.getMemberById(1)).thenReturn(member);

	    //Act
		Member actualMember = this.getMemberById.execute(1);

	    //Assert
		Member expectedMember = new Member(new Id(1), new FirstName("Alvin"), new LastName("Hamaide"), "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");

		assertThat(actualMember).isEqualTo(expectedMember);
	}

}