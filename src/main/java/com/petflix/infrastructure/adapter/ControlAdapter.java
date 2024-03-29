package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Control;
import com.petflix.domain.port.ControlPort;
import com.petflix.infrastructure.dao.ControlDao;
import com.petflix.infrastructure.dto.ControlDTO;
import com.petflix.infrastructure.mapper.ControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class ControlAdapter implements ControlPort {

	private final ControlDao controlDao;
	private final AdoptionAdapter adoptionAdapter;
	private final ControlMapper controlMapper;

	@Autowired
	public ControlAdapter(ControlDao controlDao, AdoptionAdapter adoptionAdapter, ControlMapper controlMapper) {
		this.controlDao = controlDao;
		this.adoptionAdapter = adoptionAdapter;
		this.controlMapper = controlMapper;
	}

	@Override
	public List<Control> getAllControls() {
		List<ControlDTO> controlDTOs = this.controlDao.getAllControls();

		Set<Integer> adoptionIds = controlDTOs.stream().map(ControlDTO::getAnimalId).collect(toSet());
		List<Adoption> adoptions = this.adoptionAdapter.getAdoptionsByIds(adoptionIds);

		return this.controlMapper.mapAllToDomain(controlDTOs, adoptions);
	}

	@Override
	public ActionSuccess createControl(Control control) {
		ControlDTO controlDTO = this.controlMapper.mapToDTO(control);

		return this.controlDao.createControl(controlDTO);
	}

}
