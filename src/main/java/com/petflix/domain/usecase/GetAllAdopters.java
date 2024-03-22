package com.petflix.domain.usecase;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.port.AdopterPort;

import java.util.List;

public class GetAllAdopters {

	private final AdopterPort adopterPort;

	public GetAllAdopters(AdopterPort adopterPort) {
		this.adopterPort = adopterPort;
	}

	public List<Adopter> execute() {
		return this.adopterPort.getAllAdopters();
	}

}
