package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.VideoFiltersViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VideoFiltersPresenter {

	public ResponseEntity<String> present(VideoFiltersViewModel videoFiltersViewModel) {
		return ResponseEntity.ok(new Gson().toJson(videoFiltersViewModel));
	}

}
