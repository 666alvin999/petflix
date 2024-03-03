package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.infrastructure.dto.AdoptionDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AdoptionMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public Adoption mapToDomain(AdoptionDTO adoptionDTO, Adopter adopter, Animal animal) {
		return new Adoption(
			new Id(adoptionDTO.getId()),
			adopter,
			animal,
			LocalDate.parse(adoptionDTO.getAdoptionDate(), this.dateFormatter)
		);
	}

	public List<Adoption> mapAllToDomain(List<AdoptionDTO> adoptionDTOs, List<Adopter> adopters, List<Animal> animals) {
		return adoptionDTOs.stream().map(adoptionDTO -> {
			Animal adoptionAnimal = findAdoptionAnimal(animals, adoptionDTO);
			Adopter adoptionAdopter = findAdoptionAdopter(adopters, adoptionDTO);

			return this.mapToDomain(adoptionDTO, adoptionAdopter, adoptionAnimal);
		}).toList();
	}

	public AdoptionDTO mapToDTO(Adoption adoption) {
		return new AdoptionDTO(
			adoption.id().value(),
			adoption.adopter().id().value(),
			adoption.animal().id().value(),
			this.dateFormatter.format(adoption.date())
		);
	}

	private Adopter findAdoptionAdopter(List<Adopter> adopters, AdoptionDTO adoptionDTO) {
		Adopter adoptionAdopter = null;
		for (Adopter adopter: adopters) {
			if (adopter.id().value() == adoptionDTO.getAdopterId()) {
				adoptionAdopter = adopter;
			}
		}
		return adoptionAdopter;
	}

	private Animal findAdoptionAnimal(List<Animal> animals, AdoptionDTO adoptionDTO) {
		Animal adoptionAnimal = null;

		for (Animal animal: animals) {
			if (animal.id().value() == adoptionDTO.getAnimalId()) {
				adoptionAnimal = animal;
			}
		}
		return adoptionAnimal;
	}

}
