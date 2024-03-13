package com.petflix.domain.port;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AnimalPort {

	Animal getAnimalById(int id);

	List<Animal> getAnimalsByIds(Set<Integer> ids);

	List<AnimalType> getAllTypes();

	List<Animal> getAnimalsByPresentationVideoId(String videoId);

	Map<VideoId, List<AnimalType>> getAnimalTypesByPresentationVideoIds(Set<String> videoIds);
}
