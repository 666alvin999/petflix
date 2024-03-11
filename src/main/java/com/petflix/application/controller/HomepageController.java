package com.petflix.application.controller;

import com.petflix.application.presenter.PresentationVideoAndAnimalTypesPresenter;
import com.petflix.application.presenter.VideoFiltersPresenter;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.MemberPort;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.domain.usecase.GetAllVideoFilters;
import com.petflix.domain.usecase.GetAnimalTypesByPresentationVideoId;
import com.petflix.domain.usecase.GetPresentationVideosWithFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

	private final GetAnimalTypesByPresentationVideoId getAnimalTypesByPresentationVideoId;
	private final GetPresentationVideosWithFilter getPresentationVideosWithFilter;
	private final GetAllVideoFilters getAllVideoFilters;

	private final PresentationVideoAndAnimalTypesPresenter presentationVideoAndAnimalTypesPresenter;
	private final VideoFiltersPresenter videoFiltersPresenter;

	@Autowired
	public HomepageController(PresentationVideoAndAnimalTypesPresenter presentationVideoAndAnimalTypesPresenter, VideoFiltersPresenter videoFiltersPresenter, AnimalPort animalPort, MemberPort memberPort, PresentationVideoPort presentationVideoPort) {
		this.getAnimalTypesByPresentationVideoId = new GetAnimalTypesByPresentationVideoId(animalPort);
		this.getPresentationVideosWithFilter = new GetPresentationVideosWithFilter(presentationVideoPort);
		this.getAllVideoFilters = new GetAllVideoFilters(animalPort, memberPort);
		this.presentationVideoAndAnimalTypesPresenter = presentationVideoAndAnimalTypesPresenter;
		this.videoFiltersPresenter = videoFiltersPresenter;
	}

	@GetMapping("test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("test");
	}

}
