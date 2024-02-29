package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdoptionDTO {

	private int id;
	private int adopterId;
	private int animalId;
	private String adoptionDate;

}
