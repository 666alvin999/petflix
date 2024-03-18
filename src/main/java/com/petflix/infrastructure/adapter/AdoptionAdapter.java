package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Adopter;
import com.petflix.domain.bean.Adoption;
import com.petflix.domain.bean.Animal;
import com.petflix.infrastructure.dao.AdoptionDao;
import com.petflix.infrastructure.dto.AdoptionDTO;
import com.petflix.infrastructure.mapper.AdoptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class AdoptionAdapter {

	private final AdoptionDao adoptionDao;
	private final AnimalAdapter animalAdapter;
	private final AdopterAdapter adopterAdapter;
	private final AdoptionMapper adoptionMapper;

	@Autowired
	public AdoptionAdapter(AdoptionDao adoptionDao, AnimalAdapter animalAdapter, AdopterAdapter adopterAdapter, AdoptionMapper adoptionMapper) {
		this.adoptionDao = adoptionDao;
		this.animalAdapter = animalAdapter;
		this.adopterAdapter = adopterAdapter;
		this.adoptionMapper = adoptionMapper;
	}

	public List<Adoption> getAdoptionsByIds(Set<Integer> ids) {
		List<AdoptionDTO> adoptionDTOs = this.adoptionDao.getAdoptionsByIds(ids);

		Set<Integer> animalsIds = adoptionDTOs.stream().map(AdoptionDTO::getAnimalId).collect(toSet());
		Set<Integer> adoptersIds = adoptionDTOs.stream().map(AdoptionDTO::getAdopterId).collect(toSet());

		List<Animal> animals = this.animalAdapter.getAnimalsByIds(animalsIds);
		List<Adopter> adopters = this.adopterAdapter.getAdoptersByIds(adoptersIds);

		return this.adoptionMapper.mapAllToDomain(adoptionDTOs, adopters, animals);
	}

}
