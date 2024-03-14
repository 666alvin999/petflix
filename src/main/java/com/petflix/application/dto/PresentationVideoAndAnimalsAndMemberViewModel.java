package com.petflix.application.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PresentationVideoAndAnimalsAndMemberViewModel {

	private PresentationVideoViewModel presentationVideo;
	private List<AnimalViewModel> animals;
	private MemberViewModel member;

}
