package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoFiltersViewModel;
import com.petflix.domain.bean.PresentationVideoFilters;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;
import org.springframework.stereotype.Component;

@Component
public class PresentationVideoFiltersPresentationMapper {

	public PresentationVideoFiltersViewModel mapToViewModel(PresentationVideoFilters presentationVideoFilters) {
		return new PresentationVideoFiltersViewModel(
			presentationVideoFilters.animalTypes().stream().map(AnimalType::value).toList(),
			presentationVideoFilters.memberCities().stream().map(MemberCity::value).toList()
		);
	}

}
