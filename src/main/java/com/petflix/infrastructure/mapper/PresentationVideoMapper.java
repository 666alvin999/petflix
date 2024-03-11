package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PresentationVideoMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PresentationVideo mapToDomain(PresentationVideoDTO presentationVideoDTO) {
		return new PresentationVideo(
			new VideoId(presentationVideoDTO.getId()),
			presentationVideoDTO.getTitle(),
			presentationVideoDTO.getDescription(),
			LocalDate.parse(presentationVideoDTO.getUploadDate(), this.dateFormatter)
		);
	}

	public List<PresentationVideo> mapAllToDomain(List<PresentationVideoDTO> presentationVideoDTOs) {
		return presentationVideoDTOs.stream().map(this::mapToDomain).toList();
	}

	public PresentationVideoDTO mapToDTO(PresentationVideo presentationVideo) {
		return new PresentationVideoDTO(
			presentationVideo.id().value(),
			presentationVideo.title(),
			presentationVideo.description(),
			this.dateFormatter.format(presentationVideo.uploadDate())
		);
	}

	public List<PresentationVideoDTO> mapAllToDTO(List<PresentationVideo> presentationVideos) {
		return presentationVideos.stream().map(this::mapToDTO).toList();
	}
}
