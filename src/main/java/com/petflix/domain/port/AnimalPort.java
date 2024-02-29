package com.petflix.domain.port;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.generalfields.Url;

import java.util.List;

public interface AnimalPort {

	List<String> getAllTypes();

	List<Animal> getAnimalsByPresentationVideoUrl(String url);

}
