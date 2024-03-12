package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MemberDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private String mail;
	private String phone;

}
