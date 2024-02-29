package com.petflix.domain.usecase;

import com.petflix.domain.bean.Adoption;
import com.petflix.domain.port.AdoptionPort;

import java.util.List;

public class GetAllAdoptions {

	private AdoptionPort adoptionPort;

	public GetAllAdoptions(AdoptionPort adoptionPort) {
		this.adoptionPort = adoptionPort;
	}

	public List<Adoption> execute() {
		return this.adoptionPort.getAllAdoptions();
	}

}
