package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dao.PresentationVideoDao;
import com.petflix.infrastructure.dto.AnimalDTO;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.infrastructure.mapper.PresentationVideoMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public PresentationVideo getPresentationVideoById(int id) {
		List<PresentationVideoDTO> presentationVideoDTOs = this.presentationVideoDao.getPresentationVideoById(id);

		if (presentationVideoDTOs.size() != 1) {
			return null;
		}

		return this.presentationVideoMapper.mapToDomain(presentationVideoDTOs.get(0));
	}

	@Override
	public List<PresentationVideo> getPresentationVideosWithFilter(String animalType, String city) {
		List<AnimalDTO> animalDTOs = this.animalDao.getAnimalsByTypeAndMemberCity(animalType, city);

		List<String> urls = animalDTOs.stream().map(AnimalDTO::getPresentationVideoUrl).toList();

		List<PresentationVideoDTO> presentationVideoDTOs = this.presentationVideoDao.getPresentationVideosByUrls(urls);

		return this.presentationVideoMapper.mapAllToDomain(presentationVideoDTOs);
	}

	@Override
	public ActionSuccess submitPresentationVideo(PresentationVideo presentationVideo) {
		PresentationVideoDTO presentationVideoDTO = this.presentationVideoMapper.mapToDTO(presentationVideo);

		return this.presentationVideoDao.submitPresentationDTO(presentationVideoDTO);
	}

}
