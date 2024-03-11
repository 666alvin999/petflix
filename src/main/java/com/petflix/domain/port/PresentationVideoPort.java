package com.petflix.domain.port;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.PresentationVideo;

import java.util.List;

public interface PresentationVideoPort {

	ActionSuccess submitPresentationVideo(PresentationVideo presentationVideo);

	List<PresentationVideo> getPresentationVideosWithFilter(String animalType, String city);

	PresentationVideo getPresentationVideoById(String id);

}
