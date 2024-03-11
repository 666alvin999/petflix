package com.petflix.domain.port;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.animalfields.AnimalType;

import java.util.List;
import java.util.Set;

public interface AnimalPort {

	Animal getAnimalById(int id);

	List<Animal> getAnimalsByIds(Set<Integer> ids);

	List<AnimalType> getAllTypes();

	List<Animal> getAnimalsByPresentationVideoId(String videoId);

	List<AnimalType> getAnimalTypesByPresentationVideoId(String videoId);

}
