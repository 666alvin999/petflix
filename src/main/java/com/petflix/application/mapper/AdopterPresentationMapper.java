package com.petflix.application.mapper;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.domain.bean.Adopter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdopterPresentationMapper {

	public AdopterViewModel mapToViewModel(Adopter adopter) {
		return new AdopterViewModel(
			adopter.firstName().value(),
			adopter.lastName().value(),
			adopter.address(),
			adopter.mail()
		);
	}

	public List<AdopterViewModel> mapAllToViewModels(List<Adopter> adopters) {
		return adopters.stream().map(this::mapToViewModel).toList();
	}
}
