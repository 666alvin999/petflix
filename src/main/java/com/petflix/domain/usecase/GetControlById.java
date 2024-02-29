package com.petflix.domain.usecase;

import com.petflix.domain.bean.Control;
import com.petflix.domain.port.ControlPort;

public class GetControlById {

	private ControlPort controlPort;

	public GetControlById(ControlPort controlPort) {
		this.controlPort = controlPort;
	}

	public Control execute(int id) {
		return this.controlPort.getControlById(id);
	}

}
