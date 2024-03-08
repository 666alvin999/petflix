package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PresentationVideoMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public PresentationVideo mapToDomain(PresentationVideoDTO presentationVideoDTO) {
		return new PresentationVideo(
			new Id(presentationVideoDTO.getId()),
			new Url(presentationVideoDTO.getUrl()),
			presentationVideoDTO.getTitle(),
			presentationVideoDTO.getDescription(),
			LocalDate.parse(presentationVideoDTO.getPostingDate(), this.dateFormatter)
		);
	}

	public List<PresentationVideo> mapAllToDomain(List<PresentationVideoDTO> presentationVideoDTOs) {
		return presentationVideoDTOs.stream().map(this::mapToDomain).toList();
	}

	public PresentationVideoDTO mapToDTO(PresentationVideo presentationVideo) {
		return new PresentationVideoDTO(
			presentationVideo.id().value(),
			presentationVideo.url().value(),
			presentationVideo.title(),
			presentationVideo.description(),
			this.dateFormatter.format(presentationVideo.postingDate())
		);
	}

	public List<PresentationVideoDTO> mapAllToDTO(List<PresentationVideo> presentationVideos) {
		return presentationVideos.stream().map(this::mapToDTO).toList();
	}
}
