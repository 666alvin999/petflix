package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.port.AdopterPort;

public class CreateAdopter {

	private final AdopterPort adopterPort;

	public CreateAdopter(AdopterPort adopterPort) {
		this.adopterPort = adopterPort;
	}

	public ActionSuccess execute(Adopter adopter) {
		return this.adopterPort.createAdopter(adopter);
	}

}
