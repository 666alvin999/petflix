package com.petflix.application.mapper;

import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PresentationVideoAndAnimalsAndMemberPresentationMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PresentationVideoAndAnimalsAndMemberViewModel mapToViewModel(PresentationVideo presentationVideo, List<Animal> animals) {
		Member member = animals.get(0).managingMember();

		return new PresentationVideoAndAnimalsAndMemberViewModel(
			mapPresentationVideoToViewModel(presentationVideo),
			animals.stream().map(this::mapAnimalToViewModel).toList(),
			this.mapMemberToViewModel(member)
		);
	}

	public PresentationVideo mapViewModelToPresentationVideo(PresentationVideoAndAnimalsAndMemberViewModel viewModel) {
		return new PresentationVideo(
			new VideoId(viewModel.getPresentationVideo().getId()),
			viewModel.getPresentationVideo().getTitle(),
			viewModel.getPresentationVideo().getDescription(),
			LocalDate.parse(viewModel.getPresentationVideo().getUploadDate(), this.dateFormatter)
		);
	}

	public List<Animal> mapViewModelToAnimals(PresentationVideoAndAnimalsAndMemberViewModel viewModel) {
		return viewModel.getAnimals().stream().map(animal -> this.mapViewModelToAnimal(animal, viewModel.getMember(), viewModel.getPresentationVideo())).toList();
	}

	private PresentationVideoViewModel mapPresentationVideoToViewModel(PresentationVideo presentationVideo) {
		return new PresentationVideoViewModel(
			presentationVideo.id().value(),
			presentationVideo.title(),
			presentationVideo.description(),
			this.dateFormatter.format(presentationVideo.uploadDate())
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
	}

	private MemberViewModel mapMemberToViewModel(Member member) {
		return new MemberViewModel(
			member.id().value(),
			member.firstName().value(),
			member.lastName().value(),
			member.city().value(),
			member.mail(),
			member.phone()
		);
	}

	private Animal mapViewModelToAnimal(AnimalViewModel animalViewModel, MemberViewModel memberViewModel, PresentationVideoViewModel presentationVideoViewModel) {
		return new Animal(
			new Id(null),
			animalViewModel.getName(),
			new AnimalType(animalViewModel.getType()),
			animalViewModel.getAge(),
			new VideoId(presentationVideoViewModel.getId()),
			this.mapViewModelToMember(memberViewModel),
			LocalDate.parse(animalViewModel.getArrivalDate(), this.dateFormatter),
			false
		);
	}

	private Member mapViewModelToMember(MemberViewModel memberViewModel) {
		return new Member(
			new Id(memberViewModel.getId()),
			new FirstName(memberViewModel.getFirstName()),
			new LastName(memberViewModel.getLastName()),
			new MemberCity(memberViewModel.getCity()),
			memberViewModel.getMail(),
			memberViewModel.getPhone()
		);
	}

}
