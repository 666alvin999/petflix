package com.petflix.application.controller;

import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import com.petflix.application.mapper.PresentationVideoAndAnimalTypesMapper;
import com.petflix.application.presenter.PresentationVideoAndAnimalTypesPresenter;
import com.petflix.application.presenter.VideoFiltersPresenter;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.MemberPort;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.domain.usecase.GetAllVideoFilters;
import com.petflix.domain.usecase.GetAnimalTypesByPresentationVideoIds;
import com.petflix.domain.usecase.GetPresentationVideosWithFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Controller
@RequestMapping("/")
public class HomepageController {

	private final GetAllVideoFilters getAllVideoFilters;
	private final GetAnimalTypesByPresentationVideoIds getAnimalTypesByPresentationVideoIds;
	private final GetPresentationVideosWithFilter getPresentationVideosWithFilter;

	private final PresentationVideoAndAnimalTypesMapper presentationVideoAndAnimalTypesMapper;

	private final PresentationVideoAndAnimalTypesPresenter presentationVideoAndAnimalTypesPresenter;
	private final VideoFiltersPresenter videoFiltersPresenter;

	public HomepageController(PresentationVideoAndAnimalTypesMapper presentationVideoAndAnimalTypesMapper, PresentationVideoAndAnimalTypesPresenter presentationVideoAndAnimalTypesPresenter, VideoFiltersPresenter videoFiltersPresenter, AnimalPort animalPort, MemberPort memberPort, PresentationVideoPort presentationVideoPort) {
		this.getAllVideoFilters = new GetAllVideoFilters(animalPort, memberPort);
		this.getAnimalTypesByPresentationVideoIds = new GetAnimalTypesByPresentationVideoIds(animalPort);
		this.getPresentationVideosWithFilter = new GetPresentationVideosWithFilter(presentationVideoPort);
		this.presentationVideoAndAnimalTypesMapper = presentationVideoAndAnimalTypesMapper;
		this.presentationVideoAndAnimalTypesPresenter = presentationVideoAndAnimalTypesPresenter;
		this.videoFiltersPresenter = videoFiltersPresenter;
	}

//	@GetMapping("getFilters")
//	public ResponseEntity<String> getFilters() {
//		VideoFilters videoFilters = this.getAllVideoFilters.execute();
//
//		return this.videoFiltersPresenter.
//	}

	@GetMapping("getAllVideoOverViews")
	public ResponseEntity<String> getAllVideoOverviews() {
		List<PresentationVideo> presentationVideos = this.getPresentationVideosWithFilter.execute(null, null);
		Set<VideoId> presentationVideoIds = presentationVideos.stream().map(PresentationVideo::id).collect(toSet());
		Map<VideoId, List<AnimalType>> animalTypesByPresentationVideoIds = this.getAnimalTypesByPresentationVideoIds.execute(presentationVideoIds);

		List<PresentationVideoAndAnimalTypesViewModel> presentationVideoAndAnimalTypesViewModels = this.presentationVideoAndAnimalTypesMapper.mapAllToViewModel(presentationVideos, animalTypesByPresentationVideoIds);

		return this.presentationVideoAndAnimalTypesPresenter.presentAll(presentationVideoAndAnimalTypesViewModels);
	}

}
