package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.AdopterPresentationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AdopterPresenter {

	public ResponseEntity<String> present(AdopterPresentationDTO adopter) {
		return ResponseEntity.ok(new Gson().toJson(adopter));
	}

	public ResponseEntity<String> presentAll(List<AdopterPresentationDTO> adopters) {
		return ResponseEntity.ok(new Gson().toJson(adopters));
	}

}
