package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoAdapter;

import java.util.List;

public class GetAllPresentationVideos {

	private PresentationVideoAdapter presentationVideoAdapter;

	public GetAllPresentationVideos(PresentationVideoAdapter presentationVideoAdapter) {
		this.presentationVideoAdapter = presentationVideoAdapter;
	}

	public List<PresentationVideo> execute() {
		return this.presentationVideoAdapter.getAllPresentationVideos();
	}

}
