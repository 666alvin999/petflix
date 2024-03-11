package com.petflix.application.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PresentationVideoFiltersViewModel {

	private List<String> animalFilters;
	private List<String> cityFilters;

}
