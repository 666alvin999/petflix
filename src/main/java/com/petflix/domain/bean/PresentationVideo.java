package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;

import java.time.LocalDate;

public record PresentationVideo(Id id, Url url, String title, String description, LocalDate postingDate) {
}
