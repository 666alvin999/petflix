package com.petflix.domain.bean;

import java.util.Date;

public record Adoption(Adopter adopter, Animal animal, Date date) {
}
