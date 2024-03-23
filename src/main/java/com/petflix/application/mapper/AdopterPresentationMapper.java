package com.petflix.application.mapper;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdopterPresentationMapper {

	public AdopterViewModel mapToViewModel(Adopter adopter) {
		return new AdopterViewModel(
			adopter.id().value(),
			adopter.firstName().value(),
			adopter.lastName().value(),
			adopter.address(),
			adopter.mail()
		);
	}

	public List<AdopterViewModel> mapAllToViewModels(List<Adopter> adopters) {
		return adopters.stream().map(this::mapToViewModel).toList();
	}

	public Adopter mapToDomain(AdopterViewModel adopterViewModel) {
		return new Adopter(
			new Id(adopterViewModel.getId()),
			new FirstName(adopterViewModel.getFirstName()),
			new LastName(adopterViewModel.getLastName()),
			adopterViewModel.getAddress(),
			adopterViewModel.getMail()
		);
	}

}
