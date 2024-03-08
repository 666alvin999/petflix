package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.MemberPresentationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MemberPresenter {

	public ResponseEntity<String> present(MemberPresentationDTO member) {
		return ResponseEntity.ok(new Gson().toJson(member));
	}

	public ResponseEntity<String> presentAll(List<MemberPresentationDTO> members) {
		return ResponseEntity.ok(new Gson().toJson(members));
	}

}
