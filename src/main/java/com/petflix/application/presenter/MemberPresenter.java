package com.petflix.application.presenter;

import com.google.gson.Gson;
import com.petflix.application.dto.MemberViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberPresenter {

	public ResponseEntity<String> presentAll(List<MemberViewModel> memberViewModels) {
		return ResponseEntity.ok(new Gson().toJson(memberViewModels));
	}

}
