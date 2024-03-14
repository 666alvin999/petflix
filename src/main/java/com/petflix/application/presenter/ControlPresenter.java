package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.ControlViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControlPresenter {

	public ResponseEntity<String> present(ControlViewModel controlViewModel) {
		return ResponseEntity.ok(new Gson().toJson(controlViewModel));
	}

	public ResponseEntity<String> presentAll(List<ControlViewModel> controlViewModels) {
		return ResponseEntity.ok(new Gson().toJson(controlViewModels));
	}

}
