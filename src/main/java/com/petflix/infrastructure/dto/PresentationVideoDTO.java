package com.petflix.infrastructure.dto;

import com.petflix.domain.bean.generalfields.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PresentationVideoDTO {

	private int id;
	private String url;
	private String title;
	private String description;
	private String postingDate;

}
