package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;

public class GetPresentationVideoById {

	private final PresentationVideoPort presentationVideoPort;

	public GetPresentationVideoById(PresentationVideoPort presentationVideoPort) {
		this.presentationVideoPort = presentationVideoPort;
	}

	public PresentationVideo execute(int id) {
		return presentationVideoPort.getPresentationVideoById(id);
	}

}
