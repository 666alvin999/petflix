package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MemberPresentationDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private String email;
	private String phone;

}
