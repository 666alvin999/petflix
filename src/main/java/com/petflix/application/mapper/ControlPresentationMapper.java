package com.petflix.application.mapper;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.ControlViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Control;
import com.petflix.domain.bean.Member;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ControlPresentationMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ControlViewModel mapToViewModel(Control control) {
		Animal animal = control.adoption().animal();
		Member member = control.adoption().animal().managingMember();
		Adopter adopter = control.adoption().adopter();

		return new ControlViewModel(
			new AnimalViewModel(
				animal.name(),
				animal.type().value(),
				animal.age(),
				animal.videoId().value(),
				this.dateFormatter.format(animal.arrivalDate()),
				animal.adopted()
			),
			new MemberViewModel(
				member.id().value(),
				member.firstName().value(),
				member.lastName().value(),
				member.city().value(),
				member.mail(),
				member.phone()
			),
			new AdopterViewModel(
				adopter.firstName().value(),
				adopter.lastName().value(),
				adopter.address(),
				adopter.mail()
			),
			this.dateFormatter.format(control.adoption().date()),
			this.dateFormatter.format(control.date())
		);
	}

	public List<ControlViewModel> mapAllToViewModel(List<Control> controls) {
		return controls.stream().map(this::mapToViewModel).toList();
	}

}
