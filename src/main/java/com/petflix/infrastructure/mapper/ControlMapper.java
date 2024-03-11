package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Control;
import com.petflix.infrastructure.dto.ControlDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ControlMapper {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Control mapToDomain(ControlDTO controlDTO, Adoption adoption) {
		return new Control(
			adoption,
			LocalDate.parse(controlDTO.getControlDate(), this.dateFormatter)
		);
	}

	public ControlDTO mapToDTO(Control control) {
		return new ControlDTO(
			control.adoption().animal().id().value(),
			this.dateFormatter.format(control.date())
		);
	}

	public List<Control> mapAllToDomain(List<ControlDTO> controlDTOs, List<Adoption> adoptions) {
		return controlDTOs.stream().map(controlDTO -> {
			Adoption adoption = findAdoption(adoptions, controlDTO);

			return this.mapToDomain(controlDTO, adoption);
		}).toList();
	}

	private Adoption findAdoption(List<Adoption> adoptions, ControlDTO controlDTO) {
		for (Adoption adoption : adoptions) {
			if (adoption.animal().id().value() == controlDTO.getAnimalId()) {
				return adoption;
			}
		}

		return null;
	}

}
