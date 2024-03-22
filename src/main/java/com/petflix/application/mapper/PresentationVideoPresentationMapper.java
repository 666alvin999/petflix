package com.petflix.application.mapper;

import com.petflix.application.dto.PresentationVideoViewModel;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PresentationVideoPresentationMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PresentationVideoViewModel mapToViewModel(PresentationVideo presentationVideo) {
		return new PresentationVideoViewModel(
			presentationVideo.id().value(),
			presentationVideo.title(),
			presentationVideo.description(),
			this.dateFormatter.format(presentationVideo.uploadDate())
		);
	}

	public PresentationVideo mapToDomain(PresentationVideoViewModel presentationVideoViewModel) {
		return new PresentationVideo(
			new VideoId(presentationVideoViewModel.getId()),
			presentationVideoViewModel.getTitle(),
			presentationVideoViewModel.getDescription(),
			LocalDate.parse(presentationVideoViewModel.getUploadDate(), this.dateFormatter)
		);
	}

}
