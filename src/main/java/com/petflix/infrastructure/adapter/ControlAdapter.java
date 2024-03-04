package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Control;
import com.petflix.domain.port.ControlPort;
import com.petflix.infrastructure.dao.ControlDao;
import com.petflix.infrastructure.dto.ControlDTO;
import com.petflix.infrastructure.mapper.ControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ControlAdapter implements ControlPort {

	private ControlDao controlDao;
	private AdoptionAdapter adoptionAdapter;
	private ControlMapper controlMapper;

	@Autowired
	public ControlAdapter(ControlDao controlDao, AdoptionAdapter adoptionAdapter, ControlMapper controlMapper) {
		this.controlDao = controlDao;
		this.adoptionAdapter = adoptionAdapter;
		this.controlMapper = controlMapper;
	}

	@Override
	public Control getControlById(int id) {
		List<ControlDTO> controlDTOs = this.controlDao.getControlById(id);

		int adoptionId = controlDTOs.get(0).getAdoptionId();
		Adoption adoption = this.adoptionAdapter.getAdoptionById(adoptionId);

		return this.controlMapper.mapToDomain(controlDTOs.get(0), adoption);
	}

}
