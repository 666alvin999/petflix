package com.petflix.domain.usecase;

import com.petflix.domain.bean.Control;
import com.petflix.domain.port.ControlPort;

import java.util.List;

public class GetAllControls {

	private final ControlPort controlPort;

	public GetAllControls(ControlPort controlPort) {
		this.controlPort = controlPort;
	}

	public List<Control> execute() {
		return this.controlPort.getAllControls();
	}

}
