package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Animal;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.port.AnimalPort;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.mapper.AnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Repository
public class AnimalAdapter implements AnimalPort {

	private final AnimalDao animalDao;
	private final MemberAdapter memberAdapter;
	private final AnimalMapper animalMapper;

	@Autowired
	public AnimalAdapter(AnimalDao animalDao, MemberAdapter memberAdapter, AnimalMapper animalMapper) {
		this.animalDao = animalDao;
		this.memberAdapter = memberAdapter;
		this.animalMapper = animalMapper;
	}

	@Override
	public Animal getAnimalById(int id) {
		List<AnimalDTO> animalDTOs = this.animalDao.getAnimalById(id);

		if (animalDTOs.size() != 1) {
			return null;
		}

		Member member = this.memberAdapter.getMemberById(animalDTOs.get(0).getId());

		return this.animalMapper.mapToDomain(animalDTOs.get(0), member);
	}

	@Override
	public List<AnimalType> getAllTypes() {
		List<String> animalTypeDTOs = this.animalDao.getAllTypes();

		return this.animalMapper.mapAllToAnimalTypes(animalTypeDTOs);
	}

	@Override
	public List<Animal> getAnimalsByPresentationVideoUrl(String url) {
		List<AnimalDTO> animalDTOs = this.animalDao.getAnimalsByPresentationVideoUrl(url);
		Set<Integer> memberIds = animalDTOs.stream().map(AnimalDTO::getMemberId).collect(toSet());
		List<Member> members = this.memberAdapter.getMembersByIds(memberIds);

		return this.animalMapper.mapAllToDomain(animalDTOs, members);
	}
}
