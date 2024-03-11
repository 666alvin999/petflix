package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class GetAnimalTypesByPresentationVideoIds {

	private final AnimalPort animalPort;

	public GetAnimalTypesByPresentationVideoIds(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public Map<VideoId, List<AnimalType>> execute(Set<VideoId> videoIds) {
		Set<String> ids = videoIds.stream().map(VideoId::value).collect(toSet());
		return this.animalPort.getAnimalTypesByPresentationVideoIds(ids);
	}

}
