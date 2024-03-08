package com.petflix.domain.usecase;

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
class GetAllCitiesTest {

	private GetAllCities getAllCities;

	@Mock
	private MemberPort memberPort;

	@BeforeEach
	public void setUp() {
		this.getAllCities = new GetAllCities(memberPort);
	}

	@Test
	public void shouldReturnAllCities() {
		//Arrange
		List<MemberCity> cities = List.of(new MemberCity("Paris"), new MemberCity("Valenciennes"), new MemberCity("Nice"));

		when(this.memberPort.getAllMembersCity()).thenReturn(cities);

		//Act
		List<MemberCity> actualCities = this.getAllCities.execute();

		//Assert
		List<MemberCity> expectedCities = List.of(new MemberCity("Paris"), new MemberCity("Valenciennes"), new MemberCity("Nice"));

		assertThat(actualCities).isEqualTo(expectedCities);
	}

}