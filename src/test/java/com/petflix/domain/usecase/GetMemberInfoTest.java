package com.petflix.domain.usecase;

import com.petflix.domain.bean.Member;
import com.petflix.domain.port.MemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetMemberInfoTest {

	private GetMemberInfo getMemberInfo;

	@Mock
	private MemberPort memberPort;

	@BeforeEach
	public void setUp() {
		this.getMemberInfo = new GetMemberInfo(memberPort);
	}

	@Test
	public void shouldGetMember() {
	    //Arrange
		Member member = new Member(1, "Alvin", "Hamaide", "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");

		when(this.memberPort.getMemberById(1)).thenReturn(member);

	    //Act
		Member actualMember = this.getMemberInfo.execute(1);

	    //Assert
		Member expectedMember = new Member(1, "Alvin", "Hamaide", "Lille", "alvin.hamaide@mail-ecv.fr", "06XXXXXXXX");

		assertThat(actualMember).isEqualTo(expectedMember);
	}

}