package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.Url;
import com.petflix.domain.bean.presentationvideofields.VideoId;

import java.time.LocalDate;

public record PresentationVideo(VideoId id, Url url, String title, String description, LocalDate postingDate) {
}
