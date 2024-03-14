package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class PresentationVideoAndAnimalTypesMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PresentationVideoAndAnimalTypesViewModel mapToViewModel(PresentationVideo presentationVideo, List<AnimalType> animalTypes) {
		return new PresentationVideoAndAnimalTypesViewModel(
			new PresentationVideoViewModel(
				presentationVideo.id().value(),
				presentationVideo.title(),
				presentationVideo.description(),
				this.dateFormatter.format(presentationVideo.uploadDate())
			),
			animalTypes.stream().map(AnimalType::value).toList()
		);
	}

	public List<PresentationVideoAndAnimalTypesViewModel> mapAllToViewModel(List<PresentationVideo> presentationVideos, Map<VideoId, List<AnimalType>> animalTypesByPresentationVideoIds) {
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
