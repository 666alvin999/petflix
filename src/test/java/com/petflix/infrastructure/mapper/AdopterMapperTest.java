package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.infrastructure.dto.AdopterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdopterMapperTest {

	private AdopterMapper adopterMapper;

	@BeforeEach
	public void setUp() {
		this.adopterMapper = new AdopterMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		//Arrange
		AdopterDTO adopterDTO = createAdopterDTO();

		//Act
		Adopter actualAdopter = this.adopterMapper.mapToDomain(adopterDTO);

		//Assert
		Adopter expectedAdopter = createAdopter();

		assertThat(actualAdopter).isEqualTo(expectedAdopter);
	}

	@Test
	public void shouldMapDomaintoDto() {
		//Arrange
		Adopter adopter = createAdopter();

		//Act
		AdopterDTO actualAdopterDTO = this.adopterMapper.mapToDTO(adopter);

		//Assert
		AdopterDTO expectedAdopterDTO = createAdopterDTO();

		assertThat(actualAdopterDTO).isEqualTo(expectedAdopterDTO);
	}

	private static Adopter createAdopter() {
		return new Adopter(
			new Id(0),
			new FirstName("Alvin"),
			new LastName("Hamaide"),
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);
	}

	private static AdopterDTO createAdopterDTO() {
		return new AdopterDTO(
			0,
			"Alvin",
			"Hamaide",
			"Valenciennes",
			"alvin.hamaide@mail-ecv.fr"
		);
	}

}