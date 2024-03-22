package com.petflix.application.controller;

import com.petflix.application.dto.AdopterViewModel;
import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.mapper.AdopterPresentationMapper;
import com.petflix.application.mapper.PresentationVideoAndAnimalsAndMemberPresentationMapper;
import com.petflix.application.presenter.AdopterPresenter;
import com.petflix.application.presenter.PresentationVideoAndAnimalsAndMemberPresenter;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AdopterPort;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.domain.usecase.GetAllAdopters;
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

	private final GetAllAdopters getAllAdopters;
	private final GetAnimalsByPresentationVideoId getAnimalsByPresentationVideoId;
	private final GetPresentationVideoById getPresentationVideoById;

	private final AdopterPresentationMapper adopterPresentationMapper;
	private final PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper;

	private final AdopterPresenter adopterPresenter;
	private final PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter;

	@Autowired
	public PresentationVideoDetailController(AdopterPresentationMapper adopterPresentationMapper, PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper, AdopterPresenter adopterPresenter, PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter, AdopterPort adopterPort, AnimalPort animalPort, PresentationVideoPort presentationVideoPort) {
		this.getAllAdopters = new GetAllAdopters(adopterPort);
		this.getAnimalsByPresentationVideoId = new GetAnimalsByPresentationVideoId(animalPort);
		this.getPresentationVideoById = new GetPresentationVideoById(presentationVideoPort);
		this.adopterPresentationMapper = adopterPresentationMapper;
		this.presentationVideoAndAnimalsAndMemberPresentationMapper = presentationVideoAndAnimalsAndMemberPresentationMapper;
		this.adopterPresenter = adopterPresenter;
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

	@GetMapping("/getAllAdopters")
	@CrossOrigin(origins = {"http://localhost:5173", "https://ecv-petflix.netlify.app/"})
	public ResponseEntity<String> getAllAdopters() {
		List<Adopter> adopters = this.getAllAdopters.execute();

		List<AdopterViewModel> adopterViewModels = this.adopterPresentationMapper.mapAllToViewModels(adopters);

		return this.adopterPresenter.presentAll(adopterViewModels);
	}

}
