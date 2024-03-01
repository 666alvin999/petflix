package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.infrastructure.dto.AdopterDTO;

public class AdopterMapper {

	public Adopter mapToDomain(AdopterDTO adopterDTO) {
		return new Adopter(
			new Id(adopterDTO.getId()),
			new FirstName(adopterDTO.getFirstName()),
			new LastName(adopterDTO.getLastName()),
			adopterDTO.getAddress(),
			adopterDTO.getMail()
		);
	}

	public AdopterDTO mapToDTO(Adopter adopter) {
		return new AdopterDTO(
			adopter.id().value(),
			adopter.firstName().value(),
			adopter.lastName().value(),
			adopter.address(),
			adopter.mail()
		);
	}

}
