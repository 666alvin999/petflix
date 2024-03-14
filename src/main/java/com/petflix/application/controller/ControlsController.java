package com.petflix.application.controller;

import com.petflix.application.mapper.ControlMapper;
import com.petflix.application.presenter.ControlPresenter;
import com.petflix.domain.bean.Control;
import com.petflix.domain.port.ControlPort;
import com.petflix.domain.usecase.GetAllControls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/controls")
public class ControlsController {

	private final GetAllControls getAllControls;
	private final ControlMapper controlMapper;
	private final ControlPresenter controlPresenter;

	@Autowired
	public ControlsController(ControlMapper controlMapper, ControlPresenter controlPresenter, ControlPort controlPort) {
		this.getAllControls = new GetAllControls(controlPort);
		this.controlMapper = controlMapper;
		this.controlPresenter = controlPresenter;
	}

	@GetMapping("/getControls")
	@CrossOrigin(origins = {"http://localhost:5173", "https://ecv-petflix.netlify.app/"})
	public ResponseEntity<String> getControls() {
		List<Control> controls = this.getAllControls.execute();

		return this.controlPresenter.presentAll(this.controlMapper.mapAllToViewModel(controls));
	}

}
