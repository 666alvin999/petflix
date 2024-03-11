package com.petflix.domain.bean;

import java.time.LocalDate;

public record Adoption(Animal animal, Adopter adopter, LocalDate date) {
}
