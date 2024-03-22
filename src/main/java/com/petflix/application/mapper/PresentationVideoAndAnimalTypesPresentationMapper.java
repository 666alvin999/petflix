package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PresentationVideoAndAnimalTypesPresentationMapper {

	private final PresentationVideoPresentationMapper presentationVideoPresentationMapper;

	@Autowired
	public PresentationVideoAndAnimalTypesPresentationMapper(PresentationVideoPresentationMapper presentationVideoPresentationMapper) {
		this.presentationVideoPresentationMapper = presentationVideoPresentationMapper;
	}

	public PresentationVideoAndAnimalTypesViewModel mapToViewModel(PresentationVideo presentationVideo, List<AnimalType> animalTypes) {
		return new PresentationVideoAndAnimalTypesViewModel(
			this.presentationVideoPresentationMapper.mapToViewModel(presentationVideo),
			animalTypes.stream().map(AnimalType::value).toList()
		);
	}

	public List<PresentationVideoAndAnimalTypesViewModel> mapAllToViewModels(List<PresentationVideo> presentationVideos, Map<VideoId, List<AnimalType>> animalTypesByPresentationVideoIds) {
		return presentationVideos
			.stream()
			.map(
				presentationVideo ->
					this.mapToViewModel(
						presentationVideo,
						animalTypesByPresentationVideoIds.get(presentationVideo.id())
					)
			).toList();
	}

}
