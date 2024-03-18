package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.infrastructure.dto.AdopterDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

	public List<Adopter> mapAllToDomain(List<AdopterDTO> adopterDTOs) {
		return adopterDTOs.stream().map(this::mapToDomain).toList();
	}

}
