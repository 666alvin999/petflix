package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PresentationVideoDTO {

	private String id;
	private String url;
	private String title;
	private String description;
	private String postingDate;

}
