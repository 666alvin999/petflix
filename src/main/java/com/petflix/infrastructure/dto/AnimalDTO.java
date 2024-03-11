package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AnimalDTO {

	private int id;
	private String name;
	private String type;
	private int age;
	private String presentationVideoId;
	private int memberId;
	private String arrivalDate;

}
