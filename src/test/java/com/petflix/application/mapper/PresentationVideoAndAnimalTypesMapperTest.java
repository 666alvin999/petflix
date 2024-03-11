//package com.petflix.application.mapper;
//
//import com.petflix.application.dto.PresentationVideoAndAnimalTypesViewModel;
//import com.petflix.domain.bean.PresentationVideo;
//import com.petflix.domain.bean.generalfields.Url;
//import com.petflix.domain.bean.presentationvideofields.VideoId;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class PresentationVideoAndAnimalTypesMapperTest {
//
//	private PresentationVideoAndAnimalTypesMapper mapper;
//
//	@BeforeEach
//	public void setUp() {
//		this.mapper = new PresentationVideoAndAnimalTypesMapper();
//	}
//
//	@Test
//	public void shouldMapToViewModel() {
//	    //Arrange
//		PresentationVideoAndAnimalTypesViewModel presentationVideoAndAnimalTypesViewModel = createViewModels().get(0);
//
//	    //Act
//
//	    //Assert
//
//	}
//
//	private static List<PresentationVideoAndAnimalTypesViewModel> createViewModels() {
//		return List.of(
//			new PresentationVideoAndAnimalTypesViewModel("0", "title", "description", "2024-03-11", List.of("chien", "chat")),
//			new PresentationVideoAndAnimalTypesViewModel("1", "title", "description", "2024-03-11", List.of("chien", "chat"))
//		);
//	}
//
//	private static List<PresentationVideo> createPresentationVideos() {
//		List.of(
//			new PresentationVideo(new VideoId("0"), new Url())
//		);
//	}
//
//}