package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.port.AdopterPort;
import com.petflix.infrastructure.dao.AdopterDao;
import com.petflix.infrastructure.dto.AdopterDTO;
import com.petflix.infrastructure.mapper.AdopterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdopterAdapter implements AdopterPort {

	private AdopterDao adopterDao;
	private AdopterMapper adopterMapper;

	@Autowired
	public AdopterAdapter(AdopterDao adopterDao, AdopterMapper adopterMapper) {
		this.adopterDao = adopterDao;
		this.adopterMapper = adopterMapper;
	}

	@Override
	public Adopter getAdopterById(int id) {
		List<AdopterDTO> adopterDTOs = this.adopterDao.getAdopterById(id);

		if (adopterDTOs.size() != 1) {
			return null;
		}

		return this.adopterMapper.mapToDomain(adopterDTOs.get(0));
	}

	@Override
	public List<Adopter> getAdoptersByIds(List<Integer> ids) {
		List<AdopterDTO> adopterDTOs = this.adopterDao.getAdoptersByIds(ids);

		return this.adopterMapper.mapAllToDomain(adopterDTOs);
	}

}
