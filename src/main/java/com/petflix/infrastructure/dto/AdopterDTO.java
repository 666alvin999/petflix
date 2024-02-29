package com.petflix.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdopterDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String mail;

}
