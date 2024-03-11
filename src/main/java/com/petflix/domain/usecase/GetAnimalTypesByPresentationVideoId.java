package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAnimalTypesByPresentationVideoId {

	private final AnimalPort animalPort;

	public GetAnimalTypesByPresentationVideoId(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<AnimalType> execute(VideoId videoId) {
		return this.animalPort.getAnimalTypesByPresentationVideoId(videoId.value());
	}

}
