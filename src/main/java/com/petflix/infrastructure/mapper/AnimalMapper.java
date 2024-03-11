package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.AnimalTypesByPresentationVideoIdDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Animal mapToDomain(AnimalDTO animalDTO, Member member) {
		return new Animal(
			new Id(animalDTO.getId()),
			animalDTO.getName(),
			this.mapToAnimalType(animalDTO.getType()),
			animalDTO.getAge(),
			new VideoId(animalDTO.getPresentationVideoId()),
			member,
			LocalDate.parse(animalDTO.getArrivalDate(), this.dateFormatter)
		);
	}

	public List<Animal> mapAllToDomain(List<AnimalDTO> animalDTOs, List<Member> members) {
		return animalDTOs.stream().map(animalDTO -> {
			Member member = this.findMember(members, animalDTO);

			return mapToDomain(animalDTO, member);
		}).toList();
	}

	public AnimalDTO mapToDTO(Animal animal) {
		return new AnimalDTO(
			animal.id().value(),
			animal.name(),
			animal.type().value(),
			animal.age(),
			animal.videoId().value(),
			animal.managingMember().id().value(),
			this.dateFormatter.format(animal.arrivalDate())
		);
	}

	public AnimalType mapToAnimalType(String animalTypeDTO) {
		return new AnimalType(animalTypeDTO);
	}

	public List<AnimalType> mapAllToAnimalTypes(List<String> animalTypeDTOs) {
		return animalTypeDTOs.stream().map(this::mapToAnimalType).toList();
	}

	public Map<VideoId, List<AnimalType>> mapToAnimalTypesByPresentationVideoIds(List<AnimalTypesByPresentationVideoIdDTO> animalTypesByPresentationVideoIdDTOs) {
		Map<VideoId, List<AnimalType>> map = new HashMap<>();

		animalTypesByPresentationVideoIdDTOs.forEach(
			animalTypesByPresentationVideoIdDTO -> map.put(
				new VideoId(animalTypesByPresentationVideoIdDTO.getPresentationVideoId()),
				Arrays.stream(
					      animalTypesByPresentationVideoIdDTO
						      .getAnimalTypes()
						      .split(",")
				      )
				      .map(AnimalType::new)
				      .toList()
			)
		);

		return map;
	}

	private Member findMember(List<Member> members, AnimalDTO animalDTO) {
		for (Member member : members) {
			if (member.id().value() == animalDTO.getId()) {
				return member;
			}
		}

		return null;
	}
}
