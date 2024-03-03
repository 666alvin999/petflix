package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.infrastructure.dao.AdopterDao;
import com.petflix.infrastructure.dto.AdopterDTO;
import com.petflix.infrastructure.mapper.AdopterMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdopterAdapterTest {

	private AdopterAdapter adopterAdapter;

	@Mock
	private AdopterDao adopterDao;

	@Mock
	private AdopterMapper adopterMapper;

	@BeforeEach
	public void setUp() {
		this.adopterAdapter = new AdopterAdapter(this.adopterDao, this.adopterMapper);
	}

	@Test
	public void shouldReturnAdopterById() {
	    //Arrange
		AdopterDTO adopterDTO = new AdopterDTO(0, "Alvin", "Hamaide", "Valenciennes", "alvin.hamaide@mail-ecv.fr");

		Adopter adopter = createAdopter();

		when(this.adopterDao.getAdopterById(0)).thenReturn(List.of(adopterDTO));
		when(this.adopterMapper.mapToDomain(adopterDTO)).thenReturn(adopter);

	    //Act
	    Adopter actualAdopter = this.adopterAdapter.getAdopterById(0);

	    //Assert
		Adopter expectedAdopter = createAdopter();

		assertThat(actualAdopter).isEqualTo(expectedAdopter);
	}

	private static Adopter createAdopter() {
		return new Adopter(new Id(0), new FirstName("Alvin"), new LastName("Hamaide"), "Valenciennes", "alvin.hamaide@mail-ecv.fr");
	}

}