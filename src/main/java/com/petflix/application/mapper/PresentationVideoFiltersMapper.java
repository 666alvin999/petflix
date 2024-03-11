package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoFiltersViewModel;
import com.petflix.domain.bean.PresentationVideoFilters;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;
import org.springframework.stereotype.Component;

@Component
public class PresentationVideoFiltersMapper {

	public PresentationVideoFiltersViewModel mapToViewModel(PresentationVideoFilters presentationVideoFilters) {
		return new PresentationVideoFiltersViewModel(
			presentationVideoFilters.getAnimalTypes().stream().map(AnimalType::value).toList(),
			presentationVideoFilters.getMemberCities().stream().map(MemberCity::value).toList()
		);
	}

}
