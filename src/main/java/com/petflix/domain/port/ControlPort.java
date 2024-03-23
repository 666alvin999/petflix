package com.petflix.domain.port;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Control;

import java.util.List;

public interface ControlPort {

	List<Control> getAllControls();

	ActionSuccess createControl(Control control);

}
