package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.Id;

import java.time.LocalDate;

public record Adoption(Id id, Adopter adopter, Animal animal, LocalDate date) {
}
