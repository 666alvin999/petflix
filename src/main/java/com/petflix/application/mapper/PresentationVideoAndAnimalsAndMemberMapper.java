package com.petflix.application.mapper;

import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PresentationVideoAndAnimalsAndMemberMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PresentationVideoAndAnimalsAndMemberViewModel mapToViewModel(PresentationVideo presentationVideo, List<Animal> animals) {
		Member member = animals.get(0).managingMember();

		return new PresentationVideoAndAnimalsAndMemberViewModel(
			new PresentationVideoViewModel(
				presentationVideo.id().value(),
				presentationVideo.title(),
				presentationVideo.description(),
				this.dateFormatter.format(presentationVideo.uploadDate())
			),
			animals.stream().map(this::mapAnimalToViewModel).toList(),
			new MemberViewModel(
				member.firstName().value(),
				member.lastName().value(),
				member.city().value(),
				member.mail(),
				member.phone()
			)
		);
	}

	private AnimalViewModel mapAnimalToViewModel(Animal animal) {
		return new AnimalViewModel(
			animal.name(),
			animal.type().value(),
			animal.age(),
			animal.videoId().value(),
			this.dateFormatter.format(animal.arrivalDate()),
			animal.adopted()
		);
	};

}
