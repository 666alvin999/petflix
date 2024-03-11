package com.petflix.domain.bean;

import com.petflix.domain.bean.presentationvideofields.VideoId;

import java.time.LocalDate;

public record PresentationVideo(VideoId id, String title, String description, LocalDate uploadDate) {
}
