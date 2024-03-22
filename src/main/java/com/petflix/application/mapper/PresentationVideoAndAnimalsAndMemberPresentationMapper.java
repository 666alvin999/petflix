package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresentationVideoAndAnimalsAndMemberPresentationMapper {

	private final AnimalPresentationMapper animalPresentationMapper;
	private final MemberPresentationMapper memberPresentationMapper;
	private final PresentationVideoPresentationMapper presentationVideoPresentationMapper;

	@Autowired
	public PresentationVideoAndAnimalsAndMemberPresentationMapper(AnimalPresentationMapper animalPresentationMapper, MemberPresentationMapper memberPresentationMapper, PresentationVideoPresentationMapper presentationVideoPresentationMapper) {
		this.animalPresentationMapper = animalPresentationMapper;
		this.memberPresentationMapper = memberPresentationMapper;
		this.presentationVideoPresentationMapper = presentationVideoPresentationMapper;
	}

	public PresentationVideoAndAnimalsAndMemberViewModel mapToViewModel(PresentationVideo presentationVideo, List<Animal> animals) {
		Member member = animals.get(0).managingMember();

		return new PresentationVideoAndAnimalsAndMemberViewModel(
			this.presentationVideoPresentationMapper.mapToViewModel(presentationVideo),
			this.animalPresentationMapper.mapAllToViewModels(animals),
			this.memberPresentationMapper.mapToViewModel(member)
		);
	}

}
