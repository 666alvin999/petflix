package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AnimalViewModel {

	private String name;
	private String type;
	private int age;
	private String presentationVideoId;
	private String arrivalDate;
	private boolean adopted;

}
