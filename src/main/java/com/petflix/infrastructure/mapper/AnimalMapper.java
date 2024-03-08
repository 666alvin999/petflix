package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.AnimalDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalMapper {

	public Animal mapToDomain(AnimalDTO animalDTO, Member member) {
		return new Animal(
			new Id(animalDTO.getId()),
			animalDTO.getName(),
			this.mapToAnimalType(animalDTO.getType()),
			animalDTO.getAge(),
			new Url(animalDTO.getPresentationVideoUrl()),
			member
		);
	}

	public List<Animal> mapAllToDomain(List<AnimalDTO> animalDTOs, List<Member> members) {
		return animalDTOs.stream().map(animalDTO -> {
			for (Member member: members) {
				if (member.id().value() == animalDTO.getId()) {
					return mapToDomain(animalDTO, member);
				}
			}

			return mapToDomain(animalDTO, null);
		}).toList();
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

	public AnimalType mapToAnimalType(String animalTypeDTO) {
		return new AnimalType(animalTypeDTO);
	}

	public List<AnimalType> mapAllToAnimalTypes(List<String> animalTypeDTOs) {
		return animalTypeDTOs.stream().map(this::mapToAnimalType).toList();
	}

	private Member findMember(List<Member> members, AnimalDTO animalDTO) {
		Member animalMember = null;

		for (Member member: members) {
			if (member.id().value() == animalDTO.getId()) {
				return member;
			}
		}

		return null;
	}

}
