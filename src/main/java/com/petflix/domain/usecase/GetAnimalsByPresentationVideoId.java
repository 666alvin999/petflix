package com.petflix.domain.usecase;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAnimalsByPresentationVideoId {

	private final AnimalPort animalPort;

	public GetAnimalsByPresentationVideoId(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<Animal> execute(VideoId videoId) {
		return this.animalPort.getAnimalsByPresentationVideoId(videoId.value());
	}

}
