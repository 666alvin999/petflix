package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdopterPresentationDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String mail;

}
