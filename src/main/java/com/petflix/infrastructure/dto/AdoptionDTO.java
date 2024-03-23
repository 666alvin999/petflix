package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdoptionDTO {

	private Integer animalId;
	private Integer adopterId;
	private String adoptionDate;

}
