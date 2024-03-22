package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.AdopterViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdopterPresenter {

	public ResponseEntity<String> presentAll(List<AdopterViewModel> adopterViewModels) {
		return ResponseEntity.ok(new Gson().toJson(adopterViewModels));
	}

}
