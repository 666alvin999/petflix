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

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Adoption mapToDomain(AdoptionDTO adoptionDTO, Adopter adopter, Animal animal) {
		return new Adoption(
			animal,
			adopter,
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
			adoption.animal().id().value(),
			adoption.adopter().id().value(),
			this.dateFormatter.format(adoption.date())
		);
	}

	private Adopter findAdoptionAdopter(List<Adopter> adopters, AdoptionDTO adoptionDTO) {
		for (Adopter adopter: adopters) {
			if (adopter.id().value() == adoptionDTO.getAdopterId()) {
				return adopter;
			}
		}

		return null;
	}

	private Animal findAdoptionAnimal(List<Animal> animals, AdoptionDTO adoptionDTO) {
		for (Animal animal: animals) {
			if (animal.id().value() == adoptionDTO.getAnimalId()) {
				return animal;
			}
		}

		return null;
	}

}
