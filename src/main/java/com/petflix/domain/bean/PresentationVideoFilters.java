package com.petflix.domain.bean;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;

import java.util.List;

public record PresentationVideoFilters(List<AnimalType> animalTypes, List<MemberCity> memberCities) {
}
