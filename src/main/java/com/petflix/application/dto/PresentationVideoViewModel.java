package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PresentationVideoViewModel {

	private String id;
	private String title;
	private String description;
	private String uploadDate;

}
