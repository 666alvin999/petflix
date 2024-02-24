package com.petflix.domain.usecase;

import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAllAnimalTypes {

	private AnimalPort animalPort;

	public GetAllAnimalTypes(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<String> execute() {
		return this.animalPort.getAllTypes();
	}

}
