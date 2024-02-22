package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.port.PresentationVideoAdapter;

public class SubmitPresentationVideo {

	private PresentationVideoAdapter presentationVideoAdapter;

	public SubmitPresentationVideo(PresentationVideoAdapter presentationVideoAdapter) {
		this.presentationVideoAdapter = presentationVideoAdapter;
	}

	public ActionSuccess execute() {
		return null;
	}

}
