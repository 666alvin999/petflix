package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.PresentationVideoAndAnimalsAndMemberViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PresentationVideoAndAnimalsAndMemberPresenter {

	public ResponseEntity<String> present(PresentationVideoAndAnimalsAndMemberViewModel presentationVideoAndAnimalsAndMemberViewModel) {
		return ResponseEntity.ok(new Gson().toJson(presentationVideoAndAnimalsAndMemberViewModel));
	}

}
