package com.petflix.domain.bean;

import java.util.Optional;

public record ActionSuccess(boolean success, Optional<String> errorMessage) {

	public ActionSuccess(boolean success) {
		this(success, Optional.empty());
	}

}
