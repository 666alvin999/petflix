package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.ActionSuccessViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ActionSuccessPresenter {

	public ResponseEntity<String> present(ActionSuccessViewModel actionSuccessViewModel) {
		return ResponseEntity.ok(new Gson().toJson(actionSuccessViewModel));
	}

}
