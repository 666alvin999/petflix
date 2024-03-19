package com.petflix.application.mapper;

import com.petflix.application.dto.ActionSuccessViewModel;
import com.petflix.domain.bean.ActionSuccess;

public class ActionSuccessPresentationMapper {

	public ActionSuccessViewModel mapToViewModel(ActionSuccess actionSuccess) {
		return new ActionSuccessViewModel(
			actionSuccess.success(),
			actionSuccess.errorMessage().orElse(null)
		);
	}

}
