package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.presentationvideofields.VideoId;
import com.petflix.domain.port.AnimalPort;
import com.petflix.infrastructure.dao.AdoptionDao;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dto.AdoptionDTO;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.AnimalTypesByPresentationVideoIdDTO;
import com.petflix.infrastructure.mapper.AnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class AnimalAdapter implements AnimalPort {

	private final AnimalDao animalDao;
	private final AdoptionDao adoptionDao;
	private final MemberAdapter memberAdapter;
	private final AnimalMapper animalMapper;

	@Autowired
	public AnimalAdapter(AnimalDao animalDao, AdoptionDao adoptionDao, MemberAdapter memberAdapter, AnimalMapper animalMapper) {
		this.animalDao = animalDao;
		this.adoptionDao = adoptionDao;
		this.memberAdapter = memberAdapter;
		this.animalMapper = animalMapper;
	}

	@Override
	public List<Animal> getAnimalsByIds(Set<Integer> ids) {
		List<AnimalDTO> animalDTOs = this.animalDao.getAnimalsByIds(ids);

		Set<Integer> animalIds = animalDTOs.stream().map(AnimalDTO::getId).collect(toSet());
		Set<Integer> memberIds = animalDTOs.stream().map(AnimalDTO::getManagingMember).collect(toSet());

		List<AdoptionDTO> adoptionDTOs = this.adoptionDao.getAdoptionsByIds(animalIds);
		List<Member> members = this.memberAdapter.getMembersByIds(memberIds);

		return this.animalMapper.mapAllToDomain(animalDTOs, members, adoptionDTOs);
	}

	@Override
	public List<AnimalType> getAllTypes() {
		List<String> animalTypeDTOs = this.animalDao.getAllTypes();

		return this.animalMapper.mapAllToAnimalTypes(animalTypeDTOs);
	}

	@Override
	public List<Animal> getAnimalsByPresentationVideoId(String videoId) {
		List<AnimalDTO> animalDTOs = this.animalDao.getAnimalsByPresentationVideoId(videoId);
		Set<Integer> animalIds = animalDTOs.stream().map(AnimalDTO::getId).collect(toSet());

		List<AdoptionDTO> adoptionDTOs = this.adoptionDao.getAdoptionsByIds(animalIds);

		Set<Integer> memberIds = animalDTOs.stream().map(AnimalDTO::getManagingMember).collect(toSet());
		List<Member> members = this.memberAdapter.getMembersByIds(memberIds);

		return this.animalMapper.mapAllToDomain(animalDTOs, members, adoptionDTOs);
	}

	@Override
	public Map<VideoId, List<AnimalType>> getAnimalTypesByPresentationVideoIds(Set<String> videoIds) {
		List<AnimalTypesByPresentationVideoIdDTO> animalTypesByPresentationVideoIdDTOs = this.animalDao.getAnimalTypesGroupByPresentationVideoIds(videoIds);

		return this.animalMapper.mapToAnimalTypesByPresentationVideoIds(animalTypesByPresentationVideoIdDTOs);
	}

	@Override
	public ActionSuccess submitAnimals(List<Animal> animals) {
		List<AnimalDTO> animalDTOs = this.animalMapper.mapAllToDTO(animals);

		return this.animalDao.submitAnimals(animalDTOs);
	}

}
