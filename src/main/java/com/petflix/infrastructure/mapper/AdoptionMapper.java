package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.infrastructure.dto.AdoptionDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public AdoptionDTO mapToDTO(Adoption adoption) {
		return new AdoptionDTO(
			adoption.id().value(),
			adoption.adopter().id().value(),
			adoption.animal().id().value(),
			this.dateFormatter.format(adoption.date())
		);
	}
}
