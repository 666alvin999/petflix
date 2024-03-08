package com.petflix.domain.usecase;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.port.AnimalPort;

import java.util.List;

public class GetAllAnimalTypes {

	private final AnimalPort animalPort;

	public GetAllAnimalTypes(AnimalPort animalPort) {
		this.animalPort = animalPort;
	}

	public List<AnimalType> execute() {
		return this.animalPort.getAllTypes();
	}

}
