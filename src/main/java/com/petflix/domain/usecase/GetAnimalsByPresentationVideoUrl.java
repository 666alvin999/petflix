package com.petflix.domain.usecase;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAnimalsByPresentationVideoUrl {

	private final AnimalPort animalPort;

	public GetAnimalsByPresentationVideoUrl(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<Animal> execute(Url url) {
		return this.animalPort.getAnimalsByPresentationVideoUrl(url);
	}
}
