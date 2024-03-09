package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.AdopterViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AdopterPresenter {

	public ResponseEntity<String> present(AdopterViewModel adopter) {
		return ResponseEntity.ok(new Gson().toJson(adopter));
	}

	public ResponseEntity<String> presentAll(List<AdopterViewModel> adopters) {
		return ResponseEntity.ok(new Gson().toJson(adopters));
	}

}
