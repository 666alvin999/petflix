package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresentationVideoAndAnimalTypesPresenter {

	public ResponseEntity<String> present(PresentationVideoAndAnimalTypesViewModel presentationVideoAndAnimalTypesViewModel) {
		return ResponseEntity.ok(new Gson().toJson(presentationVideoAndAnimalTypesViewModel));
	}

	public ResponseEntity<String> presentAll(List<PresentationVideoAndAnimalTypesViewModel> presentationVideoAndAnimalTypesViewModels) {
		return ResponseEntity.ok(new Gson().toJson(presentationVideoAndAnimalTypesViewModels));
	}

}
