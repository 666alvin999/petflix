package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.infrastructure.dto.AdopterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdopterMapperTest {

	private AdopterMapper adopterMapper;

	@BeforeEach
	public void setUp() {
		this.adopterMapper = new AdopterMapper();
	}

	@Test
	public void shouldMapDtoToDomain() {
		// Arrange
		AdopterDTO adopterDTO = createAdopterDTOs().get(0);

		// Act
		Adopter actualAdopter = this.adopterMapper.mapToDomain(adopterDTO);

		// Assert
		Adopter expectedAdopter = createAdopters().get(0);

		assertThat(actualAdopter).isEqualTo(expectedAdopter);
	}

	@Test
	public void shouldMapAllDtosToDomain() {
		// Arrange
		List<AdopterDTO> adopterDTOs = createAdopterDTOs();

		// Act
		List<Adopter> actualAdopters = this.adopterMapper.mapAllToDomain(adopterDTOs);

		// Assert
		List<Adopter> expectedAdopters = createAdopters();

		assertThat(actualAdopters).isEqualTo(expectedAdopters);
	}

	@Test
	public void shouldMapDomainToDTO() {
		// Arrange
		Adopter adopter = createAdopters().get(0);

		// Act
		AdopterDTO actualAdopterDTO = this.adopterMapper.mapToDTO(adopter);

		// Assert
		AdopterDTO expectedAdopterDTO = createAdopterDTOs().get(0);

		assertThat(actualAdopterDTO).isEqualTo(expectedAdopterDTO);
	}

	private static List<Adopter> createAdopters() {
		return List.of(
			new Adopter(
				new Id(0),
				new FirstName("Alvin"),
				new LastName("Hamaide"),
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr"
			),
			new Adopter(
				new Id(1),
				new FirstName("Martin"),
				new LastName("Matin"),
				"Valenciennes",
				"martin.matin@mail-ecv.fr"
			)
		);
	}

	private static List<AdopterDTO> createAdopterDTOs() {
		return List.of(
			new AdopterDTO(
				0,
				"Alvin",
				"Hamaide",
				"Valenciennes",
				"alvin.hamaide@mail-ecv.fr"
			),
			new AdopterDTO(
				1,
				"Martin",
				"Matin",
				"Valenciennes",
				"martin.matin@mail-ecv.fr"
			)
		);
	}

}