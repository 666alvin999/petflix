package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ActionSuccessViewModel {

	private boolean success;
	private String errorMessage;

}
