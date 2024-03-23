package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adopter;
import com.petflix.domain.port.AdopterPort;
import com.petflix.infrastructure.dao.AdopterDao;
import com.petflix.infrastructure.dto.AdopterDTO;
import com.petflix.infrastructure.mapper.AdopterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class AdopterAdapter implements AdopterPort {

	private final AdopterDao adopterDao;
	private final AdopterMapper adopterMapper;

	@Autowired
	public AdopterAdapter(AdopterDao adopterDao, AdopterMapper adopterMapper) {
		this.adopterDao = adopterDao;
		this.adopterMapper = adopterMapper;
	}

	@Override
	public List<Adopter> getAllAdopters() {
		List<AdopterDTO> adopterDTOs = this.adopterDao.getAllAdopters();

		return this.adopterMapper.mapAllToDomain(adopterDTOs);
	}

	public List<Adopter> getAdoptersByIds(Set<Integer> ids) {
		List<AdopterDTO> adopterDTOs = this.adopterDao.getAdoptersByIds(ids);

		return this.adopterMapper.mapAllToDomain(adopterDTOs);
	}

	@Override
	public ActionSuccess createAdopter(Adopter adopter) {
		AdopterDTO adopterDTO = this.adopterMapper.mapToDTO(adopter);

		return this.adopterDao.createAdopter(adopterDTO);
	}

}
