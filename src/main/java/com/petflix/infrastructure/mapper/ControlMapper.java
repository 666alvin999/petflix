package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Control;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.infrastructure.dto.ControlDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ControlMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public Control mapToDomain(ControlDTO controlDTO, Adoption adoption) {
		return new Control(
			new Id(controlDTO.getId()),
			adoption,
			LocalDate.parse(controlDTO.getDate(), this.dateFormatter)
		);
	}

	public ControlDTO mapToDTO(Control control) {
		return new ControlDTO(
			control.id().value(),
			control.adoption().id().value(),
			this.dateFormatter.format(control.date())
		);
	}

}
