package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;

import java.util.List;

public class GetAllPresentationVideos {

	private PresentationVideoPort presentationVideoPort;

	public GetAllPresentationVideos(PresentationVideoPort presentationVideoPort) {
		this.presentationVideoPort = presentationVideoPort;
	}

	public List<PresentationVideo> execute() {
		return this.presentationVideoPort.getAllPresentationVideos();
	}

}
