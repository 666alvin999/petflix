package com.petflix.domain.usecase;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Control;
import com.petflix.domain.port.ControlPort;

public class CreateControl {

	private final ControlPort controlPort;

	public CreateControl(ControlPort controlPort) {
		this.controlPort = controlPort;
	}

	public ActionSuccess execute(Control control) {
		return this.controlPort.createControl(control);
	}

}
