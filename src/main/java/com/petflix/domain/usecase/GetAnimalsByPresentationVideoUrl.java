package com.petflix.domain.usecase;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAnimalsByPresentationVideoUrl {

	private final AnimalPort animalPort;

	public GetAnimalsByPresentationVideoUrl(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<Animal> execute(String url) {
		return this.animalPort.getAnimalsByPresentationVideoUrl(url);
	}
}
