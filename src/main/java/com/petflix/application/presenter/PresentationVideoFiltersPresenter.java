package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.PresentationVideoFiltersViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PresentationVideoFiltersPresenter {

	public ResponseEntity<String> present(PresentationVideoFiltersViewModel presentationVideoFiltersViewModel) {
		return ResponseEntity.ok(new Gson().toJson(presentationVideoFiltersViewModel));
	}

}
