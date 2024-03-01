package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.AnimalDTO;

public class AnimalMapper {

	public Animal mapToDomain(AnimalDTO animalDTO, Member member) {
		return new Animal(
			new Id(animalDTO.getId()),
			animalDTO.getName(),
			new AnimalType(animalDTO.getType()),
			animalDTO.getAge(),
			new Url(animalDTO.getPresentationVideoUrl()),
			member
		);
	}

	public AnimalDTO mapToDTO(Animal animal) {
		return new AnimalDTO(
			animal.id().value(),
			animal.name(),
			animal.type().value(),
			animal.age(),
			animal.presentationUrl().value(),
			animal.managingMember().id().value()
		);
	}

}
