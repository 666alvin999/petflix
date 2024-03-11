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

	private String id;
	private String title;
	private String description;
	private String date;
	private List<String> animalTypes;

}
