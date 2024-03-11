package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAnimalTypesByPresentationVideoUrl {

	private final AnimalPort animalPort;

	public GetAnimalTypesByPresentationVideoUrl(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<AnimalType> execute(Url url) {
		return this.animalPort.getAnimalTypesByPresentationVideoUrl(url.value());
	}

}
