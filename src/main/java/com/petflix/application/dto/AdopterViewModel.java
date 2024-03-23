package com.petflix.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdopterViewModel {

	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	private String mail;

}
