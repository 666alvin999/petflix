package com.petflix.application.controller;

import com.petflix.application.dto.*;
import com.petflix.application.mapper.ActionSuccessPresentationMapper;
import com.petflix.application.mapper.AdopterPresentationMapper;
import com.petflix.application.mapper.AnimalPresentationMapper;
import com.petflix.application.mapper.PresentationVideoAndAnimalsAndMemberPresentationMapper;
import com.petflix.application.presenter.ActionSuccessPresenter;
import com.petflix.application.presenter.AdopterPresenter;
import com.petflix.application.presenter.PresentationVideoAndAnimalsAndMemberPresenter;
import com.petflix.domain.bean.*;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.*;
import com.petflix.domain.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/video")
public class PresentationVideoDetailController {

	private final CreateAdopter createAdopter;
	private final CreateAdoption createAdoption;
	private final CreateControl createControl;
	private final GetAllAdopters getAllAdopters;
	private final GetAnimalsByPresentationVideoId getAnimalsByPresentationVideoId;
	private final GetPresentationVideoById getPresentationVideoById;

	private final ActionSuccessPresentationMapper actionSuccessPresentationMapper;
	private final AdopterPresentationMapper adopterPresentationMapper;
	private final AnimalPresentationMapper animalPresentationMapper;
	private final PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper;

	private final ActionSuccessPresenter actionSuccessPresenter;
	private final AdopterPresenter adopterPresenter;
	private final PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter;

	@Autowired
	public PresentationVideoDetailController(ActionSuccessPresentationMapper actionSuccessPresentationMapper, AdopterPresentationMapper adopterPresentationMapper, AnimalPresentationMapper animalPresentationMapper, PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper, ActionSuccessPresenter actionSuccessPresenter, AdopterPresenter adopterPresenter, PresentationVideoAndAnimalsAndMemberPresenter presentationVideoAndAnimalsAndMemberPresenter, AdopterPort adopterPort, AdoptionPort adoptionPort, AnimalPort animalPort, ControlPort controlPort, PresentationVideoPort presentationVideoPort) {
		this.createAdopter = new CreateAdopter(adopterPort);
		this.createAdoption = new CreateAdoption(adoptionPort);
		this.createControl = new CreateControl(controlPort);
		this.getAllAdopters = new GetAllAdopters(adopterPort);
		this.getAnimalsByPresentationVideoId = new GetAnimalsByPresentationVideoId(animalPort);
		this.getPresentationVideoById = new GetPresentationVideoById(presentationVideoPort);
		this.actionSuccessPresentationMapper = actionSuccessPresentationMapper;
		this.adopterPresentationMapper = adopterPresentationMapper;
		this.animalPresentationMapper = animalPresentationMapper;
		this.presentationVideoAndAnimalsAndMemberPresentationMapper = presentationVideoAndAnimalsAndMemberPresentationMapper;
		this.actionSuccessPresenter = actionSuccessPresenter;
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

	@PostMapping("/submitAdoption")
	@CrossOrigin(origins = {"http://localhost:5173", "https://ecv-petflix.netlify.app/"})
	public ResponseEntity<String> submitAdoption(@RequestBody AdopterAndAnimalAndMemberViewModel adopterAndAnimalAndMemberViewModel) {
		Adopter adopter = this.adopterPresentationMapper.mapToDomain(adopterAndAnimalAndMemberViewModel.getAdopterViewModel());
		Animal animal = this.animalPresentationMapper.mapToDomain(adopterAndAnimalAndMemberViewModel.getAnimalViewModel(), adopterAndAnimalAndMemberViewModel.getMemberViewModel());

		ActionSuccess createAdopterSuccess = new ActionSuccess(true);

		if (isNull(adopter.id().value())) {
			createAdopterSuccess = this.createAdopter.execute(adopter);
		}

		if (createAdopterSuccess.success()) {
			Adoption adoption = new Adoption(animal, adopter, LocalDate.now());

			ActionSuccess createAdoptionSuccess = this.createAdoption.execute(adoption);

			if (createAdoptionSuccess.success()) {
				Control control = new Control(adoption, adoption.date().plusMonths(6));

				ActionSuccess createControlSuccess = this.createControl.execute(control);

				return this.actionSuccessPresenter.present(
					this.actionSuccessPresentationMapper.mapToViewModel(createControlSuccess)
				);
			}

			return this.actionSuccessPresenter.present(
				this.actionSuccessPresentationMapper.mapToViewModel(createAdoptionSuccess)
			);
		}

		return this.actionSuccessPresenter.present(
			this.actionSuccessPresentationMapper.mapToViewModel(createAdopterSuccess)
		);
	}

}
