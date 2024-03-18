package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Adopter;
import com.petflix.infrastructure.dao.AdopterDao;
import com.petflix.infrastructure.dto.AdopterDTO;
import com.petflix.infrastructure.mapper.AdopterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class AdopterAdapter {

	private final AdopterDao adopterDao;
	private final AdopterMapper adopterMapper;

	@Autowired
	public AdopterAdapter(AdopterDao adopterDao, AdopterMapper adopterMapper) {
		this.adopterDao = adopterDao;
		this.adopterMapper = adopterMapper;
	}

	public List<Adopter> getAdoptersByIds(Set<Integer> ids) {
		List<AdopterDTO> adopterDTOs = this.adopterDao.getAdoptersByIds(ids);

		return this.adopterMapper.mapAllToDomain(adopterDTOs);
	}

}
