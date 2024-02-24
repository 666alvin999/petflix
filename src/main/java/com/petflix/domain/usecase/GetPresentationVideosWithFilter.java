package com.petflix.domain.usecase;

import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;

import java.util.List;

public class GetPresentationVideosWithFilter {

	private final PresentationVideoPort presentationVideoPort;

	public GetPresentationVideosWithFilter(PresentationVideoPort presentationVideoPort) {
		this.presentationVideoPort = presentationVideoPort;
	}

	public List<PresentationVideo> execute(String animalType, String city) {
		return this.presentationVideoPort.getPresentationVideosWithFilter(animalType, city);
	}
}
