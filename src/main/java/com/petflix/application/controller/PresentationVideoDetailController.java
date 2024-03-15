package com.petflix.application.controller;

import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.mapper.PresentationVideoAndAnimalsAndMemberPresentationMapper;
import com.petflix.application.presenter.PresentationVideoAndAnimalsAndMemberPresenter;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.domain.usecase.GetAnimalsByPresentationVideoId;
import com.petflix.domain.usecase.GetPresentationVideoById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/video")
public class PresentationVideoDetailController {

	private final GetAnimalsByPresentationVideoId getAnimalsByPresentationVideoId;
	private final GetPresentationVideoById getPresentationVideoById;

	private final PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper;
	private final PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter;

	@Autowired
	public PresentationVideoDetailController(PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper, PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter, AnimalPort animalPort, PresentationVideoPort presentationVideoPort) {
		this.getAnimalsByPresentationVideoId = new GetAnimalsByPresentationVideoId(animalPort);
		this.getPresentationVideoById = new GetPresentationVideoById(presentationVideoPort);
		this.presentationVideoAndAnimalsAndMemberPresentationMapper = presentationVideoAndAnimalsAndMemberPresentationMapper;
		this.presentationVideoAndAnimalsAndMemberPresenter = presentationVideoAndAnimalsAndMemberPresenter;
	}

	@GetMapping("/getPresentationVideoDetails")
	@CrossOrigin(origins = {"http://localhost:5173", "https://ecv-petflix.netlify.app/"})
	public ResponseEntity<String> getPresentationVideoDetails(@RequestParam String presentationVideoId) {
		List<Animal> animals = this.getAnimalsByPresentationVideoId.execute(new VideoId(presentationVideoId));
		PresentationVideo presentationVideo = this.getPresentationVideoById.execute(presentationVideoId);

		PresentationVideoAndAnimalsAndMemberViewModel viewModel = this.presentationVideoAndAnimalsAndMemberPresentationMapper.mapToViewModel(presentationVideo, animals);

		return this.presentationVideoAndAnimalsAndMemberPresenter.present(viewModel);
	}

}
