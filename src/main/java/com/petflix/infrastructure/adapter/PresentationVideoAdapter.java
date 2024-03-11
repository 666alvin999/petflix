package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dao.PresentationVideoDao;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.infrastructure.mapper.PresentationVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class PresentationVideoAdapter implements PresentationVideoPort {

	private final PresentationVideoDao presentationVideoDao;
	private final AnimalDao animalDao;
	private final PresentationVideoMapper presentationVideoMapper;

	@Autowired
	public PresentationVideoAdapter(PresentationVideoDao presentationVideoDao, AnimalDao animalDao, PresentationVideoMapper presentationVideoMapper) {
		this.presentationVideoDao = presentationVideoDao;
		this.animalDao = animalDao;
		this.presentationVideoMapper = presentationVideoMapper;
	}

	@Override
	public PresentationVideo getPresentationVideoById(String id) {
		List<PresentationVideoDTO> presentationVideoDTOs = this.presentationVideoDao.getPresentationVideoById(id);

		return this.presentationVideoMapper.mapToDomain(presentationVideoDTOs.get(0));
	}

	@Override
	public List<PresentationVideo> getPresentationVideosWithFilter(String animalType, String city) {
		List<AnimalDTO> animalDTOs = this.animalDao.getAnimalsByTypeAndMemberCity(animalType, city);

		Set<String> urls = animalDTOs.stream().map(AnimalDTO::getPresentationVideoId).collect(toSet());

		List<PresentationVideoDTO> presentationVideoDTOs = this.presentationVideoDao.getPresentationVideosByIds(urls);

		return this.presentationVideoMapper.mapAllToDomain(presentationVideoDTOs);
	}

	@Override
	public ActionSuccess submitPresentationVideo(PresentationVideo presentationVideo) {
		PresentationVideoDTO presentationVideoDTO = this.presentationVideoMapper.mapToDTO(presentationVideo);

		return this.presentationVideoDao.submitPresentationDTO(presentationVideoDTO);
	}

}
