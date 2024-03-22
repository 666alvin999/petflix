package com.petflix.application.mapper;

import com.petflix.application.dto.ControlViewModel;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Control;
import com.petflix.domain.bean.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ControlPresentationMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private final AdopterPresentationMapper adopterPresentationMapper;
	private final AnimalPresentationMapper animalPresentationMapper;
	private final MemberPresentationMapper memberPresentationMapper;

	@Autowired
	public ControlPresentationMapper(AdopterPresentationMapper adopterPresentationMapper, AnimalPresentationMapper animalPresentationMapper, MemberPresentationMapper memberPresentationMapper) {
		this.adopterPresentationMapper = adopterPresentationMapper;
		this.animalPresentationMapper = animalPresentationMapper;
		this.memberPresentationMapper = memberPresentationMapper;
	}

	public ControlViewModel mapToViewModel(Control control) {
		Animal animal = control.adoption().animal();
		Member member = control.adoption().animal().managingMember();
		Adopter adopter = control.adoption().adopter();

		return new ControlViewModel(
			this.animalPresentationMapper.mapToViewModel(animal),
			this.memberPresentationMapper.mapToViewModel(member),
			this.adopterPresentationMapper.mapToViewModel(adopter),
			this.dateFormatter.format(control.adoption().date()),
			this.dateFormatter.format(control.date())
		);
	}

	public List<ControlViewModel> mapAllToViewModels(List<Control> controls) {
		return controls.stream().map(this::mapToViewModel).toList();
	}

}
