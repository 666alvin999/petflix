package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdoptionDTO {

	private int animalId;
	private int adopterId;
	private String adoptionDate;

}
