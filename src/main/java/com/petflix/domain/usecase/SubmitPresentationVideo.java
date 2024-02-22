package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;

public class SubmitPresentationVideo {

	private PresentationVideoPort presentationVideoPort;

	public SubmitPresentationVideo(PresentationVideoPort presentationVideoPort) {
		this.presentationVideoPort = presentationVideoPort;
	}

	public ActionSuccess execute(PresentationVideo presentationVideo) {
		return this.presentationVideoPort.submitPresentationVideo(presentationVideo);
	}

}
