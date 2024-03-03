package com.petflix.domain.port;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.animalfields.AnimalType;

import java.util.List;

public interface AnimalPort {

	Animal getAnimalById(int id);

	List<AnimalType> getAllTypes();

	List<Animal> getAnimalsByPresentationVideoUrl(String url);

}
