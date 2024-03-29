package com.petflix.application.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PresentationVideoAndAnimalTypesViewModel {

	private PresentationVideoViewModel presentationVideo;
	private List<String> animalTypes;

}
