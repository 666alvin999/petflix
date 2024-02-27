package com.petflix.domain.bean;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.Url;

public record Animal(Id id, String name, AnimalType type, int age, Url presentationUrl, Member managingMember) {
}

