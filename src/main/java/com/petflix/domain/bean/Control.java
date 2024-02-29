package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.Id;

import java.time.LocalDate;

public record Control(Id id, Id adoptionId, LocalDate date) {
}
