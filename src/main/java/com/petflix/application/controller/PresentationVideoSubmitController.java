package com.petflix.application.controller;

import com.google.gson.Gson;
import com.petflix.application.dto.ActionSuccessViewModel;
import com.petflix.application.dto.MemberViewModel;
import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import com.petflix.application.mapper.MemberPresentationMapper;
import com.petflix.application.mapper.PresentationVideoAndAnimalsAndMemberPresentationMapper;
import com.petflix.application.presenter.MemberPresenter;
import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.MemberPort;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.domain.usecase.GetAllMembers;
import com.petflix.domain.usecase.SubmitPresentationVideo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/submitPresentationVideo")
public class PresentationVideoSubmitController {

	private final GetAllMembers getAllMembers;
	private final SubmitPresentationVideo submitPresentationVideo;

	private final MemberPresentationMapper memberPresentationMapper;
	private final PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper;

	private final MemberPresenter memberPresenter;

	public PresentationVideoSubmitController(MemberPresentationMapper memberPresentationMapper, PresentationVideoAndAnimalsAndMemberPresentationMapper presentationVideoAndAnimalsAndMemberPresentationMapper, MemberPresenter memberPresenter, MemberPort memberPort, PresentationVideoPort presentationVideoPort, AnimalPort animalPort) {
		this.getAllMembers = new GetAllMembers(memberPort);
		this.submitPresentationVideo = new SubmitPresentationVideo(presentationVideoPort, animalPort);
		this.memberPresentationMapper = memberPresentationMapper;
		this.presentationVideoAndAnimalsAndMemberPresentationMapper = presentationVideoAndAnimalsAndMemberPresentationMapper;
		this.memberPresenter = memberPresenter;
	}

	@GetMapping("/getAllMembers")
	@CrossOrigin(origins = {"http://localhost:5173", "https://ecv-petflix.netlify.app/"})
	public ResponseEntity<String> getAllMembers() {
		List<Member> members = this.getAllMembers.execute();
		List<MemberViewModel> memberViewModels = this.memberPresentationMapper.mapAllToViewModels(members);

		return this.memberPresenter.presentAll(memberViewModels);
	}

	@PostMapping("/submit")
	@CrossOrigin(origins = {"http://localhost:5173", "https://ecv-petflix.netlify.app/"})
	public ResponseEntity<String> submit(@RequestBody PresentationVideoAndAnimalsAndMemberViewModel responseViewModel) {
		List<Animal> animals = this.presentationVideoAndAnimalsAndMemberPresentationMapper.mapViewModelToAnimals(responseViewModel);

		PresentationVideo presentationVideo = this.presentationVideoAndAnimalsAndMemberPresentationMapper.mapViewModelToPresentationVideo(responseViewModel);

		ActionSuccess actionSuccess = this.submitPresentationVideo.execute(presentationVideo, animals);

		ActionSuccessViewModel actionSuccessViewModel = new ActionSuccessViewModel(actionSuccess.success(), actionSuccess.errorMessage().orElse(null));
		return ResponseEntity.ok(new Gson().toJson(actionSuccessViewModel));
	}

}
