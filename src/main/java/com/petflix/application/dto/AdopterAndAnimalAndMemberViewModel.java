package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdopterAndAnimalAndMemberViewModel {

	private AdopterViewModel adopterViewModel;
	private AnimalViewModel animalViewModel;
	private MemberViewModel memberViewModel;

}
