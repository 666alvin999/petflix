package com.petflix.domain.port;

import com.petflix.domain.bean.Control;

import java.util.List;

public interface ControlPort {

	Control getControlById(int id);

	List<Control> getAllControls();

}
