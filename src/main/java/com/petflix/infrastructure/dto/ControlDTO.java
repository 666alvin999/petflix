package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ControlDTO {

	private int id;
	private int adoptionId;
	private String date;

}
