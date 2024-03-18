package com.petflix.domain.bean;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.presentationvideofields.VideoId;

import java.time.LocalDate;

public record Animal(Id id, String name, AnimalType type, int age, VideoId videoId, Member managingMember,
                     LocalDate arrivalDate, boolean adopted) {
}

