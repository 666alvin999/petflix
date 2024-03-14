package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ControlViewModel {

	private AnimalViewModel animal;
	private MemberViewModel member;
	private AdopterViewModel adopter;
	private String adoptionDate;
	private String controlDate;

}
