package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.Id;

import java.util.Date;

public record Control(Id id, Id adoptionId, Date date) {
}
