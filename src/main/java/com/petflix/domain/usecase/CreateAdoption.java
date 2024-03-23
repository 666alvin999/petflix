package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.port.AdoptionPort;

public class CreateAdoption {

	private final AdoptionPort adoptionPort;

	public CreateAdoption(AdoptionPort adoptionPort) {
		this.adoptionPort = adoptionPort;
	}

	public ActionSuccess execute(Adoption adoption) {
		return this.adoptionPort.createAdoption(adoption);
	}

}
