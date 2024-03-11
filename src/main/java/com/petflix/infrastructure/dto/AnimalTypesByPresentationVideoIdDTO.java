package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AnimalTypesByPresentationVideoIdDTO {

	private String presentationVideoId;
	private String animalTypes;

}
