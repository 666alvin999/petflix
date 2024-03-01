package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.port.PresentationVideoPort;
import com.petflix.infrastructure.dao.AnimalDao;
import com.petflix.infrastructure.dao.MemberDao;
import com.petflix.infrastructure.dao.PresentationVideoDao;
import com.petflix.infrastructure.dto.PresentationVideoDTO;
import com.petflix.infrastructure.mapper.PresentationVideoMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PresentationVideoAdapter implements PresentationVideoPort {

	@Autowired
	private PresentationVideoDao presentationVideoDao;

	@Autowired
	private AnimalDao animalDao;

	@Autowired
	private PresentationVideoMapper presentationVideoMapper;


	@Override
	public List<PresentationVideo> getAllPresentationVideos() {
		List<PresentationVideoDTO> presentationVideoDTOs = this.presentationVideoDao.getAllPresentationVideos();

		return this.presentationVideoMapper.mapAllToDomain(presentationVideoDTOs);
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
		return null;
	}

	@Override
	public ActionSuccess submitPresentationVideo(PresentationVideo presentationVideo) {
		return null;
	}

}
