package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.AnimalDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class AnimalMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Animal mapToDomain(AnimalDTO animalDTO, Member member) {
		LocalDate adoptionDate = nonNull(animalDTO.getAdoptionDate()) ? LocalDate.parse(animalDTO.getAdoptionDate(), this.dateFormatter) : null;

		return new Animal(
			new Id(animalDTO.getId()),
			animalDTO.getName(),
			this.mapToAnimalType(animalDTO.getType()),
			animalDTO.getAge(),
			new Url(animalDTO.getPresentationVideoUrl()),
			member,
			LocalDate.parse(animalDTO.getArrivalDate(), this.dateFormatter),
			adoptionDate
		);
	}

	public List<Animal> mapAllToDomain(List<AnimalDTO> animalDTOs, List<Member> members) {
		return animalDTOs.stream().map(animalDTO -> {
			Member member = this.findMember(members, animalDTO);

			return mapToDomain(animalDTO, member);
		}).toList();
	}

	public AnimalDTO mapToDTO(Animal animal) {
		String adoptionDate = nonNull(animal.adoptionDate()) ? this.dateFormatter.format(animal.adoptionDate()) : null;

		return new AnimalDTO(
			animal.id().value(),
			animal.name(),
			animal.type().value(),
			animal.age(),
			animal.presentationUrl().value(),
			animal.managingMember().id().value(),
			this.dateFormatter.format(animal.arrivalDate()),
			adoptionDate
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
