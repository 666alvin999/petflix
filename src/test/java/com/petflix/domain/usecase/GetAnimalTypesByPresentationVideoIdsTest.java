package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAnimalTypesByPresentationVideoIdsTest {

	private GetAnimalTypesByPresentationVideoIds usecase;

	@Mock
	private AnimalPort animalPort;

	@BeforeEach
	public void setUp() {
		this.usecase = new GetAnimalTypesByPresentationVideoIds(this.animalPort);
	}

	@Test
	public void shouldReturnMapOfVideoIdAndAnimalTypes() {
		// Arrange
		Map<VideoId, List<AnimalType>> map = createMap();

		when(this.animalPort.getAnimalTypesByPresentationVideoIds(Set.of("id1", "id2"))).thenReturn(map);

		// Act
		Map<VideoId, List<AnimalType>> actualMap = this.usecase.execute(createIds());

		// Assert
		Map<VideoId, List<AnimalType>> expectedMap = createMap();

		assertThat(actualMap).isEqualTo(expectedMap);
	}

	private static Map<VideoId, List<AnimalType>> createMap() {
		return Map.of(
			new VideoId("id1"),
			List.of(
				new AnimalType("chien"),
				new AnimalType("chat"))
			,
			new VideoId("id2"),
			List.of(
				new AnimalType("chien"),
				new AnimalType("chat")
			)
		);
	}

	private static Set<VideoId> createIds() {
		return Set.of(new VideoId("id1"), new VideoId("id2"));
	}

}