package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.Id;

import java.util.Date;

public record Adoption(Id id, Adopter adopter, Animal animal, Date date) {
}
