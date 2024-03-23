package com.petflix.application.mapper;

import com.petflix.application.dto.AnimalViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AnimalPresentationMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private final MemberPresentationMapper memberPresentationMapper;

	@Autowired
	public AnimalPresentationMapper(MemberPresentationMapper memberPresentationMapper) {
		this.memberPresentationMapper = memberPresentationMapper;
	}

	public AnimalViewModel mapToViewModel(Animal animal) {
		return new AnimalViewModel(
			animal.name(),
			animal.type().value(),
			animal.age(),
			animal.videoId().value(),
			this.dateFormatter.format(animal.arrivalDate()),
			animal.adopted()
		);
	}

	public List<AnimalViewModel> mapAllToViewModels(List<Animal> animals) {
		return animals.stream().map(this::mapToViewModel).toList();
	}

	public Animal mapToDomain(AnimalViewModel animalViewModel, MemberViewModel memberViewModel) {
		return new Animal(
			new Id(null),
			animalViewModel.getName(),
			new AnimalType(animalViewModel.getType()),
			animalViewModel.getAge(),
			new VideoId(animalViewModel.getPresentationVideoId()),
			this.memberPresentationMapper.mapToDomain(memberViewModel),
			LocalDate.parse(animalViewModel.getArrivalDate(), this.dateFormatter),
			false
		);
	}

	public List<Animal> mapAllToDomain(List<AnimalViewModel> animals, MemberViewModel member) {
		return animals.stream().map(animal -> this.mapToDomain(animal, member)).toList();
	}

}
