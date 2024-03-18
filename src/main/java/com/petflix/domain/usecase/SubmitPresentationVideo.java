package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.PresentationVideoPort;

import java.util.List;
import java.util.Optional;

public class SubmitPresentationVideo {

	private final PresentationVideoPort presentationVideoPort;
	private final AnimalPort animalPort;

	public SubmitPresentationVideo(PresentationVideoPort presentationVideoPort, AnimalPort animalPort) {
		this.presentationVideoPort = presentationVideoPort;
		this.animalPort = animalPort;
	}

	public ActionSuccess execute(PresentationVideo presentationVideo, List<Animal> animals) {
		ActionSuccess presentationVideoSubmit = this.presentationVideoPort.submitPresentationVideo(presentationVideo);
		ActionSuccess animalsSubmit = this.animalPort.submitAnimals(animals);

		if (presentationVideoSubmit.success() && animalsSubmit.success()) {
			return new ActionSuccess(true);
		} else {
			return new ActionSuccess(false, Optional.of("Impossible d'enregistrer la video et les animaux"));
		}
	}

}
