package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresentationVideoAndAnimalTypesPresenter {

	public ResponseEntity<String> present(PresentationVideoAndAnimalTypesViewModel adopter) {
		return ResponseEntity.ok(new Gson().toJson(adopter));
	}

	public ResponseEntity<String> presentAll(List<PresentationVideoAndAnimalTypesViewModel> adopters) {
		return ResponseEntity.ok(new Gson().toJson(adopters));
	}

}
