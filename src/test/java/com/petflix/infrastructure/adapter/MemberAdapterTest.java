package com.petflix.infrastructure.adapter;

import com.petflix.infrastructure.dao.MemberDao;
import com.petflix.infrastructure.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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

	    //Act

	    //Assert

	}

}