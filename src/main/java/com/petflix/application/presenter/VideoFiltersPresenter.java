package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.VideoFiltersViewModel;
import org.springframework.http.ResponseEntity;

public class VideoFiltersPresenter {

	public ResponseEntity<String> present(VideoFiltersViewModel videoFiltersViewModel) {
		return ResponseEntity.ok(new Gson().toJson(videoFiltersViewModel));
	}

}
