package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.AnimalsAndMemberViewModel;
import org.springframework.http.ResponseEntity;

public class AnimalsAndMemberPresenter {

	public ResponseEntity<String> present(AnimalsAndMemberViewModel animalsAndMemberViewModel) {
		return ResponseEntity.ok(new Gson().toJson(animalsAndMemberViewModel));
	}

}
